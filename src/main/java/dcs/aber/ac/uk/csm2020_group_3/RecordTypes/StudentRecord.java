package dcs.aber.ac.uk.csm2020_group_3.RecordTypes;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Table;

/**
 * Holds data regarding single student record, used when dealing with db
 */
public class StudentRecord extends Record{

    private String studentId;

    private String studentName;

    private int studentYear;

    private String studentPassword;


    /**
     * Construct a single student record for Student table
     * @param id student's id
     * @param name student's name
     * @param year student's current year
     * @param password student's password
     */
    public StudentRecord (String id, String name, int year, String password) {
        this.studentId = id;

        this.studentName = name;

        this.studentYear = year;

        this.studentPassword = password;

        //change the number to how many private variables the class has, or how many field the table has
        this.setNumberOfFields(4);
        this.setTableName(Table.STUDENT);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentYear() {
        return studentYear;
    }

    public String getStudentPassword() {
        return studentPassword;
    }
}

