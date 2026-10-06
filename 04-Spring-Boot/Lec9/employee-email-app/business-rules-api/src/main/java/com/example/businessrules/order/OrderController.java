package com.example.businessrules.order;

import com.example.businessrules.common.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) { this.service = service; }
    @PostMapping("/products") @ResponseStatus(HttpStatus.CREATED)
    StoreProduct addProduct(@Valid @RequestBody CreateStoreProductRequest request) { return service.addProduct(request); }
    @PostMapping("/customers") @ResponseStatus(HttpStatus.CREATED)
    StoreCustomer addCustomer(@Valid @RequestBody CreateCustomerRequest request) { return service.addCustomer(request); }
    @PutMapping("/customers/{id}/unpaid")
    StoreCustomer setUnpaid(@PathVariable UUID id, @RequestParam boolean value) { return service.setUnpaid(id, value); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    PurchaseOrder create(@Valid @RequestBody CreateOrderRequest request) { return service.create(request); }
    @PutMapping("/{id}/items")
    PurchaseOrder updateItems(@PathVariable UUID id, @Valid @RequestBody List<@Valid OrderItemInput> items) { return service.updateItems(id, items); }
    @PostMapping("/{id}/confirm") PurchaseOrder confirm(@PathVariable UUID id) { return service.confirm(id); }
}

record CreateStoreProductRequest(@NotBlank String name, @DecimalMin("0.01") BigDecimal price, @Min(0) int stock, boolean active) {}
record StoreProduct(UUID id, String name, BigDecimal currentPrice, int stock, boolean active) {}
record CreateCustomerRequest(@NotBlank String name, boolean hasUnpaidPreviousOrders) {}
record StoreCustomer(UUID id, String name, boolean hasUnpaidPreviousOrders) {}
record OrderItemInput(@NotNull UUID productId, @Min(1) int quantity) {}
record CreateOrderRequest(@NotNull UUID customerId, @NotEmpty List<@Valid OrderItemInput> items) {}
record PlacedOrderLine(UUID productId, String productName, int quantity, BigDecimal unitPrice) {}
record PurchaseOrder(UUID id, UUID customerId, List<PlacedOrderLine> items, BigDecimal subtotal, BigDecimal shipping, BigDecimal total, OrderStatus status) {}
enum OrderStatus { CREATED, CONFIRMED }

@org.springframework.stereotype.Service
class OrderService {
    private static final BigDecimal FREE_SHIPPING_THRESHOLD = new BigDecimal("100.00"), STANDARD_SHIPPING = new BigDecimal("12.00");
    private final Map<UUID, StoreProduct> products = new LinkedHashMap<>(); private final Map<UUID, StoreCustomer> customers = new LinkedHashMap<>(); private final Map<UUID, PurchaseOrder> orders = new LinkedHashMap<>();
    synchronized StoreProduct addProduct(CreateStoreProductRequest r) { StoreProduct p = new StoreProduct(UUID.randomUUID(), r.name(), r.price(), r.stock(), r.active()); products.put(p.id(), p); return p; }
    synchronized StoreCustomer addCustomer(CreateCustomerRequest r) { StoreCustomer c = new StoreCustomer(UUID.randomUUID(), r.name(), r.hasUnpaidPreviousOrders()); customers.put(c.id(), c); return c; }
    synchronized StoreCustomer setUnpaid(UUID id, boolean value) { StoreCustomer old = customer(id); StoreCustomer updated = new StoreCustomer(old.id(), old.name(), value); customers.put(id, updated); return updated; }
    synchronized PurchaseOrder create(CreateOrderRequest r) {
        StoreCustomer customer = customer(r.customerId()); if (customer.hasUnpaidPreviousOrders()) throw ApiException.conflict("Customer has unpaid previous orders");
        List<PlacedOrderLine> lines = validateAndPrice(r.items(), Map.of());
        reduceStock(lines); PurchaseOrder order = build(UUID.randomUUID(), customer.id(), lines, OrderStatus.CREATED); orders.put(order.id(), order); return order;
    }
    synchronized PurchaseOrder updateItems(UUID id, List<OrderItemInput> items) {
        PurchaseOrder old = order(id); if (old.status() == OrderStatus.CONFIRMED) throw ApiException.conflict("A confirmed order cannot be modified");
        Map<UUID, Integer> released = old.items().stream().collect(java.util.stream.Collectors.toMap(PlacedOrderLine::productId, PlacedOrderLine::quantity, Integer::sum));
        List<PlacedOrderLine> lines = validateAndPrice(items, released);
        old.items().forEach(line -> changeStock(line.productId(), line.quantity())); reduceStock(lines);
        PurchaseOrder updated = build(old.id(), old.customerId(), lines, old.status()); orders.put(id, updated); return updated;
    }
    synchronized PurchaseOrder confirm(UUID id) { PurchaseOrder old = order(id); if (old.status() != OrderStatus.CREATED) throw ApiException.conflict("Only created orders can be confirmed"); PurchaseOrder updated = build(old.id(), old.customerId(), old.items(), OrderStatus.CONFIRMED); orders.put(id, updated); return updated; }
    private List<PlacedOrderLine> validateAndPrice(List<OrderItemInput> inputs, Map<UUID, Integer> stockToRelease) {
        Set<UUID> unique = new HashSet<>(); List<PlacedOrderLine> result = new ArrayList<>();
        for (OrderItemInput input : inputs) { if (!unique.add(input.productId())) throw ApiException.badRequest("A product may appear only once in an order"); StoreProduct p = product(input.productId()); if (!p.active()) throw ApiException.conflict("Only active products can be ordered"); if (input.quantity() > p.stock() + stockToRelease.getOrDefault(p.id(), 0)) throw ApiException.conflict("Requested quantity exceeds available stock for " + p.name()); result.add(new PlacedOrderLine(p.id(), p.name(), input.quantity(), p.currentPrice())); }
        return result;
    }
    private PurchaseOrder build(UUID id, UUID customerId, List<PlacedOrderLine> items, OrderStatus status) { BigDecimal subtotal = items.stream().map(l -> l.unitPrice().multiply(BigDecimal.valueOf(l.quantity()))).reduce(BigDecimal.ZERO, BigDecimal::add); BigDecimal shipping = subtotal.compareTo(FREE_SHIPPING_THRESHOLD) >= 0 ? BigDecimal.ZERO : STANDARD_SHIPPING; return new PurchaseOrder(id, customerId, List.copyOf(items), money(subtotal), shipping, money(subtotal.add(shipping)), status); }
    private void reduceStock(List<PlacedOrderLine> lines) { lines.forEach(l -> changeStock(l.productId(), -l.quantity())); }
    private void changeStock(UUID id, int delta) { StoreProduct p = product(id); products.put(id, new StoreProduct(p.id(), p.name(), p.currentPrice(), p.stock() + delta, p.active())); }
    private StoreProduct product(UUID id) { return Optional.ofNullable(products.get(id)).orElseThrow(() -> ApiException.notFound("Product not found")); }
    private StoreCustomer customer(UUID id) { return Optional.ofNullable(customers.get(id)).orElseThrow(() -> ApiException.notFound("Customer not found")); }
    private PurchaseOrder order(UUID id) { return Optional.ofNullable(orders.get(id)).orElseThrow(() -> ApiException.notFound("Order not found")); }
    private BigDecimal money(BigDecimal value) { return value.setScale(2, RoundingMode.HALF_UP); }
}
