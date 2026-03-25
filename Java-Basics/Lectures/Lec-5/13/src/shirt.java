class Shirt {
    String color;
    int number;

    Shirt(String color, int number) {
        this.color = color;
        this.number = number;
    }

    void show() {
        System.out.println("Shirt Color: " + color);
        System.out.println("Number: " + number);
    }
}