

public class AccountValidationServiceImpl implements AccountValidationService {

    private EWalletService wallet;

    public AccountValidationServiceImpl(EWalletService wallet) {
        this.wallet = wallet;
    }

    @Override
    public void validateUsername(String username) {
        if (username == null)
            throw new RuntimeException("Invalid username.");

        username = username.trim();

        if (username.length() < 3 || !Character.isUpperCase(username.charAt(0)))
            throw new RuntimeException("Invalid username.");

        String finalUsername = username;
        boolean exists = wallet.getAccounts().stream()
                .anyMatch(a -> a.getUsername().equalsIgnoreCase(finalUsername));

        if (exists)
            throw new RuntimeException("Username already exists.");
    }




    @Override
    public void validatePassword(String password) {
        if (password == null || password.length() < 4)
            throw new RuntimeException("Weak password.");
        password = password.trim();

    }

    @Override
    public void validateAge(int age) {
        if (age < 18)
            throw new RuntimeException("Age must be >= 18.");
    }

    @Override
    public void validatePhone(String phone) {
        if (!phone.matches("^01[0-2,5]\\d{8}$"))
            throw new RuntimeException("Invalid phone.");

        boolean exists = wallet.getAccounts().stream()
                .anyMatch(a -> a.getPhone().equals(phone));

        if (exists)
            throw new RuntimeException("Phone already exists.");
    }
}
