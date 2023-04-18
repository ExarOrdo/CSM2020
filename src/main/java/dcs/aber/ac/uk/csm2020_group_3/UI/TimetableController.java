package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.Main;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.CoreListGenerator;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.ElectiveListGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimetableController implements Initializable {
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
    Button studentRecordBtn;
    @FXML
    Button helpBtn;
    @FXML
    Button timetableBtn;
    private final ArrayList<String> electiveNames = new ArrayList<>();
    private final ArrayList<String> coreNames = new ArrayList<>();

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
    private void toStudentRecord() throws IOException {

        Main main = new Main();
        main.changeScene("StudentRecord.fxml");

    }

    @FXML
    private void toHelp() throws IOException {

        Main main = new Main();
        main.changeScene("Help.fxml");

    }

    @FXML
    private void toTimetable() {
        pressBurgerBtn();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < ElectiveListGenerator.electiveModulesList.size(); i++) {
            electiveNames.add(ElectiveListGenerator.electiveModulesList.get(i).getName());
        }
        for (int i = 0; i < CoreListGenerator.coreModulesList.size(); i++) {
            coreNames.add(CoreListGenerator.coreModulesList.get(i).getName());
        }
        System.out.println(electiveNames);
        System.out.println(coreNames);
    }
}
