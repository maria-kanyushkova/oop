package lab7.people;

public class StudentImpl<T> extends PersonImpl<T> {
    private String universityName;
    private String studentCardNumber;

    StudentImpl(String _firstName, String _lastName, String _patronymic, String _address, String _universityName, String _studentCardNumber) {
        super(_firstName, _lastName, _patronymic, _address);
        setUniversityName(_universityName);
        setStudentCardNumber(_studentCardNumber);
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getStudentCardNumber() {
        return studentCardNumber;
    }

    public void setStudentCardNumber(String studentCardNumber) {
        this.studentCardNumber = studentCardNumber;
    }
}
