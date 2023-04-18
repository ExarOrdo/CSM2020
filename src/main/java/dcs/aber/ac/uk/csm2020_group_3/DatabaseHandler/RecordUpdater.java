package dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler;

import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.*;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.Record;
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

                    editRecord.execute();
                    editRecord.close();

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

                    editRecord.execute();
                    editRecord.close();

                    System.out.println("Edited details for Core Module ID: " + coreModuleRecord.getModuleId() +
                            " for Course ID: " + coreModuleRecord.getCourseId());

                } catch (Exception err) {
                    System.err.println("Error when editing Core Module record in Core_Module table: " + err.getMessage());
                    err.printStackTrace();
                    return false;
                }
            }

            case MODULE -> {
                ModuleRecord moduleRecord = (ModuleRecord) recordToUpdate;
                ModuleRecord newDetails = (ModuleRecord)  newRecordDetails;

                try {
                    PreparedStatement editRecord = connection.prepareStatement("UPDATE MODULE SET " +
                            "ModuleName = ?, ModuleDescription = ?, ModuleCredits = ?, ModuleYear = ?," +
                            "ModuleSemester = ?, ModuleTag1 = ?, ModuleTag2 = ?, ModuleTag3 = ?, ModuleTag4 = ?," +
                            "ModuleTag5 = ?, ModuleTag6 = ?, ModuleTag7 = ?, ModuleTag8 = ?, ModulePrerequisite = ?" +
                            "WHERE ModuleID = ?");
                    editRecord.setString(1, newDetails.getModuleName());
                    editRecord.setString(2, newDetails.getModuleDescription());
                    editRecord.setInt(3, newDetails.getModuleCredits());
                    editRecord.setInt(4, newDetails.getModuleYear());
                    editRecord.setInt(5, newDetails.getModuleSemester());
                    editRecord.setString(6, newDetails.getTag1());
                    editRecord.setString(7, newDetails.getTag2());
                    editRecord.setString(8, newDetails.getTag3());
                    editRecord.setString(9, newDetails.getTag4());
                    editRecord.setString(10, newDetails.getTag5());
                    editRecord.setString(11, newDetails.getTag6());
                    editRecord.setString(12, newDetails.getTag7());
                    editRecord.setString(13, newDetails.getTag8());
                    editRecord.setString(14, newDetails.getModulePrerequisite());
                    editRecord.setString(15, moduleRecord.getModuleId());

                    editRecord.execute();
                    editRecord.close();

                    System.out.println("Edited details for a Module ID: " + moduleRecord.getModuleId());

                }catch (Exception err) {
                    System.err.println("Error when editing Module record in Module table: " + err.getMessage());
                    err.printStackTrace();
                    return false;
                }

            }

            case MARKS -> {
                MarkRecord markRecord = (MarkRecord) recordToUpdate;
                MarkRecord newDetails = (MarkRecord) newRecordDetails;

                try {
                    PreparedStatement editRecord = connection.prepareStatement("UPDATE MARKS SET " +
                            "StudentMark = ?, MarkDate = ? WHERE StudentID = ? AND ModuleID = ?");

                    editRecord.setInt(1, newDetails.getStudentMark());
                    editRecord.setDate(2, newDetails.getMarkDate());
                    editRecord.setString(3, markRecord.getStudentId());
                    editRecord.setString(4, markRecord.getModuleId());

                    editRecord.execute();
                    editRecord.close();

                } catch (Exception err) {
                    System.err.println("Error when updating Mark record in Mark table " + err.getMessage());
                    err.printStackTrace();
                    return false;
                }

            }

            case COURSE -> {
                CourseRecord courseRecord = (CourseRecord) recordToUpdate;
                CourseRecord newDetails = (CourseRecord) newRecordDetails;

                try {
                    PreparedStatement editRecord = connection.prepareStatement("UPDATE COURSE SET " +
                            "CourseName = ?, CourseDescription = ? " +
                            "WHERE CourseID = ?");

                    editRecord.setString(1, newDetails.getCourseName());
                    editRecord.setString(2, newDetails.getCourseDescription());
                    editRecord.setString(3, courseRecord.getCourseId());

                    editRecord.execute();
                    editRecord.close();

                }catch (Exception err) {
                    System.err.println("Error when updating Course record in Course table " + err.getMessage());
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
