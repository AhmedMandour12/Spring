package com.example.businessrules.leave;

import com.example.businessrules.common.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
    private final LeaveService service;
    public LeaveController(LeaveService service) { this.service = service; }
    @PostMapping("/employees") @ResponseStatus(HttpStatus.CREATED)
    Employee addEmployee(@Valid @RequestBody CreateEmployeeRequest request) { return service.addEmployee(request); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    LeaveRequest request(@Valid @RequestBody LeaveRequestInput request) { return service.request(request); }
    @PostMapping("/{id}/approve") LeaveRequest approve(@PathVariable UUID id) { return service.transition(id, LeaveStatus.APPROVED); }
    @PostMapping("/{id}/reject") LeaveRequest reject(@PathVariable UUID id) { return service.transition(id, LeaveStatus.REJECTED); }
    @PostMapping("/{id}/cancel") LeaveRequest cancel(@PathVariable UUID id) { return service.transition(id, LeaveStatus.CANCELED); }
    @GetMapping("/employees/{id}/balance") LeaveBalance balance(@PathVariable UUID id) { return service.balance(id); }
}

record CreateEmployeeRequest(@NotBlank String name, @NotNull EmployeeType type) {}
record Employee(UUID id, String name, EmployeeType type) {}
record LeaveRequestInput(@NotNull UUID employeeId, @NotNull LocalDate startDate, @NotNull LocalDate endDate) {}
record LeaveRequest(UUID id, UUID employeeId, LocalDate startDate, LocalDate endDate, int businessDays, LeaveStatus status) {}
record LeaveBalance(UUID employeeId, int yearlyLimit, int usedOrReservedDays, int remainingDays) {}
enum EmployeeType { REGULAR, CONTRACTOR, EXECUTIVE }
enum LeaveStatus { PENDING, APPROVED, REJECTED, CANCELED }

@org.springframework.stereotype.Service
class LeaveService {
    private static final Map<EmployeeType, Integer> LIMITS = Map.of(EmployeeType.REGULAR, 21, EmployeeType.CONTRACTOR, 14, EmployeeType.EXECUTIVE, 30);
    private final Map<UUID, Employee> employees = new HashMap<>(); private final Map<UUID, LeaveRequest> requests = new LinkedHashMap<>();
    synchronized Employee addEmployee(CreateEmployeeRequest r) { Employee e = new Employee(UUID.randomUUID(), r.name(), r.type()); employees.put(e.id(), e); return e; }
    synchronized LeaveRequest request(LeaveRequestInput r) {
        Employee employee = employee(r.employeeId()); LocalDate today = LocalDate.now();
        if (r.startDate().isBefore(today)) throw ApiException.badRequest("Leave cannot start before today");
        if (r.endDate().isBefore(r.startDate())) throw ApiException.badRequest("End date cannot be before start date");
        if (r.startDate().getYear() != r.endDate().getYear()) throw ApiException.badRequest("A leave request must be within one calendar year");
        boolean overlap = requests.values().stream().anyMatch(x -> x.employeeId().equals(employee.id()) && countable(x) && !r.endDate().isBefore(x.startDate()) && !r.startDate().isAfter(x.endDate()));
        if (overlap) throw ApiException.conflict("Leave request overlaps an existing active request");
        int days = businessDays(r.startDate(), r.endDate()); LeaveBalance balance = balance(employee.id());
        if (days > balance.remainingDays()) throw ApiException.conflict("Leave exceeds the remaining yearly balance");
        LeaveRequest request = new LeaveRequest(UUID.randomUUID(), employee.id(), r.startDate(), r.endDate(), days, LeaveStatus.PENDING); requests.put(request.id(), request); return request;
    }
    synchronized LeaveRequest transition(UUID id, LeaveStatus target) {
        LeaveRequest r = Optional.ofNullable(requests.get(id)).orElseThrow(() -> ApiException.notFound("Leave request not found"));
        if (!countable(r)) throw ApiException.conflict("Rejected or canceled leave cannot transition again");
        if (target == LeaveStatus.PENDING) throw ApiException.badRequest("Invalid leave status transition");
        LeaveRequest updated = new LeaveRequest(r.id(), r.employeeId(), r.startDate(), r.endDate(), r.businessDays(), target); requests.put(id, updated); return updated;
    }
    synchronized LeaveBalance balance(UUID employeeId) { Employee e = employee(employeeId); int used = requests.values().stream().filter(r -> r.employeeId().equals(e.id()) && countable(r) && r.startDate().getYear() == LocalDate.now().getYear()).mapToInt(LeaveRequest::businessDays).sum(); int limit = LIMITS.get(e.type()); return new LeaveBalance(e.id(), limit, used, limit - used); }
    private boolean countable(LeaveRequest r) { return r.status() == LeaveStatus.PENDING || r.status() == LeaveStatus.APPROVED; }
    private Employee employee(UUID id) { return Optional.ofNullable(employees.get(id)).orElseThrow(() -> ApiException.notFound("Employee not found")); }
    private int businessDays(LocalDate start, LocalDate end) { int count = 0; for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) count++; return count; }
}
