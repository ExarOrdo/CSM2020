package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.DatabaseHandler;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Login;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordCreator;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginController {

    @FXML
    Button registerBtn;

    @FXML
    Button loginBtn;

    @FXML
    PasswordField studentPassword;

    @FXML
    TextField studentId;
    @FXML
    Text incorrectField;

    @FXML
    private void login() throws IOException {

        Main main = new Main();
        Login login = new Login(studentId.getText(), studentPassword.getText());

        if (login.tryLogin()) {
            incorrectField.setVisible(false);
            Login.setCurrentStudentId(studentId.getText()); // Store the logged-in student's ID

            main.changeScene("Recommendation.fxml");
            System.out.println("Successful login");
        } else {
            incorrectField.setVisible(true);
        }


    }

    @FXML
    private void register() throws IOException {

        Main main = new Main();
        main.changeScene("Register.fxml");

    }
}