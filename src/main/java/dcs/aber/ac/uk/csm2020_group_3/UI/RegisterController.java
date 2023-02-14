package dcs.aber.ac.uk.csm2020_group_3.UI;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController {
    @FXML
    ComboBox yearComboBox;

    @FXML
    public void initialize() {
        yearComboBox.getItems().removeAll(yearComboBox.getItems());
        yearComboBox.getItems().addAll("0", "1", "2", "3");
    }
}
