package lab7.people;

public class AdvancedStudent extends StudentImpl<IAdvancedStudent> implements IAdvancedStudent {
    private String dissertationTopic;

    AdvancedStudent(String _firstName, String _lastName, String _patronymic, String _address, String _universityName, String _studentCardNumber, String _dissertationTopic) {
        super(_firstName, _lastName, _patronymic, _address, _universityName, _studentCardNumber);
        setDissertationTopic(_dissertationTopic);
    }

    public String getDissertationTopic() {
        return dissertationTopic;
    }

    public void setDissertationTopic(String dissertationTopic) {
        this.dissertationTopic = dissertationTopic;
    }
}
