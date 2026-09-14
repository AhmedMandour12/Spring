//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            try {
                int result = 10 / 0;
                System.out.println(result);
            } catch (ArithmeticException e) {
                System.out.println("Inner catch handled then rethrows.");
                throw e;
            }
        } catch (ArithmeticException e) {
            System.out.println("Outer catch handled ArithmeticException.");
        }
    }
}