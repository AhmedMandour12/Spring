import java.util.Scanner;

public class AddNums {
    private int num1,num2,num3;
    public AddNums() {
        System.out.println("Pls Enter Even Numbers");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter num1: ");
        num1 = sc.nextInt();
        System.out.print("Enter num2: ");
        num2 = sc.nextInt();
        System.out.print("Enter nm3: ");
        num3 = sc.nextInt();
        if (num1 %2==0 && num2 %2==0 && num3 %2==0) {
            this.num1 = num1 + num2 + num3;
            System.out.println("Result of Add Nums is :" + (num1+num2+num3));


        }
        else {
            System.out.println("Invalid input, please try again And enter even numbers :)");
        }

    }

}
