package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Register;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController {
    @FXML
    ComboBox yearComboBox;
    @FXML
    TextField password;

    @FXML
    TextField studentId;
    @FXML
    ComboBox courseComboBox;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;

    @FXML
    public void initialize() {
        yearComboBox.getItems().removeAll(yearComboBox.getItems());
        yearComboBox.getItems().addAll("0", "1", "2", "3");
    }

    @FXML
    private void cancel(ActionEvent actionEvent) throws IOException {

        Main main = new Main();
        main.changeScene("Login.fxml");

    }

    @FXML
    private void confirm(ActionEvent actionEvent) throws IOException, SQLException {

        Main main = new Main();
        Register register = new Register(studentId.getText(), password.getText(), firstName.getText(),lastName.getText(),Integer.parseInt(yearComboBox.getValue().toString()), courseComboBox.getValue().toString());
        if (register.tryRegister()){
            main.changeScene("Recommendation.fxml");
            System.out.println("Registered successfully.");
        }

    }
}
