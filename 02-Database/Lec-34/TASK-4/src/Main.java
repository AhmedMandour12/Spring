//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Ahmed");
        employee.setAge(20);

        Phone phone = new Phone();
        phone.setId(1L);
        phone.setPhoneNumber("01012345678");

        employee.setPhone(phone);
        phone.setEmployee(employee);

        System.out.println(employee.getName());
        System.out.println(employee.getPhone().getPhoneNumber());

        System.out.println(phone.getEmployee().getName());
    }
}