package com.example.businessrules.delivery;

import com.example.businessrules.common.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final DeliveryService service;
    public DeliveryController(DeliveryService service) { this.service = service; }
    @PostMapping("/drivers") @ResponseStatus(HttpStatus.CREATED)
    Driver addDriver(@Valid @RequestBody CreateDriverRequest request) { return service.addDriver(request); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    DeliveryOrder create(@Valid @RequestBody CreateDeliveryRequest request) { return service.create(request); }
    @PostMapping("/{id}/assign") DeliveryOrder assign(@PathVariable UUID id, @RequestParam UUID driverId) { return service.assign(id, driverId); }
    @PostMapping("/{id}/status") DeliveryOrder status(@PathVariable UUID id, @RequestParam DeliveryStatus target) { return service.transition(id, target); }
    @PostMapping("/{id}/cancel") DeliveryOrder cancel(@PathVariable UUID id) { return service.cancel(id); }
}

record CreateDriverRequest(@NotBlank String name, @NotNull DeliveryVehicleType vehicleType) {}
record Driver(UUID id, String name, DeliveryVehicleType vehicleType) {}
record CreateDeliveryRequest(@NotBlank String customerId, @DecimalMin("0.01") BigDecimal distanceKm,
                             @DecimalMin("0.01") BigDecimal packageWeightKg, @NotNull DeliveryVehicleType requestedVehicle) {}
record DeliveryOrder(UUID id, String customerId, BigDecimal distanceKm, BigDecimal packageWeightKg,
                     DeliveryVehicleType requestedVehicle, BigDecimal deliveryFee, UUID driverId,
                     DeliveryStatus status, LocalDateTime createdAt) {}
enum DeliveryVehicleType { MOTORCYCLE, CAR, VAN, TRUCK }
enum DeliveryStatus { CREATED, ASSIGNED, PICKED_UP, IN_TRANSIT, DELIVERED, CANCELED }

@org.springframework.stereotype.Service
class DeliveryService {
    private static final Map<DeliveryVehicleType, BigDecimal> WEIGHT_LIMITS = Map.of(DeliveryVehicleType.MOTORCYCLE, new BigDecimal("5"), DeliveryVehicleType.CAR, new BigDecimal("25"), DeliveryVehicleType.VAN, new BigDecimal("100"), DeliveryVehicleType.TRUCK, new BigDecimal("1000"));
    private final Map<UUID, Driver> drivers = new LinkedHashMap<>(); private final Map<UUID, DeliveryOrder> orders = new LinkedHashMap<>();
    synchronized Driver addDriver(CreateDriverRequest r) { Driver driver = new Driver(UUID.randomUUID(), r.name(), r.vehicleType()); drivers.put(driver.id(), driver); return driver; }
    synchronized DeliveryOrder create(CreateDeliveryRequest r) {
        if (r.packageWeightKg().compareTo(WEIGHT_LIMITS.get(r.requestedVehicle())) > 0) throw ApiException.badRequest("Package exceeds the requested vehicle's weight limit");
        if (r.distanceKm().compareTo(new BigDecimal("50")) > 0 && r.requestedVehicle() != DeliveryVehicleType.TRUCK) throw ApiException.badRequest("Deliveries over 50 km require a truck");
        BigDecimal fee = new BigDecimal("5").add(r.distanceKm().multiply(new BigDecimal("0.75"))).add(r.packageWeightKg().multiply(new BigDecimal("1.50"))).setScale(2, RoundingMode.HALF_UP);
        DeliveryOrder order = new DeliveryOrder(UUID.randomUUID(), r.customerId(), r.distanceKm(), r.packageWeightKg(), r.requestedVehicle(), fee, null, DeliveryStatus.CREATED, LocalDateTime.now()); orders.put(order.id(), order); return order;
    }
    synchronized DeliveryOrder assign(UUID id, UUID driverId) {
        DeliveryOrder order = order(id); Driver driver = Optional.ofNullable(drivers.get(driverId)).orElseThrow(() -> ApiException.notFound("Driver not found"));
        if (order.status() != DeliveryStatus.CREATED) throw ApiException.conflict("Only created orders can be assigned"); if (driver.vehicleType() != order.requestedVehicle()) throw ApiException.badRequest("Driver vehicle does not match the requested vehicle");
        boolean busy = orders.values().stream().anyMatch(o -> driver.id().equals(o.driverId()) && active(o.status())); if (busy) throw ApiException.conflict("Driver already has an active delivery");
        return replace(order, driver.id(), DeliveryStatus.ASSIGNED);
    }
    synchronized DeliveryOrder transition(UUID id, DeliveryStatus target) {
        DeliveryOrder order = order(id); DeliveryStatus expected = switch (order.status()) { case ASSIGNED -> DeliveryStatus.PICKED_UP; case PICKED_UP -> DeliveryStatus.IN_TRANSIT; case IN_TRANSIT -> DeliveryStatus.DELIVERED; default -> null; };
        if (expected != target) throw ApiException.badRequest("Invalid delivery status transition from " + order.status() + " to " + target);
        return replace(order, order.driverId(), target);
    }
    synchronized DeliveryOrder cancel(UUID id) { DeliveryOrder order = order(id); if (order.status() != DeliveryStatus.CREATED && order.status() != DeliveryStatus.ASSIGNED) throw ApiException.badRequest("A customer can cancel only before driver pickup"); return replace(order, order.driverId(), DeliveryStatus.CANCELED); }
    private DeliveryOrder replace(DeliveryOrder old, UUID driverId, DeliveryStatus status) { DeliveryOrder result = new DeliveryOrder(old.id(), old.customerId(), old.distanceKm(), old.packageWeightKg(), old.requestedVehicle(), old.deliveryFee(), driverId, status, old.createdAt()); orders.put(old.id(), result); return result; }
    private boolean active(DeliveryStatus status) { return status == DeliveryStatus.ASSIGNED || status == DeliveryStatus.PICKED_UP || status == DeliveryStatus.IN_TRANSIT; }
    private DeliveryOrder order(UUID id) { return Optional.ofNullable(orders.get(id)).orElseThrow(() -> ApiException.notFound("Delivery order not found")); }
}
