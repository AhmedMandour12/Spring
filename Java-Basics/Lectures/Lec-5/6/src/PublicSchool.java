import java.sql.SQLOutput;

public class PublicSchool extends BaseEntity {
    PublicSchool(long id, String name) {
        this.id=id;
        this.name=name;
        System.out.println("Public School Created, ID: "+id+", Name: "+name);


    }
}
