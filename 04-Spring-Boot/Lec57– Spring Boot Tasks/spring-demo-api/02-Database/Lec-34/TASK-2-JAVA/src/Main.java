//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Doctor doctor1 = new Doctor(1L, "Ahmed", 10000);
        Doctor doctor2 = new Doctor(2L, "Ali", 12000);

        Patient patient1 = new Patient(1L, "Mohamed", 20);
        Patient patient2 = new Patient(2L, "Omar", 25);

        doctor1.getPatients().add(patient1);
        doctor1.getPatients().add(patient2);

        patient1.getDoctors().add(doctor1);
        patient1.getDoctors().add(doctor2);

    }
}