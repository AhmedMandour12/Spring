//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            String value = null;
            String result = toUpperText(value);
            System.out.println(result);
        } catch (NullPointerException e) {
            System.out.println("Cannot call toUpperCase on null.");
        }

    }
    public static String toUpperText(String text) {
        return text.toUpperCase();
    }
}