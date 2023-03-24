package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Register;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.controlsfx.control.action.Action;

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
    Text alreadyExists;


    public void initialize() {
        yearComboBox.getItems().removeAll(yearComboBox.getItems());
        yearComboBox.getItems().addAll("1", "2", "3", "4");
    }

    @FXML
    private void cancel(ActionEvent actionEvent) throws IOException {

        Main main = new Main();
        main.changeScene("Login.fxml");

    }

    @FXML
    private void confirm(ActionEvent actionEvent) throws IOException, SQLException {

        Main main = new Main();
        Register register = new Register(studentId.getText(), password.getText(), firstName.getText(),lastName.getText(),yearComboBox, courseComboBox);

        if (!register.studentExists()){
            alreadyExists.setText("Student ID already exists");
            alreadyExists.setVisible(true);
        }
        else if(!register.allFieldsFilled()) {
            alreadyExists.setText("All fields must be filled");
            alreadyExists.setVisible(true);

        }
        else{
            register.tryRegister();
            alreadyExists.setVisible(false);
            main.changeScene("Recommendation.fxml");
            System.out.println("Registered successfully.");
        }


    }

}
