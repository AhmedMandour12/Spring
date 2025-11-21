public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("Inside try block");
            int x = 10 / 0;   
        }
        catch (ArithmeticException e) {
            System.out.println("Inside catch block: " + e.getMessage());
        }
        finally {
            System.out.println("finally block \"Always Executes\"");
        }


    }
}
