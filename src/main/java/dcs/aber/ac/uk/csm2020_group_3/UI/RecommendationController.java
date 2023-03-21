package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class RecommendationController {
    @FXML
    Button adminBtn;
    @FXML
    Button enterModuleBtn;
    @FXML
    Pane electivePane;
    @FXML
    ComboBox futureModulesDropDown;
    @FXML
    Pane expandedPane;
    @FXML
    Button burgerBtn;
    @FXML
    Button logoutBtn;
    @FXML
    ImageView burgerIcon;
    @FXML
    Button studentRecordBtn;
    @FXML
    Button helpBtn;
    @FXML
    Button timetableBtn;
    @FXML
    Button recommendationBtn;
    @FXML
    Pane elective1;
    @FXML
    Pane elective2;
    @FXML
    Pane elective3;
    @FXML
    Button confirmModuleBtn;

    @FXML
    protected void pressBurgerBtn() {
        expandedPane.setVisible(!expandedPane.isVisible());
        if (expandedPane.isVisible()) {
            burgerIcon.setRotate(90);
        } else {
            burgerIcon.setRotate(0);
        }
    }

    @FXML
    protected void pressEnterModuleBtn() {
        electivePane.setVisible(true);
    }

    @FXML
    protected void closeEnterModulePane() {
        electivePane.setVisible(false);
    }

    @FXML
    private void logout() throws IOException {

        Main main = new Main();
        main.changeScene("Login.fxml");

    }

    @FXML
    private void toStudentRecord() throws IOException {

        Main main = new Main();
        main.changeScene("StudentRecord.fxml");

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
    private void toRecommendation() {
        pressBurgerBtn();
    }

    @FXML
    private void showElective() {

        if (elective1.isVisible() && elective2.isVisible()) {
            elective3.setVisible(true);
            enterModuleBtn.setVisible(false);
            closeEnterModulePane();
        }

        if (!elective1.isVisible()) {
            elective1.setVisible(true);
            enterModuleBtn.setLayoutY(250);
            closeEnterModulePane();
        } else if (elective1.isVisible()) {
            elective2.setVisible(true);
            enterModuleBtn.setLayoutY(295);
            closeEnterModulePane();
        }

    }
}

