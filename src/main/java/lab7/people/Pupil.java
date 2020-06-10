package lab7.people;

public class Pupil extends PersonImpl<IPupil> implements IPupil {
    private String schoolName;
    private String pupilClass;

    Pupil(String _firstName, String _lastName, String _patronymic, String _address, String _schoolName, String _pupilClass) {
        super(_firstName, _lastName, _patronymic, _address);
        setSchoolName(_schoolName);
        setPupilClass(_pupilClass);
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getPupilClass() {
        return pupilClass;
    }

    public void setPupilClass(String pupilClass) {
        this.pupilClass = pupilClass;
    }
}
