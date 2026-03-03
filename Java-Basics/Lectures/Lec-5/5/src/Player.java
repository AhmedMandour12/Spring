public class Player extends ShareDataPhone{
    private int number;
    public Player(long id,String name,int number,String phone) {

        this.number = number;
        this.name = name;
        this.phone = phone;
        this.id=id;
        System.out.println("Player ID: "+id+" Name: "+name+" Phone: "+phone+" Number: "+number);
    }
}
