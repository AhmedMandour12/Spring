import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter index (0-4): ");
            int index = scanner.nextInt();
            System.out.println("Value = " + numbers[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index is out of bounds.");
        }
    }
}