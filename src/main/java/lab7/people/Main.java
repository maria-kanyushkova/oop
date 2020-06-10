package lab7.people;

public class Main {
    public static void main(String[] args) {
        Pupil pupil = new Pupil("FS", "LN", "Patr", "add", "ScNa", "pC");
        Teacher teacher = new Teacher("FS", "LN", "Patr", "add", "cT");
        Student student = new Student("FS", "LN", "Patr", "add", "UnNa", "STN");

        printIPersonInfo(pupil);
        printIPersonInfo(teacher);
        printIPersonInfo(student);
    }

    public static void printIPersonInfo(IPerson person) {
        System.out.println(person.getFirstName());
        System.out.println(person.getLastName());
        System.out.println(person.getPatronymic());
        System.out.println(person.getAddress());
    }
}
