package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AdminController {

    @FXML
    Pane expandedPane;
    @FXML
    Button burgerBtn;
    @FXML
    Button logoutBtn;
    @FXML
    ImageView burgerIcon;
    @FXML
    Button adminTimetableBtn;
    @FXML
    Button adminBtn;

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
    private void toAdminTimetable() throws IOException {

        Main main = new Main();
        main.changeScene("AdminTimetable.fxml");

    }

    @FXML
    private void toAdmin() {
        pressBurgerBtn();
    }


}
