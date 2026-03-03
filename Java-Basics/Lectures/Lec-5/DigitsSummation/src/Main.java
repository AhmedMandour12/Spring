import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Given two numbers N and M. Print the summation of their last digits.n>=0,m<10^18
        Scanner input = new Scanner(System.in);

        long n=input.nextLong();
        long m=input.nextLong();
        long lastn=n%10;
        long lastm=m%10;
        System.out.println(lastn+lastm);

        }

    }
