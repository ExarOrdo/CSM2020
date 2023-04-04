package dcs.aber.ac.uk.csm2020_group_3;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import org.testfx.framework.junit5.Start;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest extends ApplicationTest {


    @Start
    public void start(Stage stage) throws Exception {
    }

    @Test
    void testStart() {
        Main main = new Main();
        Platform.runLater(() -> {

            Stage stage = new Stage();

            try {
                main.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals("Login", stage.getTitle());

        });
    }

    @Test
    void testChangeScene() throws IOException {
        Main main = new Main();
        Platform.runLater(() -> {
            Stage stage = new Stage();
            try {
                main.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                main.changeScene("Login.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals("Login", stage.getTitle());
            try {
                main.changeScene("Recommendation.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals("Recommendation", stage.getTitle());
            try {
                main.changeScene("StudentRecord.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals("StudentRecord", stage.getTitle());
            try {
                main.changeScene("Register.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals("Register", stage.getTitle());
            try {
                main.changeScene("Help.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals("Help", stage.getTitle());
            try {
                main.changeScene("Admin.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals("Admin", stage.getTitle());
            try {
                main.changeScene("Timetable.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals("Timetable", stage.getTitle());
        });
    }


}