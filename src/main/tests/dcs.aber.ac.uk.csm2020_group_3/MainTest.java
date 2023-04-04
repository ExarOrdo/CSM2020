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
        Thread thread = new Thread(() -> Platform.runLater(() -> {
            try {
                new Main().start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        thread.start();
    }

    @Test
    void testStart() {
        assertEquals("Login", Main.currentStage.getTitle());

    }

    @Test
    void testChangeScene() {
        Main main = new Main();
        Platform.runLater(() -> {
            try {
                main.changeScene("Login.fxml");
                assertEquals("Login", Main.currentStage.getTitle());
                System.out.println("Current scene: " + Main.currentStage.getTitle());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                main.changeScene("Recommendation.fxml");
                assertEquals("Recommendation", Main.currentStage.getTitle());
                System.out.println("Current scene: " + Main.currentStage.getTitle());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                main.changeScene("StudentRecord.fxml");
                assertEquals("StudentRecord", Main.currentStage.getTitle());
                System.out.println("Current scene: " + Main.currentStage.getTitle());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                main.changeScene("Register.fxml");
                assertEquals("Register", Main.currentStage.getTitle());
                System.out.println("Current scene: " + Main.currentStage.getTitle());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                main.changeScene("Help.fxml");
                assertEquals("Help", Main.currentStage.getTitle());
                System.out.println("Current scene: " + Main.currentStage.getTitle());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            try {
                main.changeScene("Admin.fxml");
                assertEquals("Admin", Main.currentStage.getTitle());
                System.out.println("Current scene: " + Main.currentStage.getTitle());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                main.changeScene("Timetable.fxml");
                assertEquals("Timetable", Main.currentStage.getTitle());
                System.out.println("Current scene: " + Main.currentStage.getTitle());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}