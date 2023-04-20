package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Login;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginController {

    private static String loginID;

    public static String getLoginID() {
        return loginID;
    }

    @FXML
    Button registerBtn, loginBtn, adminBtn;

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

        if (login.allFieldsFilled()) {
            if (login.tryLogin()) {
                incorrectField.setVisible(false);
                Login.setCurrentStudentId(studentId.getText()); // Store the logged-in student's ID
                loginID = studentId.getText();
                main.changeScene("Recommendation.fxml");
            } else {
                incorrectField.setText("Incorrect Student ID or Password");
                incorrectField.setVisible(true);
            }
        } else {
            incorrectField.setText("All fields must be filled");
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