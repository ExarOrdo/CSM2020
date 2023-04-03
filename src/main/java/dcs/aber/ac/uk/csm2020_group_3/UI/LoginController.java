package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Login;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordCreator;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    Button registerBtn, loginBtn, adminBtn;

    @FXML
    PasswordField studentPassword;

    @FXML
    TextField studentId;
    @FXML
    Text incorrectField;

    @FXML
    private void login() throws IOException, SQLException {

        Main main = new Main();
        Login login = new Login(studentId.getText(), studentPassword.getText());

        if (login.tryLogin()) {
            incorrectField.setVisible(false);
            RecordCreator.setCurrentStudentId(Integer.parseInt(studentId.getText())); // Store the logged-in student's ID
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

    @FXML
    private void adminLogin() throws IOException {

        Main main = new Main();
        main.changeScene("AdminLogin.fxml");

    }
}