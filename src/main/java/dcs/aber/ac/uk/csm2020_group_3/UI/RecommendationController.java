package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.DatabaseHandler;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordLoader;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.Module;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class RecommendationController implements Initializable {

    public TextArea elective1Name;
    public TextArea elective1Sem;
    public TextArea elective1Cred;
    public TextArea elective2Name;
    public TextArea elective2Sem;
    public TextArea elective2Cred;
    public TextArea elective3Name;
    public TextArea elective3Sem;
    public TextArea elective3Cred;
    public RadioButton tag2, tag3, tag4, tag5, tag6, tag7, tag8;
    public RadioButton tag1;
    public Text currentYear;
    public Button confirmSearchSelection;
    private Recommender recommender;

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

    /**
     * Method for opening and closing the navigation pane
     */
    @FXML
    protected void pressBurgerBtn() {
        expandedPane.setVisible(!expandedPane.isVisible());
        if (expandedPane.isVisible()) {
            burgerIcon.setRotate(90);
        } else {
            burgerIcon.setRotate(0);
        }
    }

    /**
     * Method for opening the pane to search for modules
     */
    @FXML
    protected void pressSelectBtn() {
        categoryPane.setVisible(true);
    }

    /**
     * Method that closes search modules pane
     */
    @FXML
    protected void closeSelectPane() {
        categoryPane.setVisible(false);
    }

    /**
     * Method for returning to login screen
     * @throws IOException
     */
    @FXML
    private void logout() throws IOException {

        Main main = new Main();
        main.changeScene("Login.fxml");

    }

    /**
     * Method for changing screen to student record
     * @throws IOException
     */
    @FXML
    private void toStudentRecord() throws IOException {

        Main main = new Main();
        main.changeScene("StudentRecord.fxml");

    }

    /**
     * Method for changing screen to help page
     * @throws IOException
     */
    @FXML
    private void toHelp() throws IOException {

        Main main = new Main();
        main.changeScene("Help.fxml");

    }

    /**
     * Method for changing screen to timetable
     * @throws IOException
     */
    @FXML
    private void toTimetable() throws IOException {

        Main main = new Main();
        main.changeScene("Timetable.fxml");

    }

    /**
     * Method for changing screen to recommendation page
     */
    @FXML
    private void toRecommendation() {
        pressBurgerBtn();
    }

    /**
     * Method to show the correct elective box based on how many are currently active
     */
    @FXML
    private void showElective() {
        onElectiveChosen();
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
    private void clearElective(String electiveToClear) {
        addModuleBtn.setVisible(true);
        // get Module selectedModule from ModuleName string
        String selectedElective = electiveToClear;
        Module selectedModule = null;


        for (int i = 0; i < ModuleHandler.selectedModules.size(); i++) {
            if (selectedElective.equals(ModuleHandler.selectedModules.get(i).getName())) {
                selectedModule = ModuleHandler.selectedModules.get(i);
            }
        }

        // add chosenElective back to electiveList
        ModuleHandler.selectedModules.remove(selectedModule);
        ElectiveListGenerator.electiveModulesList.add(selectedModule);

        // recalculate weights
        recommender.recalculateWeightsOnRemove();

        // update UI
        highView.getItems().clear();
        mediumView.getItems().clear();
        lowView.getItems().clear();
        displayRecommendations();

    }

    public void clearElectiveOne() {
        if (oneElective) {
            elective1.setVisible(false);
            twoElective = false;
            oneElective = false;
            threeElective = false;
            clearElective(elective1Name.getText());
        } else if (twoElective) {
            elective2.setVisible(false);
            twoElective = false;
            oneElective = true;
            threeElective = false;
            clearElective(elective1Name.getText());
            elective1Name.setText(elective2Name.getText());
            elective1Sem.setText(elective2Sem.getText());
            elective1Cred.setText(elective2Cred.getText());

        } else if (threeElective) {
            elective3.setVisible(false);
            threeElective = false;
            oneElective = false;
            twoElective = true;


            clearElective(elective1Name.getText());
            elective1Name.setText(elective2Name.getText());
            elective1Sem.setText(elective2Sem.getText());
            elective1Cred.setText(elective2Cred.getText());

            elective2Name.setText(elective3Name.getText());
            elective2Sem.setText(elective3Sem.getText());
            elective2Cred.setText(elective3Cred.getText());


        }
    }



    public void clearElectiveTwo() {
        if (twoElective) {
            elective2.setVisible(false);
            twoElective = false;
            oneElective = true;
            threeElective = false;
            clearElective(elective2Name.getText());

        } else if (threeElective) {
            elective3.setVisible(false);
            threeElective = false;
            oneElective = false;
            twoElective = true;

            clearElective(elective2Name.getText());
            elective2Name.setText(elective3Name.getText());
            elective2Sem.setText(elective3Sem.getText());
            elective2Cred.setText(elective3Cred.getText());

        }

    }

    public void clearElectiveThree() {
        elective3.setVisible(false);
        threeElective = false;
        oneElective = false;
        twoElective = true;
        clearElective(elective3Name.getText());
    }


    @FXML
    private TextArea coreModule1, coreModule2, coreModule3, semester1, semester2, semester3, credits1, credits2, credits3;

    private void displayCoreModules() throws SQLException {
        RecordLoader recordLoader = new RecordLoader();
        int year = recordLoader.getStudentYear(DatabaseHandler.getCurrentStudentId());
        if (year == 1) {
            if (ModuleHandler.year1Modules.size() >= 1) {
                coreModule1.setText(ModuleHandler.year1Modules.get(0).getName());
                semester1.setText("Sem " + ModuleHandler.year1Modules.get(0).getSemester());
                credits1.setText(ModuleHandler.year1Modules.get(0).getCredits() + " Credits");
            }
            if (ModuleHandler.year1Modules.size() >= 2) {
                coreModule2.setText(ModuleHandler.year1Modules.get(1).getName());
                semester2.setText("Sem " + ModuleHandler.year1Modules.get(1).getSemester());
                credits2.setText(ModuleHandler.year1Modules.get(1).getCredits() + " Credits");
            }
            if (ModuleHandler.year1Modules.size() >= 3) {
                coreModule3.setText(ModuleHandler.year1Modules.get(2).getName());
                semester3.setText("Sem " + ModuleHandler.year1Modules.get(2).getSemester());
                credits3.setText(ModuleHandler.year1Modules.get(2).getCredits() + " Credits");
            }
        } else if (year == 2) {
            if (ModuleHandler.year2Modules.size() >= 1) {
                coreModule1.setText(ModuleHandler.year2Modules.get(0).getName());
                semester1.setText("Sem " + ModuleHandler.year2Modules.get(0).getSemester());
                credits1.setText(ModuleHandler.year2Modules.get(0).getCredits() + " Credits");
            }
            if (ModuleHandler.year2Modules.size() >= 2) {
                coreModule2.setText(ModuleHandler.year2Modules.get(1).getName());
                semester2.setText("Sem " + ModuleHandler.year2Modules.get(1).getSemester());
                credits2.setText(ModuleHandler.year2Modules.get(1).getCredits() + " Credits");
            }
            if (ModuleHandler.year2Modules.size() >= 3) {
                coreModule3.setText(ModuleHandler.year2Modules.get(2).getName());
                semester3.setText("Sem " + ModuleHandler.year2Modules.get(2).getSemester());
                credits3.setText(ModuleHandler.year2Modules.get(2).getCredits() + " Credits");
            }
        } else if (year == 3) {
            if (ModuleHandler.year3Modules.size() >= 1) {
                coreModule1.setText(ModuleHandler.year3Modules.get(0).getName());
                semester1.setText("Sem " + ModuleHandler.year3Modules.get(0).getSemester());
                credits1.setText(ModuleHandler.year3Modules.get(0).getCredits() + " Credits");
            }
            if (ModuleHandler.year3Modules.size() >= 2) {
                coreModule2.setText(ModuleHandler.year3Modules.get(1).getName());
                semester2.setText("Sem " + ModuleHandler.year3Modules.get(1).getSemester());
                credits2.setText(ModuleHandler.year3Modules.get(1).getCredits() + " Credits");
            }
            if (ModuleHandler.year3Modules.size() >= 3) {
                coreModule3.setText(ModuleHandler.year3Modules.get(2).getName());
                semester3.setText("Sem " + ModuleHandler.year3Modules.get(2).getSemester());
                credits3.setText(ModuleHandler.year3Modules.get(2).getCredits() + " Credits");
            }
        }
    }

    private void createRecommender() {
        String studentID = DatabaseHandler.getCurrentStudentId(); // Use the logged-in student's ID
        recommender = new Recommender(studentID);
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

    /**
     * Bunch of functions that run when an elective is chosen in the UI.
     */

    private void listViewWrapText(ListView<String> listView) {
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
            searchResult = getSearch(searchResult, searchBar.getText());
        } else {
            searchResult = getSearch(electiveNames, searchBar.getText());
        }
        lastSearchState = searchBar.getText();
        ObservableList<String> searchList = FXCollections.observableArrayList(searchResult);
        allElectivesList.setItems(searchList);
    }


    public static ArrayList<String> getSearch(ArrayList<String> modules, String searchString) {
        ArrayList<String> searchResult = new ArrayList<>();
        for (String name : modules) {
            if (name.toLowerCase().startsWith(searchString.toLowerCase())) searchResult.add(name);
        }
        return searchResult;
    }

    public void moduleNameToModule() {

    }

    /**
     * Bunch of functions that run when an elective is chosen in the UI.
     */
    private void onElectiveChosen() {


        // get module/modules chosen
        // check prereq
        // checks credits
        // sorts into list
        String selectedElective = "";
        Module selectedModule = null;

        if (highView.getSelectionModel().getSelectedItem() != null) {
            selectedElective = highView.getSelectionModel().getSelectedItem();

        } else if (mediumView.getSelectionModel().getSelectedItem() != null) {
            selectedElective = mediumView.getSelectionModel().getSelectedItem();
        } else if (lowView.getSelectionModel().getSelectedItem() != null) {
            selectedElective = lowView.getSelectionModel().getSelectedItem();
        } else if (allElectivesList.getSelectionModel().getSelectedItem() != null) {
            selectedElective = allElectivesList.getSelectionModel().getSelectedItem();

        }
        highView.getSelectionModel().clearSelection();
        mediumView.getSelectionModel().clearSelection();
        lowView.getSelectionModel().clearSelection();


        //iterate across currently selected modules
        for (int i = 0; i < ElectiveListGenerator.electiveModulesList.size(); i++) {
            if (selectedElective.equals(ElectiveListGenerator.electiveModulesList.get(i).getName())) {
                selectedModule = ElectiveListGenerator.electiveModulesList.get(i);
            }
        }


        assert selectedModule != null;

        // check chosen module for prerequisites
        // added chosen modules and any prerequisites to list newlyAddedModules
        recommender.checkPrerequisites(selectedModule);                             // return newlyAddedModules

        // for each module and it's prereqs.
        for (int i = 0; i < ModuleHandler.selectedModules.size(); i++) {


            // sort modules into yearXModules.
            recommender.sortModules(ModuleHandler.selectedModules.get(i));

            // check credit limit for each year.
            if (recommender.checkCredits().equals(Boolean.FALSE)) {

                // remove selected module + any prereqs from lists.
                ElectiveListGenerator.electiveModulesList.removeAll(ModuleHandler.selectedModules);

                // UI message
                // failed to add message due to credit limit
            }

            // if credit check suceeds
            else {

                // remove module from electiveList
                ElectiveListGenerator.electiveModulesList.remove(ModuleHandler.selectedModules.get(i));
            }

        }

        recommender.recalculateWeightsOnAdd();


        if (!elective1.isVisible() && !elective2.isVisible() && !elective3.isVisible()) {
            elective1Name.setText(String.valueOf(selectedModule.getName()));
            elective1Sem.setText("Sem " + selectedModule.getSemester());
            elective1Cred.setText(String.valueOf(selectedModule.getCredits()));
        } else if (elective1.isVisible() && !elective2.isVisible() && !elective3.isVisible()) {
            elective2Name.setText(String.valueOf(selectedModule.getName()));
            elective2Sem.setText("Sem " + selectedModule.getSemester());
            elective2Cred.setText(String.valueOf(selectedModule.getCredits()));
            addModuleBtn.setVisible(true);
        } else if (elective1.isVisible() && elective2.isVisible() && !elective3.isVisible()) {
            elective3Name.setText(String.valueOf(selectedModule.getName()));
            elective3Sem.setText("Sem " + selectedModule.getSemester());
            elective3Cred.setText(String.valueOf(selectedModule.getCredits()));
            addModuleBtn.setVisible(false);
        }

        // display new electives in UI
        highView.getItems().clear();
        mediumView.getItems().clear();
        lowView.getItems().clear();
        allElectivesList.getItems().remove(selectedModule.getName());
        displayRecommendations();
        // ElectiveListGenerator.electiveList should be updated above by 'sortModules'
        // strengthCalculator then orders into separate lists.
        // show new weights in UI

        // set newlyAddedModules to empty again.
    }


    @FXML
    private Button confirmButton;

    public void confirm() {
        List<String> selectedElectiveIds = new ArrayList<>();
        selectedElectiveIds.add(elective1Name.getText());
        selectedElectiveIds.add(elective2Name.getText());
        selectedElectiveIds.add(elective3Name.getText());


        if (selectedElectiveIds.stream().anyMatch(s -> s.equals("Module Name"))) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select three modules.");
            return;
        }

        if (selectedElectiveIds.stream().filter(s -> !s.equals("Module Name")).distinct().count() != selectedElectiveIds.size()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Duplicate modules are not allowed.");
            return;
        }

        String studentID = DatabaseHandler.getCurrentStudentId();

        DataLoader dataLoader = new DataLoader();
        List<String> alreadyExistingModules = dataLoader.saveSelectedModules(studentID, selectedElectiveIds);


        Alert alert = new Alert(alreadyExistingModules.isEmpty() ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(alreadyExistingModules.isEmpty() ? "Success" : "Error");
        alert.setHeaderText(null);

        if (alreadyExistingModules.isEmpty()) {


            String bulletPointList = selectedElectiveIds.stream().map(moduleName -> "• " + moduleName).collect(Collectors.joining("\n"));

            alert.setContentText("The following modules were successfully added to your student record:\n" + bulletPointList);
        } else {
            String bulletPointList = alreadyExistingModules.stream().map(moduleName -> "• " + moduleName).collect(Collectors.joining("\n"));


            alert.setContentText("The following modules already exist in your student record and cannot be added:\n" + bulletPointList);
        }

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinWidth(400);
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        dialogPane.setMaxWidth(800);

        alert.showAndWait();
    }

    private void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


    public void loadTags() {
        List<String> tags = new ArrayList<String>();
        for (int i = 0; i < ElectiveListGenerator.electiveModulesList.size(); i++) {
            for (int x = 1; x < ElectiveListGenerator.electiveModulesList.get(i).getTagList().size(); x++) {
                if (!tags.contains(ElectiveListGenerator.electiveModulesList.get(i).getTagList().get(x))) {
                    tags.add(ElectiveListGenerator.electiveModulesList.get(i).getTagList().get(x));
                }
            }
        }


        if (tags.size() > 0) {
            tag1.setText(tags.get(0));
            tag2.setText(tags.get(1));
            tag3.setText(tags.get(2));
            tag4.setText(tags.get(3));
            tag5.setText(tags.get(4));
            tag6.setText(tags.get(5));
            tag7.setText(tags.get(6));
            tag8.setText(tags.get(7));
        }

    }

    @FXML
    private void showInstructions(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Instructions");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        TextFlow textFlow = new TextFlow();
        textFlow.setPrefWidth(600);
        textFlow.setLineSpacing(10);

        Text instructions = new Text("Functionality:\n\n - You must select three modules before pressing the 'Confirm' button.\n - Recommendations will be calculated based on similarities between modules. This will default to Course, but can be diversified through personal selections below.\n\nHow to use:\n\nSearch Modules\n - You can search for specific modules within the search bar. These modules will be added to your personalised recommendations.\n\nModule Tags\n - Selecting a specific tag will update your recommendations with one of eight unique focus areas.\n\nAdd Module\n - This will add a module to your selections ready to be confirmed. The module will be removed for the recommendation boxes and prompt a recalculation.\n\nCancel Module Selection\n - This will return your module selection to your recommendations and produce a new calculation.\n\nConfirm\n - This will confirm your choices and update your Student Record.\n");

        textFlow.getChildren().addAll(instructions);

        vbox.getChildren().add(textFlow);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);

        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RecordLoader recordLoader = new RecordLoader();
        try {
            currentYear.setText("Year " + recordLoader.getStudentYear(DatabaseHandler.getCurrentStudentId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        createRecommender();
        try {
            displayCoreModules();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        displayRecommendations();
        listViewWrapText(highView);
        listViewWrapText(mediumView);
        listViewWrapText(lowView);
        for (int i = 0; i < ElectiveListGenerator.electiveModulesList.size(); i++) {
            allElectivesList.getItems().add(ElectiveListGenerator.electiveModulesList.get(i).getName());
            electiveNames.add(ElectiveListGenerator.electiveModulesList.get(i).getName());
        }
        loadTags();
        ToggleGroup toggleGroup = new ToggleGroup();
        tag1.setToggleGroup(toggleGroup);
        tag2.setToggleGroup(toggleGroup);
        tag3.setToggleGroup(toggleGroup);
        tag4.setToggleGroup(toggleGroup);
        tag5.setToggleGroup(toggleGroup);
        tag6.setToggleGroup(toggleGroup);
        tag7.setToggleGroup(toggleGroup);
        tag8.setToggleGroup(toggleGroup);


    }
}