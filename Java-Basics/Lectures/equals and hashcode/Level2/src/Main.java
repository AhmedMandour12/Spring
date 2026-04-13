import java.util.HashSet;
import java.util.Set;

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

        Set<Person> set = new HashSet<>();

        set.add(new Person(1, "A"));
        set.add(new Person(1, "A"));

        System.out.println(set.size());

        System.out.println("----------");

        Set<Person> set2 = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            set2.add(new Person(i % 3, "Name" + i));
        }

        System.out.println(set2.size());
    }
}