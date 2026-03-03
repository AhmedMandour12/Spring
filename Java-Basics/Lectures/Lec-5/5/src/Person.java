public class Person extends BaseEntity {
    public Person(long id, String name) {
        this.id=id;
        this.name=name;
        System.out.println("Person ID: "+id+" Name: "+name);
    }
}
