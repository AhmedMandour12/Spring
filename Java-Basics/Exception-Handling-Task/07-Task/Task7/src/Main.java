//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws InvalidAgeException {

//    Create a custom exception InvalidAgeException. If user age < 18, throw the exception.

    try {
        cheakAgeException(5);
        System.out.println("Accepted Age");
    } catch (InvalidAgeException e) {
        System.out.println(e.getMessage());
    }


}

public static void cheakAgeException(int age) throws InvalidAgeException {
    if (age < 18) {
        throw new InvalidAgeException("Your age < 18");
    }

}

