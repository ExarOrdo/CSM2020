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

public class LoginControllerTest extends ApplicationTest {


    @Override
    public void start(Stage stage) {
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
    public void testLoginSuccess() {

        FxRobot robot = new FxRobot();
        robot.clickOn("#usernameTextField").write("12345");
        robot.clickOn("#passwordTextField").write("password");
        robot.clickOn("#loginBtn");
        assertEquals("Recommendation", Main.currentStage.getTitle());
    }

    @Test
    public void testLoginFailure() {
        FxRobot robot = new FxRobot();

        robot.clickOn("#usernameTextField").write("12345");
        robot.clickOn("#passwordTextField").write("wrongPassword");
        robot.clickOn("#loginBtn");
        Scene scene = Main.currentStage.getScene();
        Text incorrectField = (Text) scene.lookup("#incorrectField");
        assertTrue(incorrectField.isVisible());
    }

    @Test
    public void testRegisterButton(){
        FxRobot robot = new FxRobot();
        robot.clickOn("#registerBtn");
        assertEquals("Register", Main.currentStage.getTitle());


    }

}
