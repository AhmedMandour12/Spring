public class ClubRel extends BaseEntity {
    private String Rcode;
    public ClubRel(long id, String name, String number, String Rcode ) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.Rcode = Rcode;
        System.out.println("ClubRel created,ID: "+id+" Name: "+name+" Number: "+number);

    }

}
