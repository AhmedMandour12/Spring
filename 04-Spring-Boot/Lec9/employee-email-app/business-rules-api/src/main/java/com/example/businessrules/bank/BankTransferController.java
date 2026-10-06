package com.example.businessrules.bank;

import com.example.businessrules.common.ApiException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/transfers")
public class BankTransferController {
    private final BankTransferService service;
    public BankTransferController(BankTransferService service) { this.service = service; }
    @PostMapping("/accounts") @ResponseStatus(HttpStatus.CREATED)
    BankAccount createAccount(@Valid @RequestBody CreateAccountRequest request) { return service.createAccount(request); }
    @GetMapping("/accounts") Collection<BankAccount> accounts() { return service.accounts(); }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Transfer transfer(@Valid @RequestBody TransferRequest request) { return service.transfer(request); }
}

record CreateAccountRequest(@NotBlank String owner, @NotNull AccountType type,
                            @DecimalMin("0.00") BigDecimal initialBalance, boolean active) {}
record BankAccount(UUID id, String owner, AccountType type, BigDecimal balance, boolean active) {}
record TransferRequest(@NotNull UUID senderAccountId, @NotNull UUID receiverAccountId,
                       @DecimalMin("0.01") BigDecimal amount, boolean additionalVerificationPassed) {}
record Transfer(UUID id, UUID senderAccountId, UUID receiverAccountId, BigDecimal amount, LocalDateTime completedAt) {}
enum AccountType { BASIC, STANDARD, PREMIUM }

@org.springframework.stereotype.Service
class BankTransferService {
    private static final BigDecimal VERIFICATION_THRESHOLD = new BigDecimal("1000.00");
    private static final Map<AccountType, BigDecimal> DAILY_LIMITS = Map.of(AccountType.BASIC, new BigDecimal("1000"), AccountType.STANDARD, new BigDecimal("5000"), AccountType.PREMIUM, new BigDecimal("20000"));
    private final Map<UUID, BankAccount> accounts = new LinkedHashMap<>();
    private final List<Transfer> transfers = new ArrayList<>();
    synchronized BankAccount createAccount(CreateAccountRequest r) {
        BankAccount account = new BankAccount(UUID.randomUUID(), r.owner(), r.type(), r.initialBalance(), r.active()); accounts.put(account.id(), account); return account;
    }
    synchronized Collection<BankAccount> accounts() { return List.copyOf(accounts.values()); }
    synchronized Transfer transfer(TransferRequest r) {
        if (r.senderAccountId().equals(r.receiverAccountId())) throw ApiException.badRequest("Sender and receiver must be different accounts");
        BankAccount sender = require(r.senderAccountId()), receiver = require(r.receiverAccountId());
        if (!sender.active() || !receiver.active()) throw ApiException.conflict("Both accounts must be active");
        if (sender.balance().compareTo(r.amount()) < 0) throw ApiException.conflict("Insufficient balance");
        BigDecimal sentToday = transfers.stream().filter(t -> t.senderAccountId().equals(sender.id()) && t.completedAt().toLocalDate().equals(LocalDate.now())).map(Transfer::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (sentToday.add(r.amount()).compareTo(DAILY_LIMITS.get(sender.type())) > 0) throw ApiException.conflict("Daily transfer limit exceeded");
        if (r.amount().compareTo(VERIFICATION_THRESHOLD) > 0 && !r.additionalVerificationPassed()) throw ApiException.badRequest("Additional verification is required for this amount");
        // Every validation happens before either balance is changed.
        accounts.put(sender.id(), new BankAccount(sender.id(), sender.owner(), sender.type(), sender.balance().subtract(r.amount()), true));
        accounts.put(receiver.id(), new BankAccount(receiver.id(), receiver.owner(), receiver.type(), receiver.balance().add(r.amount()), true));
        Transfer transfer = new Transfer(UUID.randomUUID(), sender.id(), receiver.id(), r.amount(), LocalDateTime.now()); transfers.add(transfer); return transfer;
    }
    private BankAccount require(UUID id) { return Optional.ofNullable(accounts.get(id)).orElseThrow(() -> ApiException.notFound("Account not found")); }
}
