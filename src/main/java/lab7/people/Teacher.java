package lab7.people;

public class Teacher extends PersonImpl<ITeacher> implements ITeacher {
    private String courseTitle;

    Teacher(String _firstName, String _lastName, String _patronymic, String _address, String _courseTitle) {
        super(_firstName, _lastName, _patronymic, _address);
        setCourseTitle(_courseTitle);
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
