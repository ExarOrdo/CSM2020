package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Login;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class AdminLoginController {

    public TextField adminId;
    public PasswordField adminPassword;
    public Text incorrectField;
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
        Login login = new Login(adminId.getText(), adminPassword.getText());
        if (login.allFieldsFilled()) {
            if (login.tryLogin()) {
                incorrectField.setVisible(false);
                main.changeScene("Admin.fxml");
            } else {
                incorrectField.setText("Incorrect Admin ID or Password");
                incorrectField.setVisible(true);
            }
        } else {
            incorrectField.setText("All fields must be filled");
            incorrectField.setVisible(true);
        }
    }
}
