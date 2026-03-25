import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        long result = 1;

        for (int j = 0; j < t; j++) {

            int n = sc.nextInt();


            for (int i = 1; i <= n; i++) {
                result = result * i;
            }

            System.out.println(result);
        }

        sc.close();
    }
}