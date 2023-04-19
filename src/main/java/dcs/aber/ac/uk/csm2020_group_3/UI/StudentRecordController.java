package dcs.aber.ac.uk.csm2020_group_3.UI;

import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.StudentModule;
import dcs.aber.ac.uk.csm2020_group_3.Main;
import dcs.aber.ac.uk.csm2020_group_3.DatabaseHandler.DatabaseHandler;
import dcs.aber.ac.uk.csm2020_group_3.RecommendationSystem.DataLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class StudentRecordController implements Initializable {

    @FXML
    private Pane expandedPane;
    @FXML
    private Button burgerBtn, logoutBtn, recommendationBtn, helpBtn, timetableBtn, studentRecordBtn;
    @FXML
    private ImageView burgerIcon;
    @FXML
    private ComboBox<String> yearComboBox;
    @FXML
    private Label studentIDField, studentNameField, studentCourseField;
    @FXML
    private TableView<StudentModule> studentModuleTableView;
    @FXML
    private TableColumn<StudentModule, String> moduleIDColumn;
    @FXML
    private TableColumn<StudentModule, Date> moduleMarkDateColumn;
    @FXML
    private TableColumn<StudentModule, Integer> studentMarkColumn;
    @FXML
    private TextArea moduleNameField, moduleDescriptionField;


    public void setStudentDetails(Map<String, String> studentDetails) {
        studentIDField.setText(studentDetails.get("student_id"));
        studentNameField.setText(studentDetails.get("student_name"));
        studentCourseField.setText(studentDetails.get("student_course"));
        yearComboBox.setValue(studentDetails.get("student_year"));
    }

    @FXML
    private void updateTableByYear() {
        loadStudentModulesByYear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataLoader dataLoader = new DataLoader();
        String studentID = DatabaseHandler.getCurrentStudentId();
        Map<String, String> studentDetails = dataLoader.loadStudentDetails(studentID);
        setStudentDetails(studentDetails);
        yearComboBox.getItems().addAll("1", "2", "3", "4");

        yearComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateTableByYear());

        moduleIDColumn.setCellValueFactory(new PropertyValueFactory<>("moduleID"));
        moduleMarkDateColumn.setCellValueFactory(new PropertyValueFactory<>("markDate"));
        studentMarkColumn.setCellValueFactory(new PropertyValueFactory<>("studentMark"));

        loadStudentModulesByYear();

        studentModuleTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String selectedModuleID = studentModuleTableView.getSelectionModel().getSelectedItem().getModuleID();
                Map<String, String> moduleNameAndDescription = dataLoader.getModuleNameAndDescription(selectedModuleID);
                moduleNameField.setText(moduleNameAndDescription.get("module_name"));
                moduleDescriptionField.setText(moduleNameAndDescription.get("module_description"));
            }
        });
    }

    private void loadStudentModulesByYear() {
        int year = 1;

        if (yearComboBox.getSelectionModel().getSelectedItem() != null) {
            year = Integer.parseInt(yearComboBox.getSelectionModel().getSelectedItem());
        }

        DataLoader dataLoader = new DataLoader();
        String studentID = DatabaseHandler.getCurrentStudentId();
        ResultSet resultSet = dataLoader.loadStudentModulesWithMarksByYear(studentID, year);

        ObservableList<StudentModule> studentModules = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {
                String moduleID = resultSet.getString("ModuleID");
                int studentMark = resultSet.getInt("StudentMark");
                int moduleYear = resultSet.getInt("ModuleYear");
                Date markDate = resultSet.getDate("MarkDate");

                studentModules.add(new StudentModule(moduleID, moduleYear, studentMark, markDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        studentModuleTableView.setItems(studentModules);
    }

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
    private void toStudentRecord() {
        pressBurgerBtn();
    }
}

