package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.RecordLoader;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private RecordLoader recordLoader = new RecordLoader();

    @FXML
    ComboBox<String> subjectBox;
    @FXML
    ComboBox<String> courseBox;

    @FXML
    TabPane tabPane;
    @FXML
    Tab year0tab, year1tab, year2tab, year3tab, year4tab;
    @FXML
    ListView<String> year0list, year1list, year2list, year3list, year4list;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //disable tabs and course box so that user chooses subjects first
        courseBox.setDisable(true);
        year0tab.setDisable(true);
        year1tab.setDisable(true);
        year2tab.setDisable(true);
        year3tab.setDisable(true);
        year4tab.setDisable(true);


        //arraylists used for combo boxes and listviews
        ArrayList<String> subjectStringList = new ArrayList<>();

        var ref = new Object() {
            ArrayList<String> courseStringList = new ArrayList<>();
        };
        var ref2 = new Object() {
            ArrayList<String> moduleStringList = new ArrayList<>();
        };



        try {
            subjectStringList = recordLoader.getSubjectList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //populate combo box for subjects
        for (String s : subjectStringList) {
            subjectBox.getItems().add(s);
        }


        //handle subject box selection
        subjectBox.setOnAction((event) -> {
            String chosenSubject = (String) subjectBox.getValue();
            System.out.println("Admin chose: " + chosenSubject);

            //after user chooses subject, let them select course
            courseBox.setDisable(false);

            //empty the list and combobox if new subject is chosen
            ref.courseStringList = new ArrayList<>();
            courseBox.getItems().clear();

            try {
                ref.courseStringList = recordLoader.getCourseListBySubject(chosenSubject);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for (String s : ref.courseStringList) {
                courseBox.getItems().add(s);
            }
        });


        final String[] chosenCourse = new String[1];

        //handle course box selection
        courseBox.setOnAction((event) -> {
            chosenCourse[0] = (String) courseBox.getValue();
            System.out.println("Admin chose: " + chosenCourse[0]);

            //get whether BSc or MSc
            String level = "";
            for (int i = 0; i < 3; i++) {
                level += chosenCourse[0].charAt(i);
            }
            //depending on what level the course is at, disable unusable tabs
            switch(level) {
                case "MSc" -> {
                    year0tab.setDisable(true);
                    year1tab.setDisable(true);
                    year2tab.setDisable(true);
                    year3tab.setDisable(true);
                    year4tab.setDisable(false);
                }
                case "BSc" -> {
                    year0tab.setDisable(false);
                    year1tab.setDisable(false);
                    year2tab.setDisable(false);
                    year3tab.setDisable(false);
                    year4tab.setDisable(true);
                }
            }

            ref2.moduleStringList = new ArrayList<>();

            //depending on which tab is selected, execute different query
            getModuleListViewPopulated(chosenCourse[0], ref2.moduleStringList);

        });


        //fancy looking lambda expressions B), listeners for year tabs
        year0tab.setOnSelectionChanged(event -> getModuleListViewPopulated(chosenCourse[0], ref2.moduleStringList));
        year1tab.setOnSelectionChanged(event -> getModuleListViewPopulated(chosenCourse[0], ref2.moduleStringList));
        year2tab.setOnSelectionChanged(event -> getModuleListViewPopulated(chosenCourse[0], ref2.moduleStringList));
        year3tab.setOnSelectionChanged(event -> getModuleListViewPopulated(chosenCourse[0], ref2.moduleStringList));
        year4tab.setOnSelectionChanged(event -> getModuleListViewPopulated(chosenCourse[0], ref2.moduleStringList));

    }

    private void getModuleListViewPopulated(String chosenCourse, ArrayList<String> moduleStringList) {
        //clear the list views to avoid appending
        year0list.getItems().clear();
        year1list.getItems().clear();
        year2list.getItems().clear();
        year3list.getItems().clear();
        year4list.getItems().clear();

        //depending on which tab is selected load its contents
        if (year0tab.isSelected()) {
            try {
                moduleStringList = recordLoader.getModuleListByCourse(chosenCourse, 0);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            year0list.setItems(FXCollections.observableList(moduleStringList));

            for (String s : moduleStringList) {

                year0list.getItems().add(s);
            }

        } else if (year1tab.isSelected()) {
            try {
                moduleStringList = recordLoader.getModuleListByCourse(chosenCourse, 1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (String s : moduleStringList) {
                year1list.getItems().add(s);
            }

        } else if (year2tab.isSelected()) {
            try {
                moduleStringList = recordLoader.getModuleListByCourse(chosenCourse, 2);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (String s : moduleStringList) {
                year2list.getItems().add(s);
            }

        } else if (year3tab.isSelected()) {
            try {
                moduleStringList = recordLoader.getModuleListByCourse(chosenCourse, 3);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (String s : moduleStringList) {
                year3list.getItems().add(s);
            }

        } else if (year4tab.isSelected()) {
            try {
                moduleStringList = recordLoader.getModuleListByCourse(chosenCourse, 4);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (String s : moduleStringList) {
                year4list.getItems().add(s);
            }
        }
    }


}
