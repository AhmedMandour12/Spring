//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //Task3
    System.out.println("Welcome");

        convertToUpper(null);
}
public static void convertToUpper(String text) {
    try {
        String result = text.toUpperCase();
        System.out.println("Uppercase: " + result);

    }
    catch (NullPointerException e) {
        System.out.println("NullPointerException: text is null.");
    }
}