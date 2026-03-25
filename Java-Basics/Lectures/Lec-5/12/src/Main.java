public class Main {
    public static void main(String[] args) {

        Post p1 = new Post(1, "Hello World");
        Post p2 = new Post(2, "My Photo", "image.png");

        p1.show();
        p2.show();
    }
}