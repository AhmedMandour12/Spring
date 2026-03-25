public class Linkedin extends Base {
    public Linkedin(int id, String text, String image) {

        super(id, text, image);

    }

    @Override
    public void show() {
        System.out.println("you are on application linkedin");


        System.out.println("id: " + getId());
        System.out.println("text: " + getText());
        System.out.println("image: " + getImage());
    }
}

