public class Facebook extends Base {
    public Facebook(int id, String text, String image) {
        super(id, text, image);

    }

    @Override
    public void show() {
        System.out.println("you are on application facebook");
        System.out.println("id: " + getId());
        System.out.println("text: " + getText());
        System.out.println("image: " + getImage());
    }
}
