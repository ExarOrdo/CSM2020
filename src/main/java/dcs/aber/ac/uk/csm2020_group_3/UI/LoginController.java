package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Login;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    Button registerBtn;

    @FXML
    Button loginBtn;

    @FXML
    TextField studentPassword;

    @FXML
    TextField studentId;

    @FXML
    private void login() throws IOException {

        Main main = new Main();
        Login test = new Login();

        if(test.Login(studentId.getText(), studentPassword.getText())) {
            main.changeScene("Recommendation.fxml");

            System.out.println("asdasd");
        }


    }

    @FXML
    private void register() throws IOException {

        Main main = new Main();
        main.changeScene("Register.fxml");

    }

}