public class Account {

    private String username;
    private String password;
    private String phone;
    private int age;
    private double balance;

    public Account(String username, String password, String phone, int age) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.age = age;
        this.balance = 0.0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
