import java.util.*;

class Product {
    String code;
    double price;

    Product(String code, double price) {
        this.code = code;
        this.price = price;
    }
}

class Student {
    int id;
    String email;

    Student(int id, String email) {
        this.id = id;
        this.email = email;
    }
}

class Car {
    String plateNumber;
    String color;

    Car(String plateNumber, String color) {
        this.plateNumber = plateNumber;
        this.color = color;
    }
}

public class Main {
    public static void main(String[] args) {

        Set<Product> products = new HashSet<>();
        products.add(new Product("P1", 100));
        products.add(new Product("P1", 200));

        System.out.println(products.size());

        Set<Student> students = new HashSet<>();
        students.add(new Student(1, "a@mail.com"));
        students.add(new Student(2, "a@mail.com"));

        System.out.println(students.size());

        Map<Car, String> cars = new HashMap<>();
        cars.put(new Car("123", "Red"), "Owner1");
        cars.put(new Car("123", "Blue"), "Owner2");

        System.out.println(cars.size());
    }
}