import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter first integer: ");
            int first = scanner.nextInt();

            System.out.print("Enter second integer: ");
            int second = scanner.nextInt();

            int result = first / second;
            System.out.println("Result = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero.");
        }

    }
}