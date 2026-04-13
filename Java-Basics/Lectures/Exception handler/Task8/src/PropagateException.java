public class PropagateException {
    public static void methodThatThrows() {
        throw new RuntimeException("Exception from methodThatThrows.");
    }

    public static void methodThatCalls() {
        methodThatThrows();
    }
}