import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double R = input.nextDouble();
        final double PI = 3.141592653;
        double Area = PI * Math.pow(R, 2);
        System.out.println(Area);



    }
}