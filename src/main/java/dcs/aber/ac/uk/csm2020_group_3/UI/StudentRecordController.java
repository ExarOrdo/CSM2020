package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class StudentRecordController {

    @FXML
    Button adminBtn;
    @FXML
    Pane expandedPane;
    @FXML
    Button burgerBtn;
    @FXML
    Button logoutBtn;
    @FXML
    ImageView burgerIcon;
    @FXML
    Button recommendationBtn;
    @FXML
    Button helpBtn;
    @FXML
    Button timetableBtn;
    @FXML
    Button studentRecordBtn;

    @FXML
    protected void pressBurgerBtn() {
        expandedPane.setVisible(!expandedPane.isVisible());
        if(expandedPane.isVisible()){
            burgerIcon.setRotate(90);
        }else {
            burgerIcon.setRotate(0);
        }
    }

    @FXML
    private void logout() throws IOException {

        Main main = new Main();
        main.changeScene("Login.fxml");

    }

    @FXML
    private void toRecommendation() throws IOException {

        Main main = new Main();
        main.changeScene("Recommendation.fxml");

    }

    @FXML
    private void toAdmin() throws IOException {

        Main main = new Main();
        main.changeScene("Admin.fxml");

    }

    @FXML
    private void toHelp() throws IOException {

        Main main = new Main();
        main.changeScene("Help.fxml");

    }

    @FXML
    private void toTimetable() throws IOException {

        Main main = new Main();
        main.changeScene("Timetable.fxml");

    }
    @FXML
    private void toStudentRecord() {
        pressBurgerBtn();
    }
}
