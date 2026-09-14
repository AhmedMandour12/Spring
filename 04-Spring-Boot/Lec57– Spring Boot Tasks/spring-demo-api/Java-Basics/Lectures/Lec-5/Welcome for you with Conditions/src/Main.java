import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Given two numbers A and B. Print "Yes" if A is greater than or equal to B. Otherwise print "No".
//
//                Input
//        Only one line containing two numbers A and B (0  ≤  A, B  ≤  100).
//
//                Output
//        Print "Yes" or "No" according to the statement.
        Scanner input = new Scanner(System.in);
        int A = input.nextInt();
        int B = input.nextInt();
        if ((A >= 0 && A <= 100) && (B >= 0 && B <= 100)) {
            if (A >= B) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

    }
}