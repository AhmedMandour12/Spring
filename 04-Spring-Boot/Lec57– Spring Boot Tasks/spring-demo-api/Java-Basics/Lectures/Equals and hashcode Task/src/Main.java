import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        // =========================================
        // LEVEL 1
        // =========================================

        Person p1 = new Person(1, "Ahmed");
        Person p2 = new Person(1, "Ali");

        System.out.println("Equals:");
        System.out.println(p1.equals(p2));

        // =========================================
        // LEVEL 2 - HASHSET
        // =========================================

        HashSet<Person> set = new HashSet<>();

        set.add(new Person(1, "A"));
        set.add(new Person(1, "B"));
        set.add(new Person(2, "C"));
        set.add(new Person(3, "D"));

        System.out.println("\nHashSet size:");
        System.out.println(set.size());

        // =========================================
        // LEVEL 3 - HASHMAP
        // =========================================

        HashMap<Person, String> map = new HashMap<>();

        map.put(new Person(1, "Ahmed"), "Employee");

        // same id -> replace value
        map.put(new Person(1, "Ali"), "Manager");

        System.out.println("\nHashMap size:");
        System.out.println(map.size());

        System.out.println("\nRetrieve:");
        System.out.println(
                map.get(new Person(1, "Test"))
        );

        // =========================================
        // MODIFY KEY PROBLEM
        // =========================================

        Person key = new Person(100, "Mohamed");

        HashMap<Person, String> testMap =
                new HashMap<>();

        testMap.put(key, "Developer");

        System.out.println("\nBefore change:");
        System.out.println(testMap.get(key));

        key.id = 200;

        System.out.println("\nAfter change:");
        System.out.println(testMap.get(key));

        // =========================================
        // PRODUCT
        // =========================================

        Product pr1 = new Product("P1", 100);
        Product pr2 = new Product("P1", 200);

        HashSet<Product> products =
                new HashSet<>();

        products.add(pr1);
        products.add(pr2);

        System.out.println("\nProducts size:");
        System.out.println(products.size());

        // =========================================
        // STUDENT
        // =========================================

        Student s1 =
                new Student(1, "same@test.com");

        Student s2 =
                new Student(2, "same@test.com");

        System.out.println("\nStudents equal:");
        System.out.println(s1.equals(s2));

        // =========================================
        // CAR
        // =========================================

        HashMap<Car, String> cars =
                new HashMap<>();

        cars.put(
                new Car("ABC123", "Red"),
                "Owner 1"
        );

        cars.put(
                new Car("ABC123", "Blue"),
                "Owner 2"
        );

        System.out.println("\nCars map size:");
        System.out.println(cars.size());
    }
}