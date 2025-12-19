

public interface AccountService {

    void signup(Account account);

    Account login(String username, String password);

    void deposit(String username, double amount);

    void withdraw(String username, double amount);

    void transfer(String from, String to, double amount);

    void changePassword(String username, String oldPass, String newPass);

    Account getAccount(String username);
}
