package dcs.aber.ac.uk.csm2020_group_3;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordRemover;
import dcs.aber.ac.uk.csm2020_group_3.RecordTypes.StudentRecord;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterControllerTest extends ApplicationTest {


    @Override
    public void start(Stage stage) {
        Thread thread = new Thread(() -> Platform.runLater(() -> {
            try {
                new Main().start(new Stage());
                Main main = new Main();
                main.changeScene("Register.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        thread.start();
    }

    @Test
    public void testRegistration() throws SQLException {
        FxRobot robot = new FxRobot();
        StudentRecord studentRecord = new StudentRecord("uiUnitTestRegister", "John", "Doe", 1, "Computer Science", "password123");
        RecordRemover recordRemover = new RecordRemover(studentRecord);
        recordRemover.tryRemovingRecord();

        // Enter valid student details
        robot.clickOn("#studentId").write("uiUnitTestRegister");
        robot.clickOn("#password").write("password123");
        robot.clickOn("#firstName").write("John");
        robot.clickOn("#lastName").write("Doe");
        robot.clickOn("#yearComboBox").clickOn("1");
        robot.clickOn("#courseComboBox").clickOn("G400");
        robot.clickOn("#confirmBtn");
        assertEquals("Recommendation", Main.currentStage.getTitle());
    }


    @Test
    public void testEmptyFields() {
        FxRobot robot = new FxRobot();

        // Leave all fields empty and click confirm
        robot.clickOn("#confirmBtn");
        Scene scene = Main.currentStage.getScene();
        Text incorrectField = (Text) scene.lookup("#alreadyExists");
        assertTrue(incorrectField.isVisible());
        assertEquals("All fields must be filled", incorrectField.getText());

    }

    @Test
    public void testExistingStudentId() {
        FxRobot robot = new FxRobot();

        // Enter a student ID that already exists in the database
        robot.clickOn("#studentId").write("12345");
        robot.clickOn("#password").write("password123");
        robot.clickOn("#firstName").write("John");
        robot.clickOn("#lastName").write("Doe");
        robot.clickOn("#yearComboBox").clickOn("1");
        robot.clickOn("#courseComboBox").clickOn("G400");
        robot.clickOn("#confirmBtn");
        Scene scene = Main.currentStage.getScene();
        Text incorrectField = (Text) scene.lookup("#alreadyExists");
        assertEquals("Student ID already exists", incorrectField.getText());
        // Check that an error message is displayed
    }
}