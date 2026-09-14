import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //X= (A*B)-(C*D)
        Scanner input = new Scanner(System.in);
        long A=input.nextLong();
        long B=input.nextLong();
        long C=input.nextLong();
        long D=input.nextLong();
        long X =(A*B)-(C*D);

        System.out.println("Difference = "+X);



    }
}