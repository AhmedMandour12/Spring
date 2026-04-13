import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String path = inputScanner.nextLine();

        try (Scanner fileScanner = new Scanner(new File(path))) {
            System.out.println("File opened successfully. First lines:");
            int count = 0;
            while (fileScanner.hasNextLine() && count < 5) {
                System.out.println(fileScanner.nextLine());
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}