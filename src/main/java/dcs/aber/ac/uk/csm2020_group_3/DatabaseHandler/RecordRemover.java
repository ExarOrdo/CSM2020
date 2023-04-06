package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.*;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.Record;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RecordRemover extends DatabaseHandler{

    private Record recordObject;

    private Table typeOfRecord;


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
            case STUDENT:
                //cast recordObject into StudentRecord object
                StudentRecord studentRecord = (StudentRecord) recordObject;

                try {
                    PreparedStatement createStudent = connection.prepareStatement("DELETE FROM STUDENT WHERE StudentID = ?");

                    createStudent.setString(1, studentRecord.getStudentId());

                    createStudent.execute();
                    createStudent.close();

                    System.out.println("Removed student with id: " + studentRecord.getStudentId());


                } catch (Exception err) {
                    System.err.println("Error when removing Student record from Student table:" + err.getMessage());
                    err.printStackTrace();
                    return false;
                }

                break;
            case OPTIONAL_MODULE:

                OptionalModuleRecord optionalModuleRecord = (OptionalModuleRecord) recordObject;

                this.connection = getConnection();
                try {
                    PreparedStatement createRecord = connection.prepareStatement("DELETE FROM OPTIONAL_MODULE (CousreID, ModuleID) VALUES (?,?)");

                    createRecord.setString(1, optionalModuleRecord.getCourseId());
                    createRecord.setString(2, optionalModuleRecord.getModuleId());

                    createRecord.execute();
                    createRecord.close();

                    System.out.println("Deleted optional module with id: " + optionalModuleRecord.getModuleId() + " for " +
                            "course with id: +" + optionalModuleRecord.getCourseId());


                } catch (Exception err) {
                    System.err.println("Error when deleting Optional module record in Optional_Module table:" + err.getMessage());
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
