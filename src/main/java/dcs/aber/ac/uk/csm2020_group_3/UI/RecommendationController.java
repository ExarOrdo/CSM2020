package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.IOException;

public class RecommendationController {
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
    protected void pressBurgerBtn() {
        expandedPane.setVisible(!expandedPane.isVisible());
        if(expandedPane.isVisible()){
            burgerIcon.setRotate(90);
        }else {
            burgerIcon.setRotate(0);
        }
    }

    @FXML
    protected void pressEnterModuleBtn() {
        electivePane.setVisible(true);
    }

    @FXML
    protected void closeEnterModulePane(){
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
    private ListView<String> electiveListView;

    // Other variables and methods

    @FXML
    public void initialize() {
        populateElectiveListView();
    }

    private void populateElectiveListView() {
        ObservableList<String> moduleList = FXCollections.observableArrayList(
                "MSC Project",
                "Introduction to Computer Infrastructure",
                "Problems and Solutions",
                "Fundamentals of Web Development",
                "Information security",
                "Introduction to Programming",
                "Programming Using an Object-Oriented Language",
                "Professional and Personal Development",
                "Algorithm Design and Data Structures",
                "Software Engineering",
                "Robotics and Embedded Systems",
                "Modelling Persistent Data",
                "Agile Development and Testing",
                "Computational Bioinformatics",
                "Mobile Development with Android",
                "Advanced Algorithms",
                "Computer Graphics and Games",
                "Professional Issues in the Computing Industry",
                "Major Project",
                "Programming for Scientists",
                "Agile Software Development Project",
                "Modelling, Managing and Securing Data",
                "Fundamentals of Intelligent Systems",
                "Advanced Data Analytics",
                "Research Topics in Computing"
        );

        electiveListView.setItems(moduleList);
    }
}