public class AccountServiceImpl implements AccountService {

    private final EWalletService wallet;
    private final AccountValidationService validator;

    public AccountServiceImpl(EWalletService wallet) {
        if (wallet == null)
            throw new IllegalArgumentException("Wallet service cannot be null.");
        this.wallet = wallet;
        this.validator = new AccountValidationServiceImpl(wallet);
    }

    @Override
    public void signup(Account account) {
        if (account == null)
            throw new RuntimeException("Account cannot be null.");

        validator.validateUsername(account.getUsername().trim());
        validator.validatePassword(account.getPassword().trim());
        validator.validateAge(account.getAge());
        validator.validatePhone(account.getPhone().trim());

        wallet.getAccounts().add(account);
    }

    @Override
    public Account login(String username, String password) {
        if (username == null || password == null)
            throw new RuntimeException("Username and password are required.");

        String u = username.trim();
        String p = password.trim();

        return wallet.getAccounts().stream()
                .filter(a -> a.getUsername().equalsIgnoreCase(u)
                        && a.getPassword().equals(p))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid login."));
    }

    private Account find(String username) {
        if (username == null)
            throw new RuntimeException("Username is required.");

        String u = username.trim();

        return wallet.getAccounts().stream()
                .filter(a -> a.getUsername().equalsIgnoreCase(u))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found."));
    }

    @Override
    public void deposit(String username, double amount) {
        if (amount <= 0)
            throw new RuntimeException("Amount must be > 0.");

        Account acc = find(username);
        acc.setBalance(acc.getBalance() + amount);
    }

    @Override
    public void withdraw(String username, double amount) {
        if (amount <= 0)
            throw new RuntimeException("Amount must be > 0.");

        Account acc = find(username);

        if (acc.getBalance() < amount)
            throw new RuntimeException("Insufficient balance.");

        acc.setBalance(acc.getBalance() - amount);
    }

    @Override
    public void transfer(String from, String to, double amount) {
        if (from == null || to == null)
            throw new RuntimeException("Usernames are required.");

        if (from.trim().equalsIgnoreCase(to.trim()))
            throw new RuntimeException("Cannot transfer to yourself.");

        if (amount <= 0)
            throw new RuntimeException("Amount must be > 0.");

        Account src = find(from);
        Account dest = find(to);

        if (src.getBalance() < amount)
            throw new RuntimeException("Insufficient balance.");

        src.setBalance(src.getBalance() - amount);
        dest.setBalance(dest.getBalance() + amount);
    }

    @Override
    public void changePassword(String username, String oldPass, String newPass) {
        if (oldPass == null || newPass == null)
            throw new RuntimeException("Passwords are required.");

        Account acc = find(username);

        String oldP = oldPass.trim();
        String newP = newPass.trim();

        if (!acc.getPassword().equals(oldP))
            throw new RuntimeException("Wrong old password.");

        if (oldP.equals(newP))
            throw new RuntimeException("New password must be different.");

        validator.validatePassword(newP);
        acc.setPassword(newP);
    }

    @Override
    public Account getAccount(String username) {
        return find(username);
    }
}
