//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
//Task1
    System.out.println("Welcome To Yor Application => Exception Task");

    Scanner scanner = new Scanner(System.in);
    System.out.print("Please enter num1 :");
    int num1= scanner.nextInt();
    System.out.print("Please enter num2 :");
    int num2= scanner.nextInt();

    try {
      int result= num1/num2;
        System.out.println("result : " +result);

    }
    catch (ArithmeticException e) {
        System.out.println("Division by zero is not allowed ");
    }

}
