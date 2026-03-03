import java.util.Scanner;

public class Player {
    private String name;
    private int number;

    public void setName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        name = input.nextLine();


//       - make sure that name size > 3

        if (name!=null&&name.length() >3 ) {
            System.out.print("Please enter your number: ");
            this.name = name;
        } else {
            System.out.print("Invalid name");
        }

    }

    public void setNumber() {
        Scanner input = new Scanner(System.in);
        number = input.nextInt();

//        - make sure that number is > 0
        if (number > 0) {
            this.number = number;
        } else {
            System.out.println("Invalid number");
        }

    }
    public String getName() {

        return name;
    }

    public int getNumber() {

        return number;

    }



}
