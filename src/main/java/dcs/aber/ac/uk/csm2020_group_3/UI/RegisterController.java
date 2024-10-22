package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.Register;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    public Button confirmBtn;
    public Button cancelBtn;
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
        yearComboBox.getItems().addAll("0", "1", "2", "3");
        courseComboBox.getItems().removeAll(courseComboBox.getItems());
        courseComboBox.getItems().addAll("G400", "G480");


        yearComboBox.setOnAction(actionEvent -> {
            String year = (String) yearComboBox.getValue();
            String course = (String) courseComboBox.getValue();


            //set selected index to 1 to avoid red errors
            courseComboBox.getSelectionModel().select(0);
        });

        courseComboBox.setOnAction(actionEvent -> {
            String course = (String) courseComboBox.getValue();

            //if course equals MASTER COURSE
            if (course.equals("G480")) {
                //if choose 4 then display only master courses
                yearComboBox.setDisable(true);
            } else if (course.equals("G400")) {
                yearComboBox.getSelectionModel().select(1);
                yearComboBox.setDisable(false);
            }


        });


    }

    @FXML
    private void cancel(ActionEvent actionEvent) throws IOException {

        Main main = new Main();
        main.changeScene("Login.fxml");

    }

    @FXML
    private void confirm() throws IOException, SQLException {

        Main main = new Main();
        Register register = new Register(studentId.getText(), password.getText(), firstName.getText(), lastName.getText(), yearComboBox, courseComboBox);

        if (!register.studentExists()) {
            alreadyExists.setText("Student ID already exists");
            alreadyExists.setVisible(true);
        } else if (!register.allFieldsFilled()) {
            alreadyExists.setText("All fields must be filled");
            alreadyExists.setVisible(true);

        } else {
            register.tryRegister();
            alreadyExists.setVisible(false);
            main.changeScene("Recommendation.fxml");
        }


    }

}
