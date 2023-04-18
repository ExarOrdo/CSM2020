package dcs.aber.ac.uk.csm2020_group_3;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordCreator;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordRemover;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordUpdater;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;



/**
 * Class for testing interacting with Student table in the db
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseInteractionTest {

    //Student record details
    private final String id = "testId1";
    private final String firstName = "TestName";
    private final String lastName = "TestLastname";
    private final int year = 1;
    private final String course = "TestCourse";
    private final String password = "Testpassword";

    //student record update details
    private final String updateFirstName = "UpdateName";
    private final String updateLastName = "UpdateLastName";
    private final int updateYear = 2;
    private final String updateCourse = "UpdateCourse";
    private final String updatePassword = "UpdatePassword";



    //course record details
    private final String courseId = "testCourseId";
    private final String courseName = "testCourseName";
    private final String courseDescription = "testCourseDescription";

    //module details
    private final String moduleId = "testModuleID";
    private final String moduleName = "testModuleName";
    private final String moduleDescription = "testModuleDescription";
    private final int moduleCredits = 10;
    private final int moduleYear = 1;
    private final int moduleSemester = 1;
    private final String tag1 = "testTag1";
    private final String tag2 = "testTag2";
    private final String tag3 = "testTag3";


    //mark details
    private final int studentMark = 2;

    private final java.sql.Date markDate = new java.sql.Date(2023,11,1); //deprecated but why not


    //record objects to use in tests
    private final StudentRecord studentRecord = new StudentRecord(id, firstName, lastName, year, course, password);
    private final StudentRecord updateStudentRecord = new StudentRecord(id, updateFirstName, updateLastName, updateYear, updateCourse, updatePassword);

    private final ModuleRecord moduleRecord = new ModuleRecord(moduleId, moduleName, moduleDescription, moduleCredits, moduleYear, moduleSemester, tag1, tag2, tag3);
    private final CourseRecord courseRecord = new CourseRecord(courseId, courseName, courseDescription);
    private final OptionalModuleRecord optionalModuleRecord = new OptionalModuleRecord(courseId, moduleId);
    private final CoreModuleRecord coreModuleRecord = new CoreModuleRecord(courseId, moduleId);
    private final MarkRecord markRecord = new MarkRecord(id, moduleId, studentMark, markDate);

    //
    private void createStudentRecord() throws SQLException {
        RecordCreator recordCreator = new RecordCreator(studentRecord);
        recordCreator.tryCreatingRecord();
    }

    private void createModuleRecord() throws SQLException {
        RecordCreator recordCreator = new RecordCreator(moduleRecord);
        recordCreator.tryCreatingRecord();
    }

    private void createCourseRecord() throws SQLException {
        RecordCreator recordCreator = new RecordCreator(courseRecord);
        recordCreator.tryCreatingRecord();
    }

    private void createOptionalRecord() throws SQLException {
        RecordCreator recordCreator = new RecordCreator(optionalModuleRecord);
        recordCreator.tryCreatingRecord();
    }

    private void createCoreRecord() throws SQLException {
        RecordCreator recordCreator = new RecordCreator(coreModuleRecord);
        recordCreator.tryCreatingRecord();
    }

    private void createMarksRecord() throws SQLException {
        RecordCreator recordCreator = new RecordCreator(markRecord);
        recordCreator.tryCreatingRecord();
    }

    private void removeStudentRecord() throws SQLException {
        RecordRemover recordRemover = new RecordRemover(studentRecord);
        recordRemover.tryRemovingRecord();
    }

    private void removeModuleRecord() throws SQLException {
        RecordRemover recordRemover = new RecordRemover(moduleRecord);
        recordRemover.tryRemovingRecord();
    }

    private void removeCourseRecord() throws SQLException {
        RecordRemover recordRemover = new RecordRemover(courseRecord);
        recordRemover.tryRemovingRecord();
    }

    private void removeOptionalRecord() throws SQLException {
        RecordRemover recordRemover = new RecordRemover(optionalModuleRecord);
        recordRemover.tryRemovingRecord();
    }

    private void removeCoreRecord() throws SQLException {
        RecordRemover recordRemover = new RecordRemover(coreModuleRecord);
        recordRemover.tryRemovingRecord();
    }

    private void removeMarkRecord() throws SQLException {
        RecordRemover recordRemover = new RecordRemover(markRecord);
        recordRemover.tryRemovingRecord();
    }


    //start by testing record creation
    @Test
    @Order(1)
    void testCreatingStudent() throws SQLException {

        RecordCreator recordCreator = new RecordCreator(studentRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeStudentRecord();
    }

    @Test
    @Order (2)
    void testCreatingModule() throws SQLException {
        RecordCreator recordCreator = new RecordCreator(moduleRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeModuleRecord();
    }

    @Test
    @Order (3)
    void testCreatingCourse() throws SQLException {

        RecordCreator recordCreator = new RecordCreator(courseRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeCourseRecord();
    }

    @Test
    @Order (4)
    void testCreatingOptionalModule() throws SQLException {

        createCourseRecord();
        createModuleRecord();

        RecordCreator recordCreator = new RecordCreator(optionalModuleRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeOptionalRecord();
        removeCourseRecord();
        removeModuleRecord();
    }

    @Test
    @Order (5)
    void testCreatingCoreModule() throws SQLException {
        createCourseRecord();
        createModuleRecord();

        RecordCreator recordCreator = new RecordCreator(coreModuleRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeCoreRecord();
        removeCourseRecord();
        removeModuleRecord();
    }

    @Test
    @Order (6)
    void testCreatingMark() throws SQLException{
        createStudentRecord();
        createModuleRecord();

        RecordCreator recordCreator = new RecordCreator(markRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeMarkRecord();
        removeStudentRecord();
        removeModuleRecord();
    }


    //===================================================
    //then test removing them

    @Test
    @Order (7)
    void testRemovingMark() throws SQLException {
        createStudentRecord();
        createModuleRecord();
        createMarksRecord();

        RecordRemover recordRemover = new RecordRemover(markRecord);

        assertTrue(recordRemover.tryRemovingRecord());

        removeStudentRecord();
        removeModuleRecord();
    }

    @Test
    @Order(8)
    void testRemovingStudent() throws SQLException {

        createStudentRecord();

        RecordRemover recordRemover = new RecordRemover(studentRecord);


        assertTrue(recordRemover.tryRemovingRecord());
    }

    @Test
    @Order (9)
    void testRemovingOptionalModule() throws SQLException {
        createCourseRecord();
        createModuleRecord();
        createOptionalRecord();

        RecordRemover recordRemover = new RecordRemover(optionalModuleRecord);

        assertTrue(recordRemover.tryRemovingRecord());

        removeCourseRecord();
        removeModuleRecord();
    }

    @Test
    @Order (10)
    void testRemovingCoreModule() throws SQLException {
        createCourseRecord();
        createModuleRecord();
        createCoreRecord();

        RecordRemover recordRemover = new RecordRemover(coreModuleRecord);

        assertTrue(recordRemover.tryRemovingRecord());

        removeCourseRecord();
        removeModuleRecord();
    }

    @Test
    @Order (11)
    void testRemovingCourse() throws SQLException {
        createCourseRecord();

        RecordRemover recordRemover = new RecordRemover(courseRecord);

        assertTrue(recordRemover.tryRemovingRecord());
    }


    @Test
    @Order (12)
    void testRemovingModule() throws SQLException {
        createModuleRecord();

        RecordRemover recordRemover = new RecordRemover(moduleRecord);

        assertTrue(recordRemover.tryRemovingRecord());
    }

    @Test
    @Order(13)
    void testUpdatingStudent() throws SQLException {
        createStudentRecord();

        RecordUpdater recordUpdater = new RecordUpdater(studentRecord, updateStudentRecord);
        assertTrue(recordUpdater.tryUpdatingRecord());

        RecordUpdater newRecordUpdater = new RecordUpdater(updateStudentRecord, studentRecord);
        assertTrue(newRecordUpdater.tryUpdatingRecord());

        removeStudentRecord();

    }
}
