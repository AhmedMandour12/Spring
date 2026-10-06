package com.example.businessrules.course;

import com.example.businessrules.common.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/enrollments")
public class CourseEnrollmentController {
    private final CourseEnrollmentService service;
    public CourseEnrollmentController(CourseEnrollmentService service) { this.service = service; }
    @PostMapping("/courses") @ResponseStatus(HttpStatus.CREATED)
    Course addCourse(@Valid @RequestBody CreateCourseRequest request) { return service.addCourse(request); }
    @PostMapping("/students") @ResponseStatus(HttpStatus.CREATED)
    Student addStudent(@Valid @RequestBody CreateStudentRequest request) { return service.addStudent(request); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    Enrollment enroll(@Valid @RequestBody EnrollRequest request) { return service.enroll(request); }
    @PostMapping("/{id}/drop") Enrollment drop(@PathVariable UUID id) { return service.drop(id); }
}

record CreateCourseRequest(@NotBlank String code, @Min(1) int maximumStudents,
                           Set<UUID> prerequisiteCourseIds, Set<StudentLevel> allowedLevels,
                           @NotNull @FutureOrPresent LocalDate dropDeadline) {}
record Course(UUID id, String code, int maximumStudents, Set<UUID> prerequisiteCourseIds,
              Set<StudentLevel> allowedLevels, LocalDate dropDeadline) {}
record CreateStudentRequest(@NotBlank String name, @NotNull StudentLevel level, Set<UUID> passedCourseIds) {}
record Student(UUID id, String name, StudentLevel level, Set<UUID> passedCourseIds) {}
record EnrollRequest(@NotNull UUID studentId, @NotNull UUID courseId) {}
record Enrollment(UUID id, UUID studentId, UUID courseId, EnrollmentStatus status) {}
enum StudentLevel { FRESHMAN, SOPHOMORE, JUNIOR, SENIOR }
enum EnrollmentStatus { ENROLLED, DROPPED }

@org.springframework.stereotype.Service
class CourseEnrollmentService {
    private final Map<UUID, Course> courses = new LinkedHashMap<>(); private final Map<UUID, Student> students = new LinkedHashMap<>(); private final Map<UUID, Enrollment> enrollments = new LinkedHashMap<>();
    synchronized Course addCourse(CreateCourseRequest r) { Set<UUID> prereqs = r.prerequisiteCourseIds() == null ? Set.of() : Set.copyOf(r.prerequisiteCourseIds()); if (!courses.keySet().containsAll(prereqs)) throw ApiException.badRequest("Every prerequisite course must exist"); Course c = new Course(UUID.randomUUID(), r.code(), r.maximumStudents(), prereqs, r.allowedLevels() == null ? Set.of() : Set.copyOf(r.allowedLevels()), r.dropDeadline()); courses.put(c.id(), c); return c; }
    synchronized Student addStudent(CreateStudentRequest r) { Set<UUID> passed = r.passedCourseIds() == null ? Set.of() : Set.copyOf(r.passedCourseIds()); if (!courses.keySet().containsAll(passed)) throw ApiException.badRequest("A passed course must exist"); Student s = new Student(UUID.randomUUID(), r.name(), r.level(), passed); students.put(s.id(), s); return s; }
    synchronized Enrollment enroll(EnrollRequest r) {
        Student student = student(r.studentId()); Course course = course(r.courseId());
        if (enrollments.values().stream().anyMatch(e -> e.studentId().equals(student.id()) && e.courseId().equals(course.id()))) throw ApiException.conflict("Student cannot enroll in the same course twice");
        long filled = enrollments.values().stream().filter(e -> e.courseId().equals(course.id()) && e.status() == EnrollmentStatus.ENROLLED).count(); if (filled >= course.maximumStudents()) throw ApiException.conflict("Course has reached its maximum enrollment");
        if (!student.passedCourseIds().containsAll(course.prerequisiteCourseIds())) throw ApiException.badRequest("Student has not passed every required prerequisite");
        if (!course.allowedLevels().isEmpty() && !course.allowedLevels().contains(student.level())) throw ApiException.badRequest("Course is not available for this student level");
        Enrollment e = new Enrollment(UUID.randomUUID(), student.id(), course.id(), EnrollmentStatus.ENROLLED); enrollments.put(e.id(), e); return e;
    }
    synchronized Enrollment drop(UUID id) { Enrollment e = Optional.ofNullable(enrollments.get(id)).orElseThrow(() -> ApiException.notFound("Enrollment not found")); if (e.status() != EnrollmentStatus.ENROLLED) throw ApiException.conflict("Enrollment has already been dropped"); if (!LocalDate.now().isBefore(course(e.courseId()).dropDeadline())) throw ApiException.badRequest("Courses can be dropped only before the deadline"); Enrollment dropped = new Enrollment(e.id(), e.studentId(), e.courseId(), EnrollmentStatus.DROPPED); enrollments.put(id, dropped); return dropped; }
    private Student student(UUID id) { return Optional.ofNullable(students.get(id)).orElseThrow(() -> ApiException.notFound("Student not found")); }
    private Course course(UUID id) { return Optional.ofNullable(courses.get(id)).orElseThrow(() -> ApiException.notFound("Course not found")); }
}
