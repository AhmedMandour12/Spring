import java.util.Objects;

public class Student {

    int id;
    String email;

    Student(int id, String email) {
        this.id = id;
        this.email = email;
    }

    // equality based on email
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Student))
            return false;

        Student s = (Student) obj;

        return this.email.equals(s.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}