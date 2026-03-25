import java.util.ArrayList;

class Order {
    ArrayList<Food> foods = new ArrayList<>();
    Bill bill;
    Gift gift;

    void addFood(Food f) {
        foods.add(f);
    }

    void setBill(Bill b) {
        this.bill = b;
    }

    void setGift(Gift g) {
        this.gift = g;
    }

    void show() {
        System.out.println("Foods:");
        for (int i = 0; i < foods.size(); i++) {
            System.out.println(foods.get(i).name + " - " + foods.get(i).price);
        }

        if (bill != null) {
            bill.show();
        }

        if (gift != null) {
            System.out.println("Gift: " + gift.name);
        }
    }
}