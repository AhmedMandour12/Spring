package com.example.businessrules.discount;

import com.example.businessrules.common.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    private final DiscountService service;
    public DiscountController(DiscountService service) { this.service = service; }

    @PostMapping("/products") @ResponseStatus(HttpStatus.CREATED)
    DiscountProduct addProduct(@Valid @RequestBody CreateDiscountProduct request) { return service.addProduct(request); }
    @PostMapping("/quote")
    PriceQuote quote(@Valid @RequestBody PriceQuoteRequest request) { return service.quote(request); }
}

record CreateDiscountProduct(@NotBlank String name, @NotBlank String category,
                             @DecimalMin("0.01") BigDecimal price, boolean discountEligible) {}
record DiscountProduct(UUID id, String name, String category, BigDecimal price, boolean discountEligible) {}
record QuoteItem(UUID productId, @Min(1) int quantity) {}
record PriceQuoteRequest(@NotEmpty List<@Valid QuoteItem> items, boolean vipCustomer, Set<String> discountCodes) {}
record PriceQuote(BigDecimal subtotal, BigDecimal discount, int discountPercent,
                  BigDecimal shipping, BigDecimal finalTotal) {}

@org.springframework.stereotype.Service
class DiscountService {
    private static final Map<String, Integer> CATEGORY_DISCOUNTS = Map.of("BOOKS", 10, "ELECTRONICS", 8, "CLOTHING", 15);
    private static final Map<String, Integer> CODES = Map.of("WELCOME10", 10, "FLASH5", 5, "SAVE15", 15);
    private static final Set<Set<String>> INCOMPATIBLE = Set.of(Set.of("WELCOME10", "SAVE15"), Set.of("FLASH5", "SAVE15"));
    private static final BigDecimal FREE_SHIPPING_THRESHOLD = new BigDecimal("100.00");
    private static final BigDecimal STANDARD_SHIPPING = new BigDecimal("12.00");
    private final Map<UUID, DiscountProduct> products = new HashMap<>();

    synchronized DiscountProduct addProduct(CreateDiscountProduct r) {
        DiscountProduct product = new DiscountProduct(UUID.randomUUID(), r.name(), r.category().toUpperCase(Locale.ROOT), r.price(), r.discountEligible());
        products.put(product.id(), product); return product;
    }
    synchronized PriceQuote quote(PriceQuoteRequest request) {
        Set<String> codes = request.discountCodes() == null ? Set.of() : request.discountCodes().stream()
                .map(s -> s.toUpperCase(Locale.ROOT)).collect(java.util.stream.Collectors.toSet());
        if (!CODES.keySet().containsAll(codes)) throw ApiException.badRequest("Unknown discount code");
        if (INCOMPATIBLE.stream().anyMatch(codes::containsAll)) throw ApiException.badRequest("The selected discount codes cannot be combined");
        BigDecimal subtotal = BigDecimal.ZERO, discount = BigDecimal.ZERO;
        for (QuoteItem item : request.items()) {
            DiscountProduct p = Optional.ofNullable(products.get(item.productId())).orElseThrow(() -> ApiException.notFound("Product not found: " + item.productId()));
            BigDecimal line = p.price().multiply(BigDecimal.valueOf(item.quantity())); subtotal = subtotal.add(line);
            if (p.discountEligible()) {
                int pct = CATEGORY_DISCOUNTS.getOrDefault(p.category(), 0) + (request.vipCustomer() ? 5 : 0) + codes.stream().mapToInt(CODES::get).sum();
                pct = Math.min(pct, 30);
                discount = discount.add(line.multiply(BigDecimal.valueOf(pct)).movePointLeft(2));
            }
        }
        discount = discount.setScale(2, RoundingMode.HALF_UP);
        int effectivePercentage = subtotal.signum() == 0 ? 0 : discount.multiply(BigDecimal.valueOf(100)).divide(subtotal, 0, RoundingMode.HALF_UP).intValue();
        BigDecimal shipping = subtotal.compareTo(FREE_SHIPPING_THRESHOLD) > 0 ? BigDecimal.ZERO : STANDARD_SHIPPING;
        return new PriceQuote(money(subtotal), discount, Math.min(effectivePercentage, 30), shipping, money(subtotal.subtract(discount).add(shipping)));
    }
    private BigDecimal money(BigDecimal value) { return value.setScale(2, RoundingMode.HALF_UP); }
}
