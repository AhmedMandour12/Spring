import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = findMax(sc, n);
        System.out.println(max);


    }

    public static int findMax(Scanner sc, int n) {
        int max = sc.nextInt();
        for (int i = 1; i < n; i++) {

            int x = sc.nextInt();
            if (x > max) {
                max = x;
            }

        }
        return max;
    }

}