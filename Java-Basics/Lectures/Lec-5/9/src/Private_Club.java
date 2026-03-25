import java.util.ArrayList;

class PrivateClub {
    String name;
    String code;
    ArrayList<Member> members = new ArrayList<>();

    PrivateClub(String name, String code) {
        this.name = name;
        this.code = code;
    }

    void addMember(Member m, String c) {
        if (code.equals(c)) {
            members.add(m);
        } else {
            System.out.println("Wrong code");
        }
    }

    void show() {
        System.out.println("Private Club: " + name);
        for (int i = 0; i < members.size(); i++) {
            System.out.println(members.get(i).name);
        }
    }
}