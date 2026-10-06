package com.example.businessrules.appointment;

import com.example.businessrules.common.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService service;
    public AppointmentController(AppointmentService service) { this.service = service; }

    @PutMapping("/doctors/{doctorId}/availability")
    DoctorAvailability setAvailability(@PathVariable String doctorId, @RequestParam boolean available) {
        return service.setAvailability(doctorId, available);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Appointment book(@Valid @RequestBody BookAppointmentRequest request) { return service.book(request); }

    @PostMapping("/{id}/cancel")
    Appointment cancel(@PathVariable UUID id) { return service.cancel(id); }

    @GetMapping
    Collection<Appointment> all() { return service.all(); }
}

record BookAppointmentRequest(@NotBlank String doctorId, @NotBlank String patientId,
                              @NotNull @Future LocalDateTime startsAt,
                              @NotNull AppointmentServiceType serviceType) {}
record DoctorAvailability(String doctorId, boolean available) {}
record Appointment(UUID id, String doctorId, String patientId, LocalDateTime startsAt,
                   LocalDateTime endsAt, AppointmentServiceType serviceType,
                   AppointmentStatus status) {}
enum AppointmentServiceType { CONSULTATION, CHECKUP, PROCEDURE }
enum AppointmentStatus { BOOKED, CANCELED }

@org.springframework.stereotype.Service
class AppointmentService {
    private final Map<UUID, Appointment> appointments = new LinkedHashMap<>();
    private final Map<String, Boolean> doctorAvailability = new HashMap<>();

    synchronized DoctorAvailability setAvailability(String doctorId, boolean available) {
        doctorAvailability.put(doctorId, available);
        return new DoctorAvailability(doctorId, available);
    }

    synchronized Appointment book(BookAppointmentRequest request) {
        if (!doctorAvailability.getOrDefault(request.doctorId(), true)) {
            throw ApiException.conflict("Doctor is unavailable");
        }
        LocalDate date = request.startsAt().toLocalDate();
        long bookedToday = appointments.values().stream()
                .filter(a -> a.patientId().equals(request.patientId()) && a.status() == AppointmentStatus.BOOKED)
                .filter(a -> a.startsAt().toLocalDate().equals(date)).count();
        if (bookedToday >= 2) throw ApiException.conflict("A patient may book at most two appointments per day");

        LocalDateTime endsAt = request.startsAt().plus(durationFor(request.serviceType()));
        boolean conflict = appointments.values().stream().anyMatch(a ->
                a.doctorId().equals(request.doctorId()) && a.status() == AppointmentStatus.BOOKED
                        && request.startsAt().isBefore(a.endsAt()) && endsAt.isAfter(a.startsAt()));
        if (conflict) throw ApiException.conflict("Doctor already has an overlapping appointment");

        Appointment appointment = new Appointment(UUID.randomUUID(), request.doctorId(), request.patientId(),
                request.startsAt(), endsAt, request.serviceType(), AppointmentStatus.BOOKED);
        appointments.put(appointment.id(), appointment);
        return appointment;
    }

    synchronized Appointment cancel(UUID id) {
        Appointment appointment = require(id);
        if (appointment.status() == AppointmentStatus.CANCELED) {
            throw ApiException.conflict("A canceled appointment cannot be booked or canceled again");
        }
        if (LocalDateTime.now().plusHours(2).isAfter(appointment.startsAt())) {
            throw ApiException.badRequest("Cancellation is allowed only at least two hours before the appointment");
        }
        Appointment canceled = new Appointment(appointment.id(), appointment.doctorId(), appointment.patientId(),
                appointment.startsAt(), appointment.endsAt(), appointment.serviceType(), AppointmentStatus.CANCELED);
        appointments.put(id, canceled);
        return canceled;
    }

    synchronized Collection<Appointment> all() { return List.copyOf(appointments.values()); }
    private Appointment require(UUID id) { return Optional.ofNullable(appointments.get(id))
            .orElseThrow(() -> ApiException.notFound("Appointment not found")); }
    private Duration durationFor(AppointmentServiceType type) {
        return switch (type) { case CONSULTATION -> Duration.ofMinutes(30); case CHECKUP -> Duration.ofMinutes(45); case PROCEDURE -> Duration.ofMinutes(60); };
    }
}
