public class Main {
    public static void main(String[] args) {

        Student s1 = new Student("Ahmed");
        Student s2 = new Student("Ali");

        Course c1 = new Course("Java");
        Course c2 = new Course("Database");

        // ربط العلاقات
        s1.addCourse(c1);
        s1.addCourse(c2);

        s2.addCourse(c1);

        c1.addStudent(s1);
        c1.addStudent(s2);

        c2.addStudent(s1);

        // عرض
        s1.showCourses();
        c1.showStudents();
    }
}