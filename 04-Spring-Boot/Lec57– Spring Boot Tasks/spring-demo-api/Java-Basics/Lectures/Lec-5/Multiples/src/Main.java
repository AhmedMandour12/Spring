import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();
        if (A % B == 0 || B % A == 0) {
            System.out.println(("Multiples"));
        }else {
            System.out.println(("No Multiples"));
        }

    }
}