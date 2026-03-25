class Controller {
    Restaurant restaurant;

    Controller(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    void add(Order o) {
        restaurant.addOrder(o);
    }

    void show() {
        restaurant.showOrders();
    }
}