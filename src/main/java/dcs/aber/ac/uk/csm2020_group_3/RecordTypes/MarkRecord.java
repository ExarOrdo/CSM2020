package dcs.aber.ac.uk.csm2020_group_3.RecordTypes;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Table;

import java.util.Date;

public class MarkRecord extends Record{

    private final String studentId;

    private final String moduleId;

    private final int studentMark;

    private final java.sql.Date markDate;


    /**
     * Constructs an object holding data for single Marks record
     * @param studentId
     * @param moduleId
     * @param studentMark
     * @param markDate
     */
    public MarkRecord(String studentId, String moduleId, int studentMark, java.sql.Date markDate) {
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.studentMark = studentMark;
        this.markDate = markDate;

        this.setNumberOfFields(4);
        this.setTableName(Table.MARKS);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public int getStudentMark() {
        return studentMark;
    }

    public java.sql.Date getMarkDate() {
        return markDate;
    }
}
