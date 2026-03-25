import java.util.ArrayList;

abstract class BaseSchool {
    ArrayList<Student> students = new ArrayList<>();

    void addStudent(Student s) {
        students.add(s);
    }

    void showStudents() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).name);
        }
    }
}