package dcs.aber.ac.uk.csm2020_group_3;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordCreator;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordRemover;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.CourseRecord;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.ModuleRecord;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.OptionalModuleRecord;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.StudentRecord;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;

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


    //record objects to use in tests
    private final StudentRecord studentRecord = new StudentRecord(id, firstName, lastName, year, course, password);
    private final ModuleRecord moduleRecord = new ModuleRecord(moduleId, moduleName, moduleDescription, moduleCredits, moduleYear, moduleSemester, tag1, tag2, tag3);
    private final CourseRecord courseRecord = new CourseRecord(courseId, courseName, courseDescription);
    private final OptionalModuleRecord optionalModuleRecord = new OptionalModuleRecord(courseId, moduleId);

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


    //start by testing record creation
    @Test
    @Order(1)
    void testCreatingStudent() throws SQLException {

        RecordCreator recordCreator = new RecordCreator(studentRecord);

        assertTrue(recordCreator.tryCreatingRecord());
    }

    @Test
    @Order (2)
    void testCreatingModule() throws SQLException {
        RecordCreator recordCreator = new RecordCreator(moduleRecord);

        assertTrue(recordCreator.tryCreatingRecord());
    }

    @Test
    @Order (3)
    void testCreatingCourse() throws SQLException {

        RecordCreator recordCreator = new RecordCreator(courseRecord);

        assertTrue(recordCreator.tryCreatingRecord());
    }

    @Test
    @Order (4)
    void testCreatingOptionalModule() throws SQLException {

        RecordCreator recordCreator = new RecordCreator(optionalModuleRecord);


        assertTrue(recordCreator.tryCreatingRecord());
    }



    //===================================================
    //then test removing them
    @Test
    @Order(5)
    void testRemovingStudent() throws SQLException {

        RecordRemover recordRemover = new RecordRemover(studentRecord);


        assertTrue(recordRemover.tryRemovingRecord());
    }



    @Test
    @Order (6)
    void testRemovingCourse() throws SQLException {

        CourseRecord courseRecord = new CourseRecord(courseId, courseName, courseDescription);

        RecordRemover recordRemover = new RecordRemover(courseRecord);

        assertTrue(recordRemover.tryRemovingRecord());
    }

    @Test
    @Order (7)
    void testRemovingOptionalModule() throws SQLException {

        OptionalModuleRecord optionalModuleRecord = new OptionalModuleRecord(courseId, moduleId);

        RecordRemover recordRemover = new RecordRemover(optionalModuleRecord);

        assertTrue(recordRemover.tryRemovingRecord());
    }

    @Test
    @Order (8)
    void testRemovingModule() throws SQLException {

        ModuleRecord moduleRecord = new ModuleRecord(moduleId, moduleName, moduleDescription, moduleCredits, moduleYear,
                moduleSemester, tag1, tag2, tag3);

        RecordRemover recordRemover = new RecordRemover(moduleRecord);

        assertTrue(recordRemover.tryRemovingRecord());
    }
}
