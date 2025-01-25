package entities;

public class Attendance {

    private Integer attendanceId;
    private String studentIndex;
    private Integer groupId;
    private Integer termId;
    private String attendanceStatus;

    static public final String ATTENDANCE_STATUS_PRESENT = "present";
    static public final String ATTENDANCE_STATUS_ABSENT = "absent";
    static public final String ATTENDANCE_STATUS_EXCUSED = "excused";

    public Attendance(Integer attendanceId, String studentIndex, Integer groupId, Integer termId, String attendanceStatus) {
        this.attendanceId = attendanceId;
        this.studentIndex = studentIndex;
        this.groupId = groupId;
        this.termId = termId;
        this.attendanceStatus = attendanceStatus;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    @Override
    public String toString() {
        return attendanceStatus;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public String getStudentIndex() {
        return studentIndex;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Integer getTermId() {
        return termId;
    }

}
