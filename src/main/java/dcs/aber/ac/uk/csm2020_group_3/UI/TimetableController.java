package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.Main;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.CoreListGenerator;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.ElectiveListGenerator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimetableController implements Initializable {

    public GridPane timetable;
    public TextArea textArea00 ,textArea10 ,textArea20 ,textArea30 ,textArea40 ,textArea01 ,textArea11 ,textArea21 ,textArea31,textArea41 ,textArea02 ,textArea12 ,textArea22 ,textArea32 ,textArea42 ,textArea03 ,textArea13 ,textArea23 ,textArea33 , textArea43 ,textArea04 ,textArea14 ,textArea24 ,textArea34 ,textArea44 ,textArea05 ,textArea15 ,textArea25 ,textArea35 ,textArea45,textArea06 ,textArea16 ,textArea26 ,textArea36 ,textArea46 ,textArea07 ,textArea17 ,textArea27 ,textArea37 ,textArea47;
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
    private final ArrayList<String> allModules = new ArrayList<>();

    private final ArrayList<TextArea> allTextAreas = new ArrayList<>();

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

    private void populateTimetable(){

        allTextAreas.add(0,textArea00);
        allTextAreas.add(1,textArea10);
        allTextAreas.add(2,textArea20);
        allTextAreas.add(3,textArea30);
        allTextAreas.add(4,textArea40);
        allTextAreas.add(5,textArea01);
        allTextAreas.add(6,textArea11);
        allTextAreas.add(7,textArea21);
        allTextAreas.add(8,textArea31);
        allTextAreas.add(9,textArea41);
        allTextAreas.add(10,textArea02);
        allTextAreas.add(11,textArea12);
        allTextAreas.add(12,textArea22);
        allTextAreas.add(13,textArea32);
        allTextAreas.add(14,textArea42);
        allTextAreas.add(15,textArea03);
        allTextAreas.add(16,textArea13);
        allTextAreas.add(17,textArea23);
        allTextAreas.add(18,textArea33);
        allTextAreas.add(19,textArea43);
        allTextAreas.add(20,textArea04);
        allTextAreas.add(21,textArea14);
        allTextAreas.add(22,textArea24);
        allTextAreas.add(23,textArea34);
        allTextAreas.add(24,textArea44);
        allTextAreas.add(25,textArea05);
        allTextAreas.add(26,textArea15);
        allTextAreas.add(27,textArea25);
        allTextAreas.add(28,textArea35);
        allTextAreas.add(29,textArea45);
        allTextAreas.add(30,textArea06);
        allTextAreas.add(31,textArea16);
        allTextAreas.add(32,textArea26);
        allTextAreas.add(33,textArea36);
        allTextAreas.add(34,textArea46);
        allTextAreas.add(35,textArea07);
        allTextAreas.add(36,textArea17);
        allTextAreas.add(37,textArea27);
        allTextAreas.add(38,textArea37);
        allTextAreas.add(39,textArea47);

        for (int i = 0; i < allModules.size(); i++) {
            allTextAreas.get(i).setText(allModules.get(i));
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < ElectiveListGenerator.electiveModulesList.size(); i++) {
            electiveNames.add(ElectiveListGenerator.electiveModulesList.get(i).getName());
        }
        for (int i = 0; i < CoreListGenerator.coreModulesList.size(); i++) {
            coreNames.add(CoreListGenerator.coreModulesList.get(i).getName());
        }
        allModules.addAll(coreNames);
        allModules.addAll(electiveNames);

        populateTimetable();
    }
}
