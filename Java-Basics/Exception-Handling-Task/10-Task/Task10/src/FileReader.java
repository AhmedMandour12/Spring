import java.io.IOException;

class Main {
    public static void main(String[] args) {

        try {
            readFile();   // استدعاء الميثود اللي ممكن ترمي IOException

        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
        }

    }

    public static void readFile() throws IOException {
        throw new IOException("File not found or unable to read");
    }
}
