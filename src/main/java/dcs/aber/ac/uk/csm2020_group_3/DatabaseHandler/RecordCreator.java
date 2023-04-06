package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.*;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.Record;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a record for a new user
 * might need marks??? modules????
 */
public class RecordCreator extends DatabaseHandler{

    private Table typeOfRecord;
    private Record recordObject;

    public RecordCreator(Record recordObject) {
        this.recordObject = recordObject;

        this.typeOfRecord = recordObject.getTableName();
    }

    public boolean tryCreatingRecord() throws SQLException {


        this.connection = getConnection();

        //apparently switch Pattern Matching is not supported in java 18 so enjoy extra variable band-aid
        switch (typeOfRecord) {
            //case of registering a student
            case STUDENT:
                //cast recordObject into StudentRecord object
                StudentRecord studentRecord = (StudentRecord) recordObject;

                try{
                    PreparedStatement createStudent = connection.prepareStatement("INSERT INTO STUDENT (StudentID,StudentName,StudentCourse,StudentYear,StudentPassword) VALUES (?,?,?,?,?)");

                    createStudent.setString(1, studentRecord.getStudentId());
                    createStudent.setString(2,studentRecord.getStudentName());
                    createStudent.setString(3, studentRecord.getStudentCourse());
                    createStudent.setInt(4, studentRecord.getStudentYear());
                    createStudent.setString(5,studentRecord.getStudentPassword());

                    createStudent.execute();
                    createStudent.close();

                    System.out.println("Registered student with id: " + studentRecord.getStudentId());


                }catch  (Exception err) {
                    System.err.println("Error when creating Student record in Student table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }

                break;
            case OPTIONAL_MODULE:

                OptionalModuleRecord optionalModuleRecord = (OptionalModuleRecord) recordObject;

                this.connection = getConnection();
                try{
                    PreparedStatement createRecord = connection.prepareStatement("INSERT INTO OPTIONAL_MODULE (CousreID, ModuleID) VALUES (?,?)");

                    createRecord.setString(1, optionalModuleRecord.getCourseId());
                    createRecord.setString(2,optionalModuleRecord.getModuleId());

                    createRecord.execute();
                    createRecord.close();

                    System.out.println("Added new optional module with id: " + optionalModuleRecord.getModuleId() + " for " +
                            "course with id: +" + optionalModuleRecord.getCourseId());


                }catch  (Exception err) {
                    System.err.println("Error when creating Optional module record in Optional_Module table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }

                break;
            case CORE_MODULE:

                break;
            case MODULE:

                break;
            case MARKS:

                break;
            case COURSE:

                break;
            default:
                System.err.println("Unknown type of Record subclass instance type.");
                return false;
        }



        return true;
    }
}
