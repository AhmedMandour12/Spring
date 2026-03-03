import java.util.Scanner;

public class Teacher {
    private Long id; // id > 0
    private String name; // size >=3 and all digits char(a-z)
    private float age; // age >= 25 and age <= 60
    private String phoneNumber; //  +20111390660 13 cher and start with +20
    private float salary;  // salary >= 3000

    public void setId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Teacher ID: ");
        id = sc.nextLong();

        if(id>0){
            this.id = id;
        }else {
            System.out.println("Invalid ID");
        }

    }

    public void setName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Teacher Name: ");
        name = sc.nextLine();
        if(name.length()>3&& name !=null ){
            this.name = name ;
        }else {
            System.out.println("Invalid Name");
        }
    }

    public void setAge() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Teacher Age: ");
        this.age = sc.nextFloat();
        if(age>=25&&age <=60){
            this.age = age ;
        }else {
            System.out.println("Invalid Age");
        }
    }

    public void setPhoneNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Teacher Phone Numbe: ");
        this.phoneNumber = sc.nextLine();

        if(phoneNumber.contains("+20")&&phoneNumber.length()==13){
            this.phoneNumber = phoneNumber;
        }else {
            System.out.println("Invalid Phone Number");
        }
    }

    public void setSalary() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Teacher Salary: ");
        this.salary = sc.nextFloat();
        if(salary>3000){
            this.salary =salary;
        }else {
            System.out.println("Invalid Salary");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public float getSalary() {
        return salary;
    }
}
