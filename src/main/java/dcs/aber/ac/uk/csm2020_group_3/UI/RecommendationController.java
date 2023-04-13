package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.DatabaseHandler;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.ElectiveListGenerator;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.Module;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.Recommender;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.StrengthCalculator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.util.List;

import javafx.scene.text.Text;
import javafx.util.Callback;

public class RecommendationController implements Initializable {
    public ListView high;
    @FXML
    Pane categoryPane;
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
    Pane elective1, elective2, elective3;
    @FXML
    Button selectBtn, addModuleBtn;
    @FXML
    Button clear1, clear2, clear3;

    private Boolean oneElective, twoElective, threeElective = false;

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
    protected void pressSelectBtn() {
        categoryPane.setVisible(true);
    }

    @FXML
    protected void closeSelectPane() {
        categoryPane.setVisible(false);
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

        if (!elective1.isVisible() && !elective2.isVisible() && !elective3.isVisible()) {
            elective1.setVisible(true);
            closeSelectPane();
            oneElective = true;
            twoElective = false;
            threeElective = false;
        } else if (elective1.isVisible() && !elective2.isVisible() && !elective3.isVisible()) {
            elective2.setVisible(true);
            closeSelectPane();
            twoElective = true;
            oneElective = false;
            threeElective = false;
        } else if (elective1.isVisible() && elective2.isVisible() && !elective3.isVisible()) {
            elective3.setVisible(true);
            closeSelectPane();
            threeElective = true;
            oneElective = false;
            twoElective = false;
        }
    }

    @FXML
    private void clearElective() {

        if (oneElective) {
            elective1.setVisible(false);
            oneElective = false;
            twoElective = false;
            threeElective = false;
        } else if (twoElective) {
            elective2.setVisible(false);
            twoElective = false;
            oneElective = true;
            threeElective = false;
        } else if (threeElective) {
            elective3.setVisible(false);
            threeElective = false;
            oneElective = false;
            twoElective = true;
        }
    }

    @FXML
    private TextArea coreModule1, coreModule2, coreModule3, semester1, semester2, semester3, credits1, credits2, credits3;

    private void displayCoreModules() {
        String studentID = DatabaseHandler.getCurrentStudentId(); // Use the logged-in student's ID
        Recommender recommender = new Recommender(studentID);
        List<Module> coreModules = recommender.getCoreList();

        if (coreModules.size() >= 1) {
            coreModule1.setText(coreModules.get(0).getName());
            semester1.setText("Sem " + coreModules.get(0).getSemester());
            credits1.setText(coreModules.get(0).getCredits() + " Credits");
        }
        if (coreModules.size() >= 2) {
            coreModule2.setText(coreModules.get(1).getName());
            semester2.setText("Sem " + coreModules.get(1).getSemester());
            credits2.setText(coreModules.get(1).getCredits() + " Credits");
        }
        if (coreModules.size() >= 3) {
            coreModule3.setText(coreModules.get(2).getName());
            semester3.setText("Sem " + coreModules.get(2).getSemester());
            credits3.setText(coreModules.get(2).getCredits() + " Credits");
        }
    }

    @FXML
    private ListView<String> highView, mediumView, lowView, allElectivesList;

    /**
     * Takes lists of modules sorted by weight, gets their weight and puts into ListView variables for javaFX
     */

    private void displayRecommendations() {
        /*
        highView = new ListView<>();
        mediumView = new ListView<>();
        lowView = new ListView<>();
*/
        //high = StrengthCalculator.highStrength;
        //medium = StrengthCalculator.mediumStrength;
        //low = StrengthCalculator.lowStrength;

        // Would be nice to continue working with Module objects the entire way if possible!

        // loop through array lists, add to listview
        for (int i = 0; i < StrengthCalculator.highStrength.size(); i++) {
            highView.getItems().add(StrengthCalculator.highStrength.get(i).getName());

        }
        for (int j = 0; j < StrengthCalculator.mediumStrength.size(); j++) {
            mediumView.getItems().add(StrengthCalculator.mediumStrength.get(j).getName());

        }

        for (int k = 0; k < StrengthCalculator.lowStrength.size(); k++) {
            lowView.getItems().add(StrengthCalculator.lowStrength.get(k).getName());

        }
    }

    private void listViewWrapText(ListView<String> listView){
        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty && item != null) {
                            Text text = new Text(item);
                            text.setWrappingWidth(listView.getWidth() - 20);
                            setGraphic(text);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    @FXML
    private TextField searchBar;
    private ArrayList<String> searchResult = new ArrayList<>();
    private String lastSearchState = "";
    private final ArrayList<String> electiveNames = new ArrayList<>();

    public void search(KeyEvent event) throws IOException {
        if (searchBar.getLength() >= lastSearchState.length() && lastSearchState.length() != 0) {
            searchResult = updateSearchBar(searchResult, searchBar.getText());
        } else {
            searchResult = updateSearchBar(electiveNames, searchBar.getText());
        }
        lastSearchState = searchBar.getText();
        ObservableList<String> searchList = FXCollections.observableArrayList(searchResult);
        allElectivesList.setItems(searchList);
    }

    public static ArrayList<String> updateSearchBar(ArrayList<String> modules, String searchString) {
        ArrayList<String> searchResult = new ArrayList<>();
        for (String name : modules) {
            if (name.toLowerCase().startsWith(searchString.toLowerCase())) searchResult.add(name);
        }
        return searchResult;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayCoreModules();
        displayRecommendations();
        listViewWrapText(highView);
        listViewWrapText(mediumView);
        listViewWrapText(lowView);
        for (int i = 0; i < ElectiveListGenerator.electiveModulesList.size(); i++) {
            allElectivesList.getItems().add(ElectiveListGenerator.electiveModulesList.get(i).getName());
            electiveNames.add(ElectiveListGenerator.electiveModulesList.get(i).getName());
        }
    }
}