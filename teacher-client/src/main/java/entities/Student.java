package entities;

public class Student {
    private String studentIndex;
    private String studentName;
    private String studentSurname;
    private String studentGroup;

    public Student(String studentIndex, String studentName, String studentSurname, String studentGroup) {
        this.studentIndex = studentIndex;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentGroup = studentGroup;
    }

    public String getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    @Override
    public String toString() {
        return studentIndex + " " + studentName + " " + studentSurname + " " + studentGroup;
    }

}
