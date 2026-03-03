import java.util.Scanner;

public class Student {
    private long id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        if (id > 0 && age > 7 && age < 30) {

            this.age = age;
            this.id=id;
            this.name=name;

            System.out.println("Student ID: "+id+" Name: "+name+" Age: "+age);





        }
        else {
            System.out.println("Invalid Input");
        }

    }
}
