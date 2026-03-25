import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //input-> n
        //process-> 1- iterate from i=1 -> n
        //          2- check if i divides n(n % i == 0 )
        //          3- if yes it's divisor
        //output-> print all divisors n


        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int count = 0;
        for(int i =1; i<=n; i++){
            if(n%i==0){
                System.out.println(i);

            }

        }
        input.close();



    }

}