package dcs.aber.ac.uk.csm2020_group_3;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class AdminLoginControllerTest extends ApplicationTest{
    @Override
    public void start(Stage stage) {
        Thread thread = new Thread(() -> Platform.runLater(() -> {
            try {
                new Main().start(new Stage());
                Main main = new Main();
                main.changeScene("AdminLogin.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        thread.start();
    }

    @Test
    void testBackToLogin() {
        FxRobot robot = new FxRobot();
        robot.clickOn("#backBtn");
        assertEquals("Login", Main.currentStage.getTitle());

    }

    @Test
    void testToAdmin(){

        FxRobot robot = new FxRobot();
        robot.clickOn("#adminId").write("admin");
        robot.clickOn("#adminPassword").write("admin");
        robot.clickOn("#adminLoginBtn");
        assertEquals("Admin", Main.currentStage.getTitle());
    }
    @Test
    public void testLoginFailure() {
        FxRobot robot = new FxRobot();

        robot.clickOn("#adminId").write("12345");
        robot.clickOn("#adminPassword").write("wrongPassword");
        robot.clickOn("#adminLoginBtn");
        Scene scene = Main.currentStage.getScene();
        Text incorrectField = (Text) scene.lookup("#incorrectField");
        assertTrue(incorrectField.isVisible());
    }
}