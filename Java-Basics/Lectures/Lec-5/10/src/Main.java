//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Please Create App school with
//        base Impl (add student , show student) ---> can't create object
//        another impl contain same base impl but create new add student to print on it that you are on add student
//        another impl contain same base impl but create new show student to print on it that you are on show student
//        thank !


        Student s1 = new Student("Ahmed");
        Student s2 = new Student("Ali");

        AddSchool addSchool = new AddSchool();
        addSchool.addStudent(s1);
        addSchool.addStudent(s2);

        ShowSchool showSchool = new ShowSchool();
        showSchool.addStudent(s1);
        showSchool.addStudent(s2);

        showSchool.showStudents();
    }
}

