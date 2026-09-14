public class Main {
    public static void main(String[] args) {

        Member m1 = new Member("Ahmed");
        Member m2 = new Member("Ali");

        PublicClub c1 = new PublicClub("Open Club");
        c1.addMember(m1);

        PrivateClub c2 = new PrivateClub("VIP Club", "1234");
        c2.addMember(m2, "1234");
        c2.addMember(new Member("Omar"), "0000");

        c1.show();
        c2.show();
    }
}