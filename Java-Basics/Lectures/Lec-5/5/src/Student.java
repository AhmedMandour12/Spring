public class Student extends ShareDataPhone {
    private int age;
    public Student(long id,String name,int age,String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        System.out.println("Student ID: "+id+" Name: "+name+" Age: "+age+" Phone: "+phone);
    }
}
