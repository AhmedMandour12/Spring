import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //input-> get two integer A,B
        //process:
        //        1-for each from A->B and define temp=i
        //        2-define digit
        //        3-isLucky=true
        //        4-while(temp>0) : digit=temp%10
        //          if(digit!=4||digit!=7):isLucky=true
        //         5-    break;
        //         6- temp/=10

        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();

        boolean found = false;

        for (int i = A; i <= B; i++) {

            int temp = i;
            boolean isLucky = true;

            while (temp > 0) {

                int digit = temp % 10;

                if (digit != 4 && digit != 7) {
                    isLucky = false;
                    break;
                }

                temp = temp / 10;
            }

            if (isLucky) {
                System.out.print(i + " ");
                found = true;
            }
        }

        if (!found) {
            System.out.print(-1);
        }
    }
}