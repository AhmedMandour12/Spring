import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        int x;
        x = scanner.nextInt();
        long y;
        y = scanner.nextLong();
        char z;
        z = scanner.next().charAt(0);
        float w;
        w = scanner.nextFloat();
        double d;
        d = scanner.nextDouble();

        System.out.println(x+"\n"+y+"\n"+z+"\n"+w+"\n"+d);

    }
}