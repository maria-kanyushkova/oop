package lab7.people;

public class Student extends StudentImpl<IStudent> {
    Student(String _firstName, String _lastName, String _patronymic, String _address, String _universityName, String _studentCardNumber) {
        super(_firstName, _lastName, _patronymic, _address, _universityName, _studentCardNumber);
    }
}
