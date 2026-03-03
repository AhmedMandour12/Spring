import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        long x=input.nextInt();
        long y=input.nextInt();
        long sum = x + y;
        long mul = x * y;
        long sub = x - y;

        System.out.printf("%d + %d = %d%n", x, y, sum);
        System.out.printf("%d * %d = %d%n", x, y, mul);
        System.out.printf("%d - %d = %d%n", x, y, sub);

    }
}