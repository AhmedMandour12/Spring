//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Language english = new Language(1L, "English");
        Language german = new Language(2L, "German");

        Teacher teacher1 = new Teacher(1L, "Ahmed", 10000, english);
        Teacher teacher2 = new Teacher(2L, "Ali", 12000, english);
        Teacher teacher3 = new Teacher(3L, "Mohamed", 11000, german);

        english.getTeachers().add(teacher1);
        english.getTeachers().add(teacher2);

        german.getTeachers().add(teacher3);

        System.out.println(teacher1.getName() + " teaches " +
                teacher1.getLanguage().getName());

        System.out.println("\nTeachers of English Language:");

        for (Teacher teacher : english.getTeachers()) {
            System.out.println(teacher.getName());
        }

    }
}