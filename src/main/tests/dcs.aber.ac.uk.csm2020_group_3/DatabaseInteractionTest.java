package dcs.aber.ac.uk.csm2020_group_3;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordCreator;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordLoader;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordRemover;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordUpdater;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;


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

    //second course record details
    private final String courseId2 = "testCourseId2";
    private final String courseName2 = "testCourseName2";
    private final String courseDescription2 = "testCourseDescription2";


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
    private final String tag4 = "testTag4";
    private final String tag5 = "testTag5";
    private final String tag6 = "testTag6";
    private final String tag7 = "testTag7";
    private final String tag8 = "testTag8";

    private final String modulePrerequisite = "testPrerequisite";

    //second module details
    private final String moduleId2 = "testModuleId2";
    private final String moduleName2 = "testModuleName2";
    private final String moduleDescription2 = "testModuleDescription2";
    private final int moduleCredit2 = 40;
    private final int moduleYear2 = 3;
    private final int moduleSemester2 = 3;
    private final String tagTwo1 = "testTagTwo1";
    private final String tagTwo2 = "testTagTwo2";
    private final String tagTwo3 = "testTagTwo3";
    private final String tagTwo4 = "testTagTwo4";
    private final String tagTwo5 = "testTagTwo5";
    private final String tagTwo6 = "testTagTwo6";
    private final String tagTwo7 = "testTagTwo7";
    private final String tagTwo8 = "testTagTwo8";

    //updated module details
    private final String updatedModuleId = "updatedModuleId";
    private final String updatedModuleName = "updatedModuleName";
    private final String updatedModuleDescription = "updatedModuleDescription";
    private final int updatedModuleCredits = 20;
    private final int updatedModuleYear = 2;
    private final int updatedModuleSemester = 2;
    private final String updatedTag1 = "updatedTag1";
    private final String updatedTag2 = "updatedTag2";
    private final String updatedTag3 = "updatedTag3";
    private final String updatedTag4 = "updatedTag4";
    private final String updatedTag5 = "updatedTag5";
    private final String updatedTag6 = "updatedTag6";
    private final String updatedTag7 = "updatedTag7";
    private final String updatedTag8 = "updatedTag8";

    //updated course details
    private final String updatedCourseId = "updatedCourseId";
    private final String updatedCourseName = "updatedCourseName";
    private final String updatedCourseDescription = "updatedCourseDescription";


    //mark details
    private final int studentMark = 42;
    private final java.sql.Date markDate = new java.sql.Date(2023, 11, 1); //deprecated but why not

    private final int studentMark2 = 50;
    private final java.sql.Date markDate2 = new java.sql.Date(2023, 2, 12); //deprecated but why not


    //record objects to use in tests
    private final StudentRecord studentRecord = new StudentRecord(id, firstName, lastName, year, course, password);
    private final StudentRecord updateStudentRecord = new StudentRecord(id, updateFirstName, updateLastName, updateYear, updateCourse, updatePassword);

    private final ModuleRecord moduleRecord = new ModuleRecord(moduleId, moduleName, moduleDescription, moduleCredits, moduleYear, moduleSemester, tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, modulePrerequisite);
    private final ModuleRecord moduleRecord2 = new ModuleRecord(moduleId2, moduleName2, moduleDescription2, moduleCredit2, moduleYear2, moduleSemester2, tagTwo1, tagTwo2, tagTwo3, tagTwo4, tagTwo5, tagTwo6, tagTwo7, tagTwo8, modulePrerequisite);
    private final ModuleRecord updatedModuleRecord = new ModuleRecord(updatedModuleId, updatedModuleName, updatedModuleDescription, updatedModuleCredits, updatedModuleYear, updatedModuleSemester, updatedTag1, updatedTag2, updatedTag3, updatedTag4, updatedTag5, updatedTag6, updatedTag7, updatedTag8, modulePrerequisite);

    private final CourseRecord courseRecord = new CourseRecord(courseId, courseName, courseDescription);
    private final CourseRecord courseRecord2 = new CourseRecord(courseId2, courseName2, courseDescription2);
    private final CourseRecord updatedCourseRecord = new CourseRecord(updatedCourseId, updatedCourseName, updatedCourseDescription);

    private final OptionalModuleRecord optionalModuleRecord = new OptionalModuleRecord(courseId, moduleId);
    private final OptionalModuleRecord optionalModuleRecord2 = new OptionalModuleRecord(courseId2, moduleId2);

    private final CoreModuleRecord coreModuleRecord = new CoreModuleRecord(courseId, moduleId);
    private final CoreModuleRecord coreModuleRecord2 = new CoreModuleRecord(courseId2, moduleId2);

    private final MarkRecord markRecord = new MarkRecord(id, moduleId, studentMark, markDate);
    private final MarkRecord markRecord2 = new MarkRecord(id, moduleId, studentMark2, markDate2);

    //
    private void createStudentRecord(StudentRecord studentRecord) throws SQLException {
        RecordCreator recordCreator = new RecordCreator(studentRecord);
        recordCreator.tryCreatingRecord();
    }

    private void createModuleRecord(ModuleRecord moduleRecord) throws SQLException {
        RecordCreator recordCreator = new RecordCreator(moduleRecord);
        recordCreator.tryCreatingRecord();
    }

    private void createCourseRecord(CourseRecord courseRecord) throws SQLException {
        RecordCreator recordCreator = new RecordCreator(courseRecord);
        recordCreator.tryCreatingRecord();
    }

    private void createOptionalRecord(OptionalModuleRecord optionalModuleRecord) throws SQLException {
        RecordCreator recordCreator = new RecordCreator(optionalModuleRecord);
        recordCreator.tryCreatingRecord();
    }

    private void createCoreRecord(CoreModuleRecord coreModuleRecord) throws SQLException {
        RecordCreator recordCreator = new RecordCreator(coreModuleRecord);
        recordCreator.tryCreatingRecord();
    }

    private void createMarksRecord(MarkRecord markRecord) throws SQLException {
        RecordCreator recordCreator = new RecordCreator(markRecord);
        recordCreator.tryCreatingRecord();
    }

    private void removeStudentRecord(StudentRecord studentRecord) throws SQLException {
        RecordRemover recordRemover = new RecordRemover(studentRecord);
        recordRemover.tryRemovingRecord();
    }

    private void removeModuleRecord(ModuleRecord moduleRecord) throws SQLException {
        RecordRemover recordRemover = new RecordRemover(moduleRecord);
        recordRemover.tryRemovingRecord();
    }

    private void removeCourseRecord(CourseRecord courseRecord) throws SQLException {
        RecordRemover recordRemover = new RecordRemover(courseRecord);
        recordRemover.tryRemovingRecord();
    }

    private void removeOptionalRecord(OptionalModuleRecord optionalModuleRecord) throws SQLException {
        RecordRemover recordRemover = new RecordRemover(optionalModuleRecord);
        recordRemover.tryRemovingRecord();
    }

    private void removeCoreRecord(CoreModuleRecord coreModuleRecord) throws SQLException {
        RecordRemover recordRemover = new RecordRemover(coreModuleRecord);
        recordRemover.tryRemovingRecord();
    }

    private void removeMarkRecord(MarkRecord markRecord) throws SQLException {
        RecordRemover recordRemover = new RecordRemover(markRecord);
        recordRemover.tryRemovingRecord();
    }


    //start by testing record creation
    @Test
    @Order(1)
    void testCreatingStudent() throws SQLException {

        RecordCreator recordCreator = new RecordCreator(studentRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeStudentRecord(studentRecord);
    }

    @Test
    @Order(2)
    void testCreatingModule() throws SQLException {
        RecordCreator recordCreator = new RecordCreator(moduleRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeModuleRecord(moduleRecord);
    }

    @Test
    @Order(3)
    void testCreatingCourse() throws SQLException {

        RecordCreator recordCreator = new RecordCreator(courseRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeCourseRecord(courseRecord);
    }

    @Test
    @Order(4)
    void testCreatingOptionalModule() throws SQLException {

        createCourseRecord(courseRecord);
        createModuleRecord(moduleRecord);

        RecordCreator recordCreator = new RecordCreator(optionalModuleRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeOptionalRecord(optionalModuleRecord);
        removeCourseRecord(courseRecord);
        removeModuleRecord(moduleRecord);
    }

    @Test
    @Order(5)
    void testCreatingCoreModule() throws SQLException {
        createCourseRecord(courseRecord);
        createModuleRecord(moduleRecord);

        RecordCreator recordCreator = new RecordCreator(coreModuleRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeCoreRecord(coreModuleRecord);
        removeCourseRecord(courseRecord);
        removeModuleRecord(moduleRecord);
    }

    @Test
    @Order(6)
    void testCreatingMark() throws SQLException {
        createStudentRecord(studentRecord);
        createModuleRecord(moduleRecord);

        RecordCreator recordCreator = new RecordCreator(markRecord);

        assertTrue(recordCreator.tryCreatingRecord());

        removeMarkRecord(markRecord);
        removeStudentRecord(studentRecord);
        removeModuleRecord(moduleRecord);
    }


    //===================================================
    //then test removing them

    @Test
    @Order(7)
    void testRemovingMark() throws SQLException {
        createStudentRecord(studentRecord);
        createModuleRecord(moduleRecord);
        createMarksRecord(markRecord);

        RecordRemover recordRemover = new RecordRemover(markRecord);

        assertTrue(recordRemover.tryRemovingRecord());

        removeStudentRecord(studentRecord);
        removeModuleRecord(moduleRecord);
    }

    @Test
    @Order(8)
    void testRemovingStudent() throws SQLException {

        createStudentRecord(studentRecord);

        RecordRemover recordRemover = new RecordRemover(studentRecord);


        assertTrue(recordRemover.tryRemovingRecord());
    }

    @Test
    @Order(9)
    void testRemovingOptionalModule() throws SQLException {
        createCourseRecord(courseRecord);
        createModuleRecord(moduleRecord);
        createOptionalRecord(optionalModuleRecord);

        RecordRemover recordRemover = new RecordRemover(optionalModuleRecord);

        assertTrue(recordRemover.tryRemovingRecord());

        removeCourseRecord(courseRecord);
        removeModuleRecord(moduleRecord);
    }

    @Test
    @Order(10)
    void testRemovingCoreModule() throws SQLException {
        createCourseRecord(courseRecord);
        createModuleRecord(moduleRecord);
        createCoreRecord(coreModuleRecord);

        RecordRemover recordRemover = new RecordRemover(coreModuleRecord);

        assertTrue(recordRemover.tryRemovingRecord());

        removeCourseRecord(courseRecord);
        removeModuleRecord(moduleRecord);
    }

    @Test
    @Order(11)
    void testRemovingCourse() throws SQLException {
        createCourseRecord(courseRecord);

        RecordRemover recordRemover = new RecordRemover(courseRecord);

        assertTrue(recordRemover.tryRemovingRecord());
    }


    @Test
    @Order(12)
    void testRemovingModule() throws SQLException {
        createModuleRecord(moduleRecord);

        RecordRemover recordRemover = new RecordRemover(moduleRecord);

        assertTrue(recordRemover.tryRemovingRecord());
    }

    @Test
    @Order(13)
    void testUpdatingStudent() throws SQLException {
        createStudentRecord(studentRecord);

        RecordUpdater recordUpdater = new RecordUpdater(studentRecord, updateStudentRecord);
        assertTrue(recordUpdater.tryUpdatingRecord());

        RecordUpdater newRecordUpdater = new RecordUpdater(updateStudentRecord, studentRecord);
        assertTrue(newRecordUpdater.tryUpdatingRecord());

        removeStudentRecord(studentRecord);

    }

    @Test
    @Order(14)
    void testUpdatingOptionalModule() throws SQLException {
        createModuleRecord(moduleRecord);
        createCourseRecord(courseRecord);
        createOptionalRecord(optionalModuleRecord);

        //create second course and module to change the Optional details
        createModuleRecord(moduleRecord2);
        createCourseRecord(courseRecord2);

        RecordUpdater recordUpdater = new RecordUpdater(optionalModuleRecord, optionalModuleRecord2);

        assertTrue(recordUpdater.tryUpdatingRecord());

        removeModuleRecord(moduleRecord);
        removeModuleRecord(moduleRecord2);
        removeCourseRecord(courseRecord);
        removeCourseRecord(courseRecord2);
        removeOptionalRecord(optionalModuleRecord2);
    }

    @Test
    @Order(15)
    void testUpdatingCoreModuleRecord() throws SQLException {
        createModuleRecord(moduleRecord);
        createCourseRecord(courseRecord);
        createCoreRecord(coreModuleRecord);

        //create second course and module to change the Optional details
        createModuleRecord(moduleRecord2);
        createCourseRecord(courseRecord2);

        RecordUpdater recordUpdater = new RecordUpdater(coreModuleRecord, coreModuleRecord2);

        assertTrue(recordUpdater.tryUpdatingRecord());

        removeModuleRecord(moduleRecord);
        removeModuleRecord(moduleRecord2);
        removeCourseRecord(courseRecord);
        removeCourseRecord(courseRecord2);
        removeCoreRecord(coreModuleRecord2);
    }

    @Test
    @Order(16)
    void testUpdatingModuleRecord() throws SQLException {
        createModuleRecord(moduleRecord);

        RecordUpdater recordUpdater = new RecordUpdater(moduleRecord, moduleRecord2);

        assertTrue(recordUpdater.tryUpdatingRecord());

        removeModuleRecord(moduleRecord);

    }

    @Test
    @Order(17)
    void testUpdatingMarkRecord() throws SQLException {
        createStudentRecord(studentRecord);
        createModuleRecord(moduleRecord);
        createMarksRecord(markRecord);

        RecordUpdater recordUpdater = new RecordUpdater(markRecord, markRecord2);

        assertTrue(recordUpdater.tryUpdatingRecord());

        removeMarkRecord(markRecord2);
        removeModuleRecord(moduleRecord);
        removeStudentRecord(studentRecord);

    }

    @Test
    @Order(18)
    void testUpdatingCourseRecord() throws SQLException {
        createCourseRecord(courseRecord);

        RecordUpdater recordUpdater = new RecordUpdater(courseRecord, courseRecord2);

        assertTrue(recordUpdater.tryUpdatingRecord());

        removeCourseRecord(courseRecord);

    }

    @Test
    @Order(19)
    void testGettingStudentYear() throws SQLException {
        RecordLoader recordLoader = new RecordLoader();
        System.out.println("Year fetched: " + recordLoader.getStudentYear("12345"));
    }

    @Test
    @Order(20)
    void testGettingSubjectNames() throws SQLException {
        RecordLoader recordLoader = new RecordLoader();
        recordLoader.getSubjectList();

    }

    @Test
    @Order(21)
    void testGettingCourseNames() throws SQLException {
        RecordLoader recordLoader = new RecordLoader();
        recordLoader.getCourseListBySubject("Computer Science");
    }

    @Test
    @Order(22)
    void testGettingModules() throws SQLException {
        RecordLoader recordLoader = new RecordLoader();
        recordLoader.getModuleListByCourse("BSc Computer Science", 1);
    }
}
