package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;
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

    @FXML
    private void cancel(ActionEvent actionEvent) throws IOException {

        Main main = new Main();
        main.changeScene("Login.fxml");

    }

    @FXML
    private void confirm(ActionEvent actionEvent) throws IOException {

        Main main = new Main();
        main.changeScene("Recommendation.fxml");

    }
}
