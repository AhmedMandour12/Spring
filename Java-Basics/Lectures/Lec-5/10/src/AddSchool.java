class AddSchool extends BaseSchool {

    @Override
    void addStudent(Student s) {
        System.out.println("you are on add student");
        super.addStudent(s);
    }
}