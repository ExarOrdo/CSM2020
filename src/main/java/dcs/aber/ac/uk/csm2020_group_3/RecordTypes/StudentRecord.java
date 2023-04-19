package dcs.aber.ac.uk.csm2020_group_3.RecordTypes;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Table;

/**
 * Holds data regarding single student record, used when dealing with db
 */
public class StudentRecord extends Record {

    private final String studentId;

    private final String studentName;

    private final int studentYear;

    private final String studentPassword;

    private final String studentCourse;


    /**
     * Construct a single student record for Student table
     * @param id student's id
     * @param firstName student's first name
     * @param lastName student's last name
     * @param year student's current year
     * @param password student's password
     */
    public StudentRecord (String id, String firstName, String lastName, int year, String course,String password) {
        this.studentId = id;

        this.studentName = firstName + lastName;

        this.studentYear = year;

        this.studentCourse = course;

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

    public String getStudentCourse() {
        return studentCourse;
    }
}

