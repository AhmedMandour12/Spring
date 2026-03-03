import java.sql.SQLOutput;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Given 2 numbers A
//        and B
//. Print floor, ceil and round of A/B
//
//        Note:
//
//        Floor: Is a mathematical function that takes a real number X
//        and its output is the greatest integer less than or equal to X
//                .
//                Ceil: Is a mathematical function that takes a real number X
//        and its output is the smallest integer larger than or equal to X
//                .
//                Round: Is a mathematical function that takes a real number X
//        and its output is the closest integer to that number X

        Scanner input = new Scanner(System.in);

        double X = input.nextDouble();
        double Y = input.nextDouble();
        if (X <= 0 || Y <= 0) {
            System.out.println("Invalid Number");
            return;

        }
        double Z = X / Y;
        int integerPart = (int)Z;
        int floor = integerPart;

        int ceil;

        if (Z == integerPart) {
            ceil = integerPart;

        } else {
            ceil = integerPart + 1;
        }


        double fraction = Z - integerPart;
        int round = 0;

        int A=(int)X;
        int B=(int)Y;


        System.out.println("floor " + A + " / " + B + " = " + floor);
        System.out.println("ceil " + A + " / " + B + " = " + ceil);


        if (fraction >= .5) {
            round = integerPart + 1;
            System.out.println("round " + A + " / " + B + " = " + round);


        } else if (fraction < .5) {
            round = integerPart;
            System.out.println("round " + A + " / " + B + " = " + round);
        }
    }
}