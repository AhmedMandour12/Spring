package com.example.businessrules.restaurant;

import com.example.businessrules.common.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/api/reservations")
public class RestaurantReservationController {
    private final RestaurantReservationService service;
    public RestaurantReservationController(RestaurantReservationService service) { this.service = service; }
    @PostMapping("/tables") @ResponseStatus(HttpStatus.CREATED)
    RestaurantTable addTable(@Valid @RequestBody CreateTableRequest request) { return service.addTable(request); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    TableReservation reserve(@Valid @RequestBody ReservationRequest request) { return service.reserve(request); }
    @PostMapping("/{id}/confirm") TableReservation confirm(@PathVariable UUID id) { return service.confirm(id); }
    @PostMapping("/{id}/cancel") TableReservation cancel(@PathVariable UUID id) { return service.cancel(id); }
    @GetMapping Collection<TableReservation> all() { return service.all(); }
}

record CreateTableRequest(@NotBlank String label, @Min(1) int capacity) {}
record RestaurantTable(UUID id, String label, int capacity) {}
record ReservationRequest(@NotNull UUID tableId, @NotBlank String customerId, @Min(1) int guests,
                          @NotNull @Future LocalDateTime startsAt, @NotNull @Future LocalDateTime endsAt) {}
record TableReservation(UUID id, UUID tableId, String customerId, int guests, LocalDateTime startsAt,
                        LocalDateTime endsAt, LocalDateTime createdAt, ReservationStatus status) {}
enum ReservationStatus { PENDING, CONFIRMED, CANCELED, EXPIRED }

@org.springframework.stereotype.Service
class RestaurantReservationService {
    private static final LocalTime OPENS = LocalTime.of(11, 0), CLOSES = LocalTime.of(22, 0);
    private final Map<UUID, RestaurantTable> tables = new HashMap<>();
    private final Map<UUID, TableReservation> reservations = new LinkedHashMap<>();
    synchronized RestaurantTable addTable(CreateTableRequest r) { RestaurantTable t = new RestaurantTable(UUID.randomUUID(), r.label(), r.capacity()); tables.put(t.id(), t); return t; }
    synchronized TableReservation reserve(ReservationRequest r) {
        expirePending(); RestaurantTable table = Optional.ofNullable(tables.get(r.tableId())).orElseThrow(() -> ApiException.notFound("Table not found"));
        if (r.guests() > table.capacity()) throw ApiException.badRequest("Guest count exceeds table capacity");
        if (!r.endsAt().isAfter(r.startsAt()) || !r.startsAt().toLocalDate().equals(r.endsAt().toLocalDate()) || r.startsAt().toLocalTime().isBefore(OPENS) || r.endsAt().toLocalTime().isAfter(CLOSES)) throw ApiException.badRequest("Reservations must be within 11:00-22:00 on the same day");
        if (r.startsAt().isBefore(LocalDateTime.now().plusMinutes(30))) throw ApiException.badRequest("Reservations need at least 30 minutes notice");
        long customerActive = reservations.values().stream().filter(x -> x.customerId().equals(r.customerId()) && active(x)).count();
        if (customerActive >= 2) throw ApiException.conflict("Customer already has two active reservations");
        boolean overlaps = reservations.values().stream().anyMatch(x -> x.tableId().equals(r.tableId()) && active(x) && r.startsAt().isBefore(x.endsAt()) && r.endsAt().isAfter(x.startsAt()));
        if (overlaps) throw ApiException.conflict("Table already has an overlapping reservation");
        TableReservation reservation = new TableReservation(UUID.randomUUID(), r.tableId(), r.customerId(), r.guests(), r.startsAt(), r.endsAt(), LocalDateTime.now(), ReservationStatus.PENDING); reservations.put(reservation.id(), reservation); return reservation;
    }
    synchronized TableReservation confirm(UUID id) { expirePending(); TableReservation r = require(id); if (r.status() != ReservationStatus.PENDING) throw ApiException.conflict("Only pending reservations can be confirmed"); return replace(r, ReservationStatus.CONFIRMED); }
    synchronized TableReservation cancel(UUID id) { expirePending(); TableReservation r = require(id); if (!active(r)) throw ApiException.conflict("Only active reservations can be canceled"); if (!r.startsAt().isAfter(LocalDateTime.now().plusHours(1))) throw ApiException.badRequest("Cancellation is allowed only more than one hour before the reservation"); return replace(r, ReservationStatus.CANCELED); }
    synchronized Collection<TableReservation> all() { expirePending(); return List.copyOf(reservations.values()); }
    private boolean active(TableReservation r) { return r.status() == ReservationStatus.PENDING || r.status() == ReservationStatus.CONFIRMED; }
    private TableReservation require(UUID id) { return Optional.ofNullable(reservations.get(id)).orElseThrow(() -> ApiException.notFound("Reservation not found")); }
    private TableReservation replace(TableReservation r, ReservationStatus status) { TableReservation result = new TableReservation(r.id(), r.tableId(), r.customerId(), r.guests(), r.startsAt(), r.endsAt(), r.createdAt(), status); reservations.put(r.id(), result); return result; }
    private void expirePending() { reservations.values().stream().filter(r -> r.status() == ReservationStatus.PENDING && r.createdAt().plusMinutes(15).isBefore(LocalDateTime.now())).toList().forEach(r -> replace(r, ReservationStatus.EXPIRED)); }
}
