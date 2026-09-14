import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String path = scanner.nextLine();

        try {
            ThrowsKeyword.readFile(path);
        } catch (IOException e) {
            System.out.println("IOException handled: " + e.getMessage());
        }
    }
}