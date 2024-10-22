package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.Record;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Creates a record for any of the existing tables in our db
 */
public class RecordCreator extends DatabaseHandler {

    private final Table typeOfRecord;
    private final Record recordObject;

    public RecordCreator(Record recordObject) {
        this.recordObject = recordObject;

        this.typeOfRecord = recordObject.getTableName();
    }

    public boolean tryCreatingRecord() throws SQLException {


        this.connection = getConnection();

        //apparently switch Pattern Matching is not supported in java 18 so enjoy extra variable band-aid
        switch (typeOfRecord) {
            //case of registering a student
            case STUDENT -> {
                //cast recordObject into StudentRecord object
                StudentRecord studentRecord = (StudentRecord) recordObject;
                try {
                    PreparedStatement createStudent = connection.prepareStatement("INSERT INTO STUDENT (StudentID,StudentName,StudentCourse,StudentYear,StudentPassword) VALUES (?,?,?,?,?)");

                    createStudent.setString(1, studentRecord.getStudentId());
                    createStudent.setString(2, studentRecord.getStudentName());
                    createStudent.setString(3, studentRecord.getStudentCourse());
                    createStudent.setInt(4, studentRecord.getStudentYear());
                    createStudent.setString(5, studentRecord.getStudentPassword());

                    createStudent.execute();
                    createStudent.close();


                } catch (Exception err) {
                    System.err.println("Error when creating Student record in Student table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }
            case OPTIONAL_MODULE -> {
                OptionalModuleRecord optionalModuleRecord = (OptionalModuleRecord) recordObject;
                try {
                    PreparedStatement createRecord = connection.prepareStatement("INSERT INTO OPTIONAL_MODULE (CourseID, ModuleID) VALUES (?,?)");

                    createRecord.setString(1, optionalModuleRecord.getCourseId());
                    createRecord.setString(2, optionalModuleRecord.getModuleId());

                    createRecord.execute();
                    createRecord.close();


                } catch (Exception err) {
                    System.err.println("Error when creating Optional module record in Optional_Module table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }
            case CORE_MODULE -> {
                CoreModuleRecord coreModuleRecord = (CoreModuleRecord) recordObject;
                try {
                    PreparedStatement createRecord = connection.prepareStatement("INSERT INTO CORE_MODULE (CourseID, ModuleID) VALUES (?,?)");

                    createRecord.setString(1, coreModuleRecord.getCourseId());
                    createRecord.setString(2, coreModuleRecord.getModuleId());

                    createRecord.execute();
                    createRecord.close();


                } catch (Exception err) {
                    System.err.println("Error when creating Core module record in Core_Module table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }
            case MODULE -> {
                ModuleRecord moduleRecord = (ModuleRecord) recordObject;
                try {
                    PreparedStatement createRecord = connection.prepareStatement("INSERT INTO MODULE (ModuleID, ModuleName, ModuleDescription, ModuleCredits," +
                            "ModuleYear, ModuleSemester, ModuleTag1, ModuleTag2, ModuleTag3, ModuleTag4, ModuleTag5, ModuleTag6, " +
                            "ModuleTag7, ModuleTag8, ModulePrerequisite) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    createRecord.setString(1, moduleRecord.getModuleId());
                    createRecord.setString(2, moduleRecord.getModuleName());
                    createRecord.setString(3, moduleRecord.getModuleDescription());
                    createRecord.setInt(4, moduleRecord.getModuleCredits());
                    createRecord.setInt(5, moduleRecord.getModuleYear());
                    createRecord.setInt(6, moduleRecord.getModuleCredits());
                    createRecord.setString(7, moduleRecord.getTag1());
                    createRecord.setString(8, moduleRecord.getTag2());
                    createRecord.setString(9, moduleRecord.getTag3());
                    createRecord.setString(10, moduleRecord.getTag4());
                    createRecord.setString(11, moduleRecord.getTag5());
                    createRecord.setString(12, moduleRecord.getTag6());
                    createRecord.setString(13, moduleRecord.getTag7());
                    createRecord.setString(14, moduleRecord.getTag8());
                    createRecord.setString(15, moduleRecord.getModulePrerequisite());


                    createRecord.execute();
                    createRecord.close();


                } catch (Exception err) {
                    System.err.println("Error when creating Course record in Course table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }
            case MARKS -> {
                MarkRecord markRecord = (MarkRecord) recordObject;
                try {
                    PreparedStatement createRecord = connection.prepareStatement("INSERT INTO MARKs (StudentID, ModuleID, StudentMark, MarkDate) VALUES (?,?,?,?)");

                    createRecord.setString(1, markRecord.getStudentId());
                    createRecord.setString(2, markRecord.getModuleId());
                    createRecord.setInt(3, markRecord.getStudentMark());
                    createRecord.setDate(4, markRecord.getMarkDate()); //evil cast hack, might need to change later

                    createRecord.execute();
                    createRecord.close();


                } catch (Exception err) {
                    System.err.println("Error when creating Mark record in Marks table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }
            case COURSE -> {
                CourseRecord courseRecord = (CourseRecord) recordObject;
                try {
                    PreparedStatement createRecord = connection.prepareStatement("INSERT INTO COURSE (CourseID, CourseName, CourseDescription) VALUES (?,?,?)");

                    createRecord.setString(1, courseRecord.getCourseId());
                    createRecord.setString(2, courseRecord.getCourseName());
                    createRecord.setString(3, courseRecord.getCourseDescription());

                    createRecord.execute();
                    createRecord.close();


                } catch (Exception err) {
                    System.err.println("Error when creating Course record in Course table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }
            default -> {
                System.err.println("Unknown type of Record subclass instance type.");
                return false;
            }
        }


        return true;
    }

}
