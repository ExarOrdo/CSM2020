package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.CoreModuleRecord;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.OptionalModuleRecord;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.Record;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.StudentRecord;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Updates any specified record with new information
 */
public class RecordUpdater extends DatabaseHandler{

    private final Table typeOfRecord;

    private final Record recordToUpdate;

    private final Record newRecordDetails;

    /**
     * Constructs an Object that given an instance of Record's subclass, can update this record with new details
     * @param recordObject record object to update
     * @param newRecordDetails record object containing new details
     */
    public RecordUpdater(Record recordObject, Record newRecordDetails) {
        this.typeOfRecord = recordObject.getTableName();

        this.recordToUpdate = recordObject;

        this.newRecordDetails = newRecordDetails;
    }


    public boolean tryUpdatingRecord() throws SQLException {
        this.connection = getConnection();

        switch(typeOfRecord) {
            case STUDENT -> {
                StudentRecord studentRecord = (StudentRecord) recordToUpdate;
                StudentRecord newDetails = (StudentRecord) newRecordDetails;
                try {
                    PreparedStatement editRecord = connection.prepareStatement("UPDATE STUDENT SET" +
                            " StudentName = ?, StudentCourse = ?, StudentYear = ?, StudentPassword = ?" +
                            "WHERE StudentID = ?");

                    editRecord.setString(1, (newDetails.getStudentName()));
                    editRecord.setString(2, (newDetails.getStudentCourse()));
                    editRecord.setInt(3, newDetails.getStudentYear());
                    editRecord.setString(4, newDetails.getStudentPassword());
                    editRecord.setString(5, studentRecord.getStudentId());

                    editRecord.execute();
                    editRecord.close();

                    System.out.println("Edited details for Student Id: " + studentRecord.getStudentId());
                } catch (Exception err) {
                    System.err.println("Error when editing Student record in Student table: " + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }

            case OPTIONAL_MODULE -> {
                OptionalModuleRecord optionalModuleRecord = (OptionalModuleRecord) recordToUpdate;
                OptionalModuleRecord newDetails = (OptionalModuleRecord) newRecordDetails;
                try {
                    PreparedStatement editRecord = connection.prepareStatement("UPDATE OPTIONAL_MODULE SET" +
                            "CourseID = ?, ModuleID = ? WHERE CourseID = ? AND ModuleID = ?");

                    editRecord.setString(1, newDetails.getCourseId());
                    editRecord.setString(2, newDetails.getModuleId());
                    editRecord.setString(3, optionalModuleRecord.getCourseId());
                    editRecord.setString(4, optionalModuleRecord.getModuleId());

                    System.out.println("Edited details for Optional Module ID: " + optionalModuleRecord.getModuleId() +
                            " for Course ID: " + optionalModuleRecord.getCourseId());
                } catch (Exception err) {
                    System.err.println("Error when editing Optional Module record in Optional_Module table: " + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }

            case CORE_MODULE -> {
                CoreModuleRecord coreModuleRecord = (CoreModuleRecord) recordToUpdate;
                CoreModuleRecord newDetails = (CoreModuleRecord) newRecordDetails;

                try {
                    PreparedStatement editRecord = connection.prepareStatement("UPDATE CORE_MODULE SET" +
                            "CourseID = ?, ModuleID = ? WHERE CourseID = ? AND ModuleID = ?");
                    editRecord.setString(1, newDetails.getCourseId());
                    editRecord.setString(2, newDetails.getModuleId());
                    editRecord.setString(3, coreModuleRecord.getCourseId());
                    editRecord.setString(4, coreModuleRecord.getModuleId());

                    System.out.println("Edited details for Core Module ID: " + coreModuleRecord.getModuleId() +
                            " for Course ID: " + coreModuleRecord.getCourseId());

                } catch (Exception err) {
                    System.err.println("Error when editing Core Module record in Core_Module table: " + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }

            case MODULE -> {

            }

            case MARKS -> {

            }

            case COURSE -> {

            }

            default -> {
                System.err.println("Unknown type of Record subclass instance type.");
                return false;
            }
        }

        return true;
    }
}
