class Player {
    String name;
    Shirt shirt; // العلاقة هنا

    Player(String name, Shirt shirt) {
        this.name = name;
        this.shirt = shirt;
    }

    void show() {
        System.out.println("Player: " + name);
        shirt.show(); // استخدام العلاقة
    }
}