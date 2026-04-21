import java.util.HashSet;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // Level 1: equals
        Person p1 = new Person(1, "Ahmed");
        Person p2 = new Person(1, "Ali");

        System.out.println("Equals test:");
        System.out.println(p1.equals(p2)); // true

        //  Level 2: HashSet
        HashSet<Person> set = new HashSet<>();

        set.add(new Person(1, "A"));
        set.add(new Person(1, "B")); // duplicate (same id)
        set.add(new Person(2, "C"));

        System.out.println("\nHashSet size:");
        System.out.println(set.size()); // 2

        //  Level 3: HashMap
        HashMap<Person, String> map = new HashMap<>();

        map.put(new Person(1, "Ahmed"), "Employee");
        map.put(new Person(1, "Ali"), "Manager"); // replace

        System.out.println("\nHashMap size:");
        System.out.println(map.size()); // 1

        System.out.println("Retrieve:");
        System.out.println(map.get(new Person(1, "Test"))); // Manager

        //  Level 4: simple real example

        Product pr1 = new Product("P1", 100);
        Product pr2 = new Product("P1", 200);

        HashSet<Product> products = new HashSet<>();
        products.add(pr1);
        products.add(pr2);

        System.out.println("\nProducts size:");
        System.out.println(products.size()); // 1
    }
}