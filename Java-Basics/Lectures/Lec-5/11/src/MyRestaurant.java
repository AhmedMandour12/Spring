class MyRestaurant extends Restaurant {

    @Override
    void showOrders() {
        System.out.println("Orders:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).name);
        }
    }
}