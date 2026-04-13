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

        Person p1 = new Person(1, "Ahmed");
        Person p2 = new Person(1, "Ahmed");

        System.out.println(p1.equals(p2));

        System.out.println("----------");

        Person p3 = new Person(1, "A");
        Person p4 = new Person(1, "B");
        Person p5 = new Person(1, "C");

        System.out.println(p3.equals(p4));
        System.out.println(p4.equals(p5));
    }
}