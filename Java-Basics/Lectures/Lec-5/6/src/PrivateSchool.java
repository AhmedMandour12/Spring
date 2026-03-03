public class PrivateSchool extends BaseEntity {
    public  PrivateSchool(long id,String name) {
        this.id = id;
        this.name = name;
        System.out.println("Private School Created, ID: "+id+", Name: "+name);
    }
}
