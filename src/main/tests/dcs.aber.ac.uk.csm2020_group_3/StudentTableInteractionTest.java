package dcs.aber.ac.uk.csm2020_group_3;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordCreator;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordRemover;
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
public class StudentTableInteractionTest {

    @Test
    @Order(1)
    void testCreatingStudent() throws SQLException {

        String id = "testId1";
        String firstName = "TestName";
        String lastName = "TestLastname";
        int year = 1;
        String course = "TestCourse";
        String password = "Testpassword";

        StudentRecord studentRecord = new StudentRecord(id, firstName, lastName, year, course, password);

        RecordCreator recordCreator = new RecordCreator(studentRecord);


        assertTrue(recordCreator.tryCreatingRecord());
    }

    @Test
    @Order(2)
    void testRemovingStudent() throws SQLException {
        String id = "testId1";
        String firstName = "TestName";
        String lastName = "TestLastname";
        int year = 1;
        String course = "TestCourse";
        String password = "Testpassword";

        StudentRecord studentRecord = new StudentRecord(id, firstName, lastName, year, course, password);

        RecordRemover recordRemover = new RecordRemover(studentRecord);


        assertTrue(recordRemover.tryRemovingRecord());
    }
}
