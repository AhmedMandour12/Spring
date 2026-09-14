import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
//        Please Create App that accept id , name and age of Student and print them (with oop)


        Scanner sc = new Scanner(System.in);
        Employee emp = new Employee();
        System.out.print("Please enter your ID: ");
        emp.id = sc.nextInt();
        System.out.print("Please enter your Name: ");
        emp.name = sc.next();
        System.out.print("Please enter your age: ");
        emp.age = sc.nextInt();
        System.out.println("----------------------------------------------");
        System.out.println("ID:" + emp.id + "\n" + " Name:" + emp.name + "\n" + " Age:" + emp.age);
    }
}