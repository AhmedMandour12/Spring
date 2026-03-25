package Pack2;

public class Teacher {
    public int id;
    public String name;
    boolean active; // default

    public Teacher(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }
}