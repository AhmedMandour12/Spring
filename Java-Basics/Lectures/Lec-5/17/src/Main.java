import Pack1.Student;
import Pack2.Teacher;

public class Main {
    public static void main(String[] args) {

        Student s = new Student(1, "Ahmed", "A");
        Teacher t = new Teacher(2, "Ali", true);


        System.out.println(s.id);
        System.out.println(s.name);

        System.out.println(t.id);
        System.out.println(t.name);


    }
}