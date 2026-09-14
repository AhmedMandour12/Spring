import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Given a number N Print the summation of the numbers that is between 1 and N
        Scanner input = new Scanner(System.in);
        long n=input.nextLong();
        long sum=n*(n+1)/2;
        System.out.println(sum);








    }
}