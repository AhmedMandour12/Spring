//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
//    Write a program that may throw both NullPointerException and ArithmeticException. Use multiple catch blocks.
    try {
        String string =null;
        System.out.println(string.length());
        int x=10;
        int y=0;
        int result=x/y;
        System.out.println(result);

    }catch(NullPointerException npe) {
        System.out.println("NullPointerException");
    }catch(ArithmeticException ae) {
        System.out.println("ArithmeticException");
    }
}
