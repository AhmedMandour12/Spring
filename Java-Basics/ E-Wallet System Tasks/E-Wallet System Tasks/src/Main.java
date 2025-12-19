import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EWalletService wallet = new EWalletServiceImpl();
        AccountService accountService = new AccountServiceImpl(wallet);

        while (true) {
            System.out.println("1- Signup | 2- Login | 0- Exit");
            int ch = sc.nextInt();
            sc.nextLine(); // consume newline

            if (ch == 0) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                // ===== Signup =====
                if (ch == 1) {
                    System.out.print("Username: ");
                    String username = sc.nextLine();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    System.out.print("Phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    accountService.signup(new Account(username, password, phone, age));
                    System.out.println("Signup successful.");
                }

                // ===== Login =====
                if (ch == 2) {
                    System.out.print("Username: ");
                    String u = sc.nextLine();

                    System.out.print("Password: ");
                    String p = sc.nextLine();

                    Account acc = accountService.login(u, p);
                    System.out.println("Login successful.");

                    // ===== After Login Menu =====
                    while (true) {
                        System.out.println("""
                                1- Deposit
                                2- Withdraw
                                3- Transfer
                                4- Show Details
                                5- Change Password
                                0- Logout
                                """);

                        int c = sc.nextInt();
                        sc.nextLine();

                        if (c == 0) {
                            System.out.println("Logged out.");
                            break;
                        }

                        try {
                            switch (c) {
                                case 1 -> {
                                    System.out.print("Amount: ");
                                    double amt = sc.nextDouble();
                                    sc.nextLine();
                                    accountService.deposit(acc.getUsername(), amt);
                                    System.out.println("Deposit done.");
                                }
                                case 2 -> {
                                    System.out.print("Amount: ");
                                    double amt = sc.nextDouble();
                                    sc.nextLine();
                                    accountService.withdraw(acc.getUsername(), amt);
                                    System.out.println("Withdraw done.");
                                }
                                case 3 -> {
                                    System.out.print("To username: ");
                                    String to = sc.nextLine();
                                    System.out.print("Amount: ");
                                    double amt = sc.nextDouble();
                                    sc.nextLine();
                                    accountService.transfer(acc.getUsername(), to, amt);
                                    System.out.println("Transfer done.");
                                }
                                case 4 -> {
                                    Account a = accountService.getAccount(acc.getUsername());
                                    System.out.println(
                                            a.getUsername() + " | " +
                                                    a.getPhone() + " | " +
                                                    a.getAge() + " | Balance = " +
                                                    a.getBalance()
                                    );
                                }
                                case 5 -> {
                                    System.out.print("Old password: ");
                                    String o = sc.nextLine();
                                    System.out.print("New password: ");
                                    String n = sc.nextLine();
                                    accountService.changePassword(acc.getUsername(), o, n);
                                    System.out.println("Password changed.");
                                }
                                default -> System.out.println("Invalid choice.");
                            }
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
