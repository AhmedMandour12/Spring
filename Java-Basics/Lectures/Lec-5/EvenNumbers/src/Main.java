import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        if(num<2){
            System.out.println(-1);

        }else{ for (int i=2; i<=num; i+=2) {
            System.out.println(i);
        }}


    }
}