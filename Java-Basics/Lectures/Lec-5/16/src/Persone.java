import java.util.ArrayList;

class Person {
    String name;
    ArrayList<Order> orders = new ArrayList<>();

    Person(String name) {
        this.name = name;
    }

    void addOrder(Order o) {
        orders.add(o);
    }
}