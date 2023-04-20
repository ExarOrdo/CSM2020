package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.*;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.Record;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Table.COURSE;


public class RecordRemover extends DatabaseHandler{

    private final Record recordObject;

    private final Table typeOfRecord;


    /**
     * Constructs RecordRemover for removing a record of specific Record subclass
     * @param recordObject
     */
    public RecordRemover(Record recordObject) {
        this.recordObject = recordObject;

        this.typeOfRecord = recordObject.getTableName();
    }

    public boolean tryRemovingRecord() throws SQLException {
        this.connection = getConnection();

        //enjoy another switch magic because no Pattern matching
        switch (typeOfRecord) {
            //case of registering a student
            case STUDENT -> {
                //cast recordObject into StudentRecord object
                StudentRecord studentRecord = (StudentRecord) recordObject;
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM STUDENT WHERE StudentID = ?");

                    statement.setString(1, studentRecord.getStudentId());

                    statement.execute();
                    statement.close();

                    System.out.println("Removed student with id: " + studentRecord.getStudentId());


                } catch (Exception err) {
                    System.err.println("Error when removing Student record from Student table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }
            case OPTIONAL_MODULE -> {
                OptionalModuleRecord optionalModuleRecord = (OptionalModuleRecord) recordObject;
                this.connection = getConnection();
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM OPTIONAL_MODULE WHERE ModuleId = ?");

                    statement.setString(1, optionalModuleRecord.getModuleId());

                    statement.execute();
                    statement.close();

                    System.out.println("Removed optional module with id: " + optionalModuleRecord.getModuleId() + " for " +
                            "course with id: +" + optionalModuleRecord.getCourseId());


                } catch (Exception err) {
                    System.err.println("Error when deleting Optional module record in Optional_Module table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }
            case CORE_MODULE -> {
                CoreModuleRecord coreModuleRecord = (CoreModuleRecord) recordObject;
                this.connection = getConnection();
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM CORE_MODULE WHERE ModuleId = ?");

                    statement.setString(1, coreModuleRecord.getModuleId());

                    statement.execute();
                    statement.close();

                    System.out.println("Removed core module with id: " + coreModuleRecord.getModuleId() + " for " +
                            "course with id: +" + coreModuleRecord.getCourseId());


                } catch (Exception err) {
                    System.err.println("Error when deleting Core module record in Core_Module table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }
            case MODULE -> {
                ModuleRecord moduleRecord = (ModuleRecord) recordObject;
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM MODULE WHERE ModuleID = ?");

                    statement.setString(1, moduleRecord.getModuleId());

                    statement.execute();
                    statement.close();

                    System.out.println("Removed module with id: " + moduleRecord.getModuleId());


                } catch (Exception err) {
                    System.err.println("Error when removing module record from Module table:" + err.getMessage());
                    err.printStackTrace();

                    return false;
                }
            }
            case MARKS -> {
                MarkRecord markRecord = (MarkRecord) recordObject;
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM MARKS WHERE StudentID = ? AND ModuleID = ? AND StudentMark = ? AND MarkDate = ?");

                    statement.setString(1, markRecord.getStudentId());
                    statement.setString(2, markRecord.getModuleId());
                    statement.setInt(3, markRecord.getStudentMark());
                    statement.setDate(4, markRecord.getMarkDate()); //again, evil cast, dunno why

                    statement.execute();
                    statement.close();

                    System.out.println("Removed Mark for student: " + markRecord.getStudentId() + " for module id: " + markRecord.getModuleId());


                } catch (Exception err) {
                    System.err.println("Error when removing Mark record from Marks table:" + err.getMessage());
                    err.printStackTrace();
                }
            }
            case COURSE -> {
                CourseRecord courseRecord = (CourseRecord) recordObject;
                try {
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM COURSE WHERE CourseID = ?");

                    statement.setString(1, courseRecord.getCourseId());

                    statement.execute();
                    statement.close();

                    System.out.println("Removed course with id: " + courseRecord.getCourseId());


                } catch (Exception err) {
                    System.err.println("Error when removing course record from Course table:" + err.getMessage());
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
