package com.example.businessrules.parking;

import com.example.businessrules.common.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    private final ParkingService service;
    public ParkingController(ParkingService service) { this.service = service; }
    @PostMapping("/tickets") @ResponseStatus(HttpStatus.CREATED)
    ParkingTicket enter(@Valid @RequestBody ParkingEntryRequest request) { return service.enter(request); }
    @PostMapping("/tickets/{id}/exit") ParkingReceipt exit(@PathVariable UUID id, @RequestParam(defaultValue = "false") boolean lostTicket) { return service.exit(id, lostTicket); }
    @GetMapping("/capacity") ParkingCapacity capacity() { return service.capacity(); }
}

record ParkingEntryRequest(@NotBlank String vehiclePlate, @NotNull VehicleType vehicleType, boolean vip) {}
record ParkingTicket(UUID id, String vehiclePlate, VehicleType vehicleType, boolean vip, LocalDateTime enteredAt, TicketStatus status) {}
record ParkingReceipt(UUID ticketId, String vehiclePlate, LocalDateTime enteredAt, LocalDateTime exitedAt, boolean lostTicket, BigDecimal fee) {}
record ParkingCapacity(int totalSpaces, int occupiedSpaces, int availableSpaces) {}
enum VehicleType { MOTORCYCLE, CAR, TRUCK }
enum TicketStatus { ACTIVE, CLOSED }

@org.springframework.stereotype.Service
class ParkingService {
    private static final int CAPACITY = 100; private static final BigDecimal LOST_TICKET_PENALTY = new BigDecimal("50.00");
    private final Map<UUID, ParkingTicket> tickets = new LinkedHashMap<>();
    synchronized ParkingTicket enter(ParkingEntryRequest r) {
        if (tickets.values().stream().anyMatch(t -> t.status() == TicketStatus.ACTIVE && t.vehiclePlate().equalsIgnoreCase(r.vehiclePlate()))) throw ApiException.conflict("Vehicle already has an active ticket");
        if (tickets.values().stream().filter(t -> t.status() == TicketStatus.ACTIVE).count() >= CAPACITY) throw ApiException.conflict("Parking capacity has been reached");
        ParkingTicket ticket = new ParkingTicket(UUID.randomUUID(), r.vehiclePlate().toUpperCase(Locale.ROOT), r.vehicleType(), r.vip(), LocalDateTime.now(), TicketStatus.ACTIVE); tickets.put(ticket.id(), ticket); return ticket;
    }
    synchronized ParkingReceipt exit(UUID id, boolean lostTicket) {
        ParkingTicket ticket = Optional.ofNullable(tickets.get(id)).orElseThrow(() -> ApiException.notFound("Parking ticket not found")); if (ticket.status() != TicketStatus.ACTIVE) throw ApiException.conflict("Ticket is already closed");
        LocalDateTime exited = LocalDateTime.now(); BigDecimal fee = lostTicket ? LOST_TICKET_PENALTY : calculate(ticket, exited); tickets.put(id, new ParkingTicket(ticket.id(), ticket.vehiclePlate(), ticket.vehicleType(), ticket.vip(), ticket.enteredAt(), TicketStatus.CLOSED)); return new ParkingReceipt(ticket.id(), ticket.vehiclePlate(), ticket.enteredAt(), exited, lostTicket, fee);
    }
    synchronized ParkingCapacity capacity() { int occupied = (int) tickets.values().stream().filter(t -> t.status() == TicketStatus.ACTIVE).count(); return new ParkingCapacity(CAPACITY, occupied, CAPACITY - occupied); }
    private BigDecimal calculate(ParkingTicket ticket, LocalDateTime exited) { long minutes = Math.max(1, java.time.Duration.between(ticket.enteredAt(), exited).toMinutes()); long hours = (minutes + 59) / 60; BigDecimal first = switch (ticket.vehicleType()) { case MOTORCYCLE -> new BigDecimal("3"); case CAR -> new BigDecimal("5"); case TRUCK -> new BigDecimal("10"); }; BigDecimal additional = switch (ticket.vehicleType()) { case MOTORCYCLE -> new BigDecimal("2"); case CAR -> new BigDecimal("3"); case TRUCK -> new BigDecimal("6"); }; BigDecimal total = first.add(additional.multiply(BigDecimal.valueOf(Math.max(0, hours - 1)))); if (ticket.vip()) total = total.multiply(new BigDecimal("0.90")); return total.setScale(2, RoundingMode.HALF_UP); }
}
