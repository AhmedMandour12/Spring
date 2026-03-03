import java.util.Scanner;

public class Player {
    private long id;
    private String name;
    private String number;

    Player(int id, String name, String number) {


        if (id>0 && number.length()<=2 ) {
            this.id = id;
            this.name = name;
            this.number = number;
            System.out.println( "Palyer ID:"+id+" Name:"+name+" Number:"+number);
        }
        else  {
            System.out.println("Player Invalid Input");
        }

    }
}
