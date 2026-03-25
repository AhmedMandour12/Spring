import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int even = 0;
        int odd = 0;
        int positive = 0;
        int negative = 0;
        for (int i = 1; i <= n; i++) {
            int x = input.nextInt();
            if (x % 2 == 0) {
                even++;
            } else {
                odd++;
            }
            if (x > 0) {
                positive++;
            } else if (x < 0) {
                negative++;
            }


        }
        System.out.println("Even: " + even);
        System.out.println("Odd: " + odd);
        System.out.println("Positive: " + positive);
        System.out.println("Negative: " + negative);

    }
}

