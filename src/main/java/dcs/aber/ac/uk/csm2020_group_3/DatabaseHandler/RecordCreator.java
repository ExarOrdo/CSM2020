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

                try{
                    PreparedStatement createRecord = connection.prepareStatement("INSERT INTO OPTIONAL_MODULE (CourseID, ModuleID) VALUES (?,?)");

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

                ModuleRecord moduleRecord = (ModuleRecord) recordObject;

                try{
                    PreparedStatement createRecord = connection.prepareStatement("INSERT INTO MODULE (ModuleID, ModuleName, ModuleDescription, ModuleCredits," +
                            "ModuleYear, ModuleSemester, ModuleTag1, ModuleTag2, ModuleTag3) VALUES (?,?,?,?,?,?,?,?,?)");

                    createRecord.setString(1, moduleRecord.getModuleId());
                    createRecord.setString(2, moduleRecord.getModuleName());
                    createRecord.setString(3, moduleRecord.getModuleDescription());
                    createRecord.setInt(4, moduleRecord.getModuleCredits());
                    createRecord.setInt(5, moduleRecord.getModuleYear());
                    createRecord.setInt(6, moduleRecord.getModuleCredits());
                    createRecord.setString(7, moduleRecord.getTag1());
                    createRecord.setString(8, moduleRecord.getTag2());
                    createRecord.setString(9, moduleRecord.getTag3());


                    createRecord.execute();
                    createRecord.close();

                    System.out.println("Added new module with id: " + moduleRecord.getModuleId() + " and name: " + moduleRecord.getModuleName());


                }catch  (Exception err) {
                    System.err.println("Error when creating Course record in Course table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }

                break;
            case MARKS:

                break;
            case COURSE:

                CourseRecord courseRecord = (CourseRecord) recordObject;

                try{
                    PreparedStatement createRecord = connection.prepareStatement("INSERT INTO COURSE (CourseID, CourseName, CourseDescription) VALUES (?,?,?)");

                    createRecord.setString(1, courseRecord.getCourseId());
                    createRecord.setString(2,courseRecord.getCourseName());
                    createRecord.setString(3, courseRecord.getCourseDescription());

                    createRecord.execute();
                    createRecord.close();

                    System.out.println("Added new course with id: " + courseRecord.getCourseId() + " and name: " +
                            courseRecord.getCourseName());


                }catch  (Exception err) {
                    System.err.println("Error when creating Course record in Course table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
                break;
            default:
                System.err.println("Unknown type of Record subclass instance type.");
                return false;
        }



        return true;
    }
}
