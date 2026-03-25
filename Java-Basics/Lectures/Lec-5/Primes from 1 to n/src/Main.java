import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        for (int j = 2; j <= n; j++) {

            boolean isPrime = true; // reset لكل رقم

            for (int i = 2; i < j; i++) {
                if (j % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.print(j + " ");
            }
        }

        input.close();
    }
}