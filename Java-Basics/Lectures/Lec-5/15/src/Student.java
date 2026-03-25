import java.util.ArrayList;

class Student {
    String name;
    ArrayList<Course> courses = new ArrayList<>();

    Student(String name) {
        this.name = name;
    }

    void addCourse(Course c) {
        courses.add(c);
    }

    void showCourses() {
        System.out.println("Student: " + name);
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).name);
        }
    }
}