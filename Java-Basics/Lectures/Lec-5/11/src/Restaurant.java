import java.util.ArrayList;

abstract class Restaurant {
    ArrayList<Order> orders = new ArrayList<>();

    // فيها implementation
    void addOrder(Order o) {
        orders.add(o);
    }

    // بدون implementation
    abstract void showOrders();
}