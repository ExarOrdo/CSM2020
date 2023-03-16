package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Login;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

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
    private void login() throws IOException, SQLException {

        Main main = new Main();
        Login login = new Login(studentId.getText(), studentPassword.getText());

        if(login.tryLogin()) {
            main.changeScene("Recommendation.fxml");

            System.out.println("Successful login");
        }


    }

    @FXML
    private void register() throws IOException {

        Main main = new Main();
        main.changeScene("Register.fxml");

    }

}