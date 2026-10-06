package com.example.businessrules.library;

import com.example.businessrules.common.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    private final LibraryService service;
    public LibraryController(LibraryService service) { this.service = service; }
    @PostMapping("/books") @ResponseStatus(HttpStatus.CREATED)
    LibraryBook addBook(@Valid @RequestBody CreateBookRequest request) { return service.addBook(request); }
    @PostMapping("/members") @ResponseStatus(HttpStatus.CREATED)
    LibraryMember addMember(@Valid @RequestBody CreateMemberRequest request) { return service.addMember(request); }
    @PostMapping("/borrowings") @ResponseStatus(HttpStatus.CREATED)
    Borrowing borrow(@Valid @RequestBody BorrowRequest request) { return service.borrow(request); }
    @PostMapping("/borrowings/{id}/return")
    Borrowing returnBook(@PathVariable UUID id) { return service.returnBook(id); }
    @GetMapping("/borrowings") Collection<Borrowing> borrowings() { return service.borrowings(); }
}

record CreateBookRequest(@NotBlank String title, @Min(1) int copies) {}
record LibraryBook(UUID id, String title, int availableCopies) {}
record CreateMemberRequest(@NotBlank String name, @NotNull MemberType type) {}
record LibraryMember(UUID id, String name, MemberType type) {}
record BorrowRequest(@NotNull UUID bookId, @NotNull UUID memberId) {}
record Borrowing(UUID id, UUID bookId, UUID memberId, LocalDate borrowedOn, LocalDate dueOn,
                 BorrowingStatus status, BigDecimal lateFine) {}
enum MemberType { STUDENT, NORMAL, PREMIUM }
enum BorrowingStatus { ACTIVE, RETURNED }

@org.springframework.stereotype.Service
class LibraryService {
    private final Map<UUID, LibraryBook> books = new LinkedHashMap<>();
    private final Map<UUID, LibraryMember> members = new LinkedHashMap<>();
    private final Map<UUID, Borrowing> borrowings = new LinkedHashMap<>();
    synchronized LibraryBook addBook(CreateBookRequest r) { LibraryBook b = new LibraryBook(UUID.randomUUID(), r.title(), r.copies()); books.put(b.id(), b); return b; }
    synchronized LibraryMember addMember(CreateMemberRequest r) { LibraryMember m = new LibraryMember(UUID.randomUUID(), r.name(), r.type()); members.put(m.id(), m); return m; }
    synchronized Borrowing borrow(BorrowRequest r) {
        LibraryBook book = book(r.bookId()); LibraryMember member = member(r.memberId());
        if (book.availableCopies() == 0) throw ApiException.conflict("No copies are available");
        List<Borrowing> active = borrowings.values().stream().filter(b -> b.memberId().equals(member.id()) && b.status() == BorrowingStatus.ACTIVE).toList();
        if (member.type() == MemberType.STUDENT && active.size() >= 3) throw ApiException.conflict("A student may borrow at most three books");
        if (member.type() == MemberType.STUDENT && active.stream().anyMatch(b -> b.dueOn().isBefore(LocalDate.now()))) throw ApiException.conflict("A student with overdue books cannot borrow another book");
        LocalDate today = LocalDate.now(); LocalDate due = today.plusDays(member.type() == MemberType.PREMIUM ? 30 : 14);
        Borrowing borrowing = new Borrowing(UUID.randomUUID(), book.id(), member.id(), today, due, BorrowingStatus.ACTIVE, BigDecimal.ZERO);
        books.put(book.id(), new LibraryBook(book.id(), book.title(), book.availableCopies() - 1)); borrowings.put(borrowing.id(), borrowing); return borrowing;
    }
    synchronized Borrowing returnBook(UUID id) {
        Borrowing old = Optional.ofNullable(borrowings.get(id)).orElseThrow(() -> ApiException.notFound("Borrowing not found"));
        if (old.status() == BorrowingStatus.RETURNED) throw ApiException.conflict("Book has already been returned");
        long overdueDays = Math.max(0, LocalDate.now().toEpochDay() - old.dueOn().toEpochDay());
        Borrowing returned = new Borrowing(old.id(), old.bookId(), old.memberId(), old.borrowedOn(), old.dueOn(), BorrowingStatus.RETURNED, BigDecimal.valueOf(overdueDays));
        borrowings.put(id, returned); LibraryBook book = book(old.bookId()); books.put(book.id(), new LibraryBook(book.id(), book.title(), book.availableCopies() + 1)); return returned;
    }
    synchronized Collection<Borrowing> borrowings() { return List.copyOf(borrowings.values()); }
    private LibraryBook book(UUID id) { return Optional.ofNullable(books.get(id)).orElseThrow(() -> ApiException.notFound("Book not found")); }
    private LibraryMember member(UUID id) { return Optional.ofNullable(members.get(id)).orElseThrow(() -> ApiException.notFound("Member not found")); }
}
