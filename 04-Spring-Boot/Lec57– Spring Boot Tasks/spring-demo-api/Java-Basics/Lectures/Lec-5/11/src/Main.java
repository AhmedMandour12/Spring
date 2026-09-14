public class Main {
    public static void main(String[] args) {

        Restaurant r = new MyRestaurant();
        Controller c = new Controller(r);

        c.add(new Order("Pizza"));
        c.add(new Order("Burger"));

        c.show();
    }
}