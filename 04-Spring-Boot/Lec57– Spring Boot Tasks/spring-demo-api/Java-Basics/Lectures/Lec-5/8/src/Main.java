//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Please Create App facebook & LinkedIn api for posts that accept (id,text,image) and show them
//        Look!
//                on facebook please print that you are on application facebook
//        on LinkedIn please print that you are on application LinkedIn
        Facebook F=new Facebook(15,"facebook","page");
        F.show();

        System.out.println("----------------------------------------");

        Linkedin L =new Linkedin(20,"Linkedin","page1");
        L.show();

    }
}