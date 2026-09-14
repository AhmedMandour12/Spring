import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type scenario (arith or null): ");
        String scenario = scanner.nextLine();

        try {
            if ("arith".equalsIgnoreCase(scenario)) {
                int result = 10 / 0;
                System.out.println(result);
            } else {
                String text = null;
                System.out.println(text.length());
            }
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException handled: divide by zero.");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException handled: null value used.");
        }
    }
}