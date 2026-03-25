public class Main {
    public static void main(String[] args) {

        Shirt s1 = new Shirt("Red", 10);
        Player p1 = new Player("Ahmed", s1);

        p1.show();
    }
}