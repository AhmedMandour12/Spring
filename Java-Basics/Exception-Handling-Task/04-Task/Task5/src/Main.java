//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {


    File file=new File("C:\\Users\\mando\\my files\\java\\files\\test.txt");
    try{
        Scanner sc =new Scanner(file);

    } catch (FileNotFoundException e)
    {
        System.out.println("File not found");
        throw new RuntimeException(e);
    }

}
