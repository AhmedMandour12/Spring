public class Main {
    public static void main(String[] args) {

        Person p = new Person("Ahmed");

        Order o1 = new Order();

        o1.addFood(new Food("Burger", 50));
        o1.addFood(new Food("Pizza", 80));

        o1.setBill(new Bill(130));
        o1.setGift(new Gift("Free Drink"));

        p.addOrder(o1);

        // عرض
        o1.show();
    }
}