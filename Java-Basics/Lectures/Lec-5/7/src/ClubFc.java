public class ClubFc extends BaseEntity{
    private String Fcode;
    public ClubFc(long id,String name,String number,String Fcode) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.Fcode = Fcode;
        System.out.println("ClubFc created,ID: "+id+" Name: "+name+" Number: "+number+" Fcode: "+Fcode);

    }

}
