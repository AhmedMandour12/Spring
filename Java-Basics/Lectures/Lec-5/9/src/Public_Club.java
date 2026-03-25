import java.util.ArrayList;

class PublicClub {
    String name;
    ArrayList<Member> members = new ArrayList<>();

    PublicClub(String name) {
        this.name = name;
    }

    void addMember(Member m) {
        members.add(m);
    }

    void show() {
        System.out.println("Public Club: " + name);
        for (int i = 0; i < members.size(); i++) {
            System.out.println(members.get(i).name);
        }
    }
}