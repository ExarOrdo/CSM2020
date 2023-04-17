package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminLoginController {

    @FXML
    Button backBtn, adminLoginBtn;

    @FXML
    private void backToLogin() throws IOException {
        Main main = new Main();
        main.changeScene("Login.fxml");
    }

    @FXML
    private void toAdmin() throws IOException {
        Main main = new Main();
        main.changeScene("Admin.fxml");
    }
}
