import java.util.ArrayList;

class Course {
    String name;
    ArrayList<Student> students = new ArrayList<>();

    Course(String name) {
        this.name = name;
    }

    void addStudent(Student s) {
        students.add(s);
    }

    void showStudents() {
        System.out.println("Course: " + name);
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).name);
        }
    }
}