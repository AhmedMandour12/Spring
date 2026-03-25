class Post {
    private int id;
    private String text;
    private String image;

    // constructor 1 (id, text)
    Post(int id, String text) {
        this.id = id;
        this.text = text;
        this.image = null; // مفيش صورة
    }

    // constructor 2 (id, text, image)
    Post(int id, String text, String image) {
        this.id = id;
        this.text = text;
        this.image = image;
    }

    // getters فقط (اختياري لكن مفيد)
    int getId() {
        return id;
    }

    String getText() {
        return text;
    }

    String getImage() {
        return image;
    }

    void show() {
        System.out.println("ID: " + id);
        System.out.println("Text: " + text);
        if (image != null) {
            System.out.println("Image: " + image);
        }
        System.out.println("-----");
    }
}