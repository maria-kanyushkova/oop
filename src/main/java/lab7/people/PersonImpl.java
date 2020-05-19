package lab7.people;

public class PersonImpl<T> {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String address;

    PersonImpl(String _firstName, String _lastName, String _patronymic, String _address) {
        setFirstName(_firstName);
        setLastName(_lastName);
        setPatronymic(_patronymic);
        setAddress(_address);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
