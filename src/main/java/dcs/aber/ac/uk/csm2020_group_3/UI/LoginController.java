package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class LoginController {

    @FXML
    Button registerBtn;

    @FXML
    Button loginBtn;


    @FXML
    private void login() throws IOException {

        Main main = new Main();
        main.changeScene("Recommendation.fxml");

    }

    @FXML
    private void register() throws IOException {

        Main main = new Main();
        main.changeScene("Register.fxml");

    }

}