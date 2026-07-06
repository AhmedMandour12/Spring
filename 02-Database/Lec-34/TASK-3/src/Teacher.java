public class Teacher {

    private Long id;
    private String name;
    private double salary;

    private Language language;

    public Teacher() {
    }

    public Teacher(Long id, String name, double salary, Language language) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}