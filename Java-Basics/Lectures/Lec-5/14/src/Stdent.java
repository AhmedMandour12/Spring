import java.util.ArrayList;

class Student {
    String name;
    ArrayList<Email> emails = new ArrayList<>();

    Student(String name) {
        this.name = name;
    }

    void addEmail(Email e) {
        emails.add(e);
    }

    void show() {
        System.out.println("Student: " + name);
        System.out.println("Emails:");

        for (int i = 0; i < emails.size(); i++) {
            emails.get(i).show();
        }
    }
}