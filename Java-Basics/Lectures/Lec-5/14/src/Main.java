public class Main {
    public static void main(String[] args) {

        Student s = new Student("Ahmed");

        s.addEmail(new Email("ahmed@gmail.com"));
        s.addEmail(new Email("ahmed@university.com"));

        s.show();
    }
}