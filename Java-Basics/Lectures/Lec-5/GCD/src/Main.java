import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // input-> 2 integer A and B

        // define gcd variable
        //process-> Get the minimum of the two numbers:
        // min = Math.min(A, B)
        // iterate from i=1 ->min
        //        If i divides both A and B:
        //        A % i == 0 && B % i == 0
        //        Then update:
        //        gcd = i

        //output print largest divisor

        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int gcd=0;

        int min=Math.min(A,B);
        for(int i=1;i<=min;i++){
            if (A%i==0&&B%i==0){
               gcd = i;
            }
        }
        System.out.println(gcd);

    }
}