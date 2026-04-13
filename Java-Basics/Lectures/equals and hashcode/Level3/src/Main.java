import java.util.HashMap;
import java.util.Map;

class Person {
    int id;
    String name;

    Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {

        Map<Person, String> map = new HashMap<>();

        map.put(new Person(1, "A"), "Employee");
        map.put(new Person(1, "B"), "Manager");

        System.out.println(map.size());

        Person key = new Person(1, "X");
        System.out.println(map.get(key));

        Person p = new Person(2, "Ali");
        map.put(p, "Test");

        p.id = 5;

        System.out.println(map.get(p));
    }
}