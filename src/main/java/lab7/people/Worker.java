package lab7.people;

public class Worker extends PersonImpl<IWorker> {
    private String speciality;

    Worker(String _firstName, String _lastName, String _patronymic, String _address, String _speciality) {
        super(_firstName, _lastName, _patronymic, _address);
        setSpeciality(_speciality);
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
