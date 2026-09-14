//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//
//        public class Teacher {
//        private Long id; // id > 0
//        private String name; // size >=3 and all digits char(a-z)
//        private float age; // age >= 25 and age <= 60
//        private String phoneNumber; //  +20111390660 13 cher and start with +20
//        private float salary;  // salary >= 3000
        Teacher teacher = new Teacher();

        teacher.setId();
        teacher.setAge();
        teacher.setName();
        teacher.setPhoneNumber();
        teacher.setSalary();

        System.out.println(teacher.getId());
        System.out.println(teacher.getName());
        System.out.println(teacher.getAge());
        System.out.println(teacher.getPhoneNumber());
        System.out.println(teacher.getSalary());





    }
}