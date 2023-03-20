package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.Main;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.Recommender;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

public class RecommendationController {

    private Recommender recommender;

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
    private void toAdmin() throws IOException {

        Main main = new Main();
        main.changeScene("Admin.fxml");

    }

    @FXML
    public void initialize() throws SQLException {
        recommender = new Recommender();
        recommender.getModuleData();
    }

}