//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {

            PropagateException.methodThatCalls();
        } catch (RuntimeException e) {
            System.out.println("Caught in main: " + e.getMessage());
        }

    }
}