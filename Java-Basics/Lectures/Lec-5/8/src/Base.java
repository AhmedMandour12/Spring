abstract public class Base {
    private int id;
    private String text;
    private String image;

    public Base(int id, String text, String image) {
        this.id = id;
        this.text = text;
        this.image = image;

    }
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

   abstract public void show();

}