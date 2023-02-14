package dcs.aber.ac.uk.csm2020_group_3.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class RecommendationController {

    @FXML
    Pane expandedPane;

    @FXML
    Button burgerBtn;

    @FXML
    protected void pressBurgerBtn() {
        expandedPane.setVisible(!expandedPane.isVisible());
    }

}