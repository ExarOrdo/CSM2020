package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;


import java.sql.Date;

public class StudentModule {
    private String moduleID;
    private int moduleYear;
    private int studentMark;

    private final Date markDate;

    public StudentModule(String moduleID, int moduleYear, int studentMark, Date markDate) {
        this.moduleID = moduleID;
        this.moduleYear = moduleYear;
        this.studentMark = studentMark;
        this.markDate = markDate;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public String getModuleID() {
        return moduleID;
    }

    public int getModuleYear() {
        return moduleYear;
    }

    public int getStudentMark() {
        return studentMark;
    }

}