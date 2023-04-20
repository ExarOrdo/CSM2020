package dcs.aber.ac.uk.csm2020_group_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {


    public static Stage currentStage;

    public FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {
        currentStage = stage;
        fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        currentStage.setScene(new Scene(root));
        currentStage.show();
        currentStage.setMinHeight(400.0);
        currentStage.setMinWidth(600.0);
        currentStage.setTitle("Login");
    }

    public void changeScene(String fxml) throws IOException {

        final String sceneTitle = fxml.substring(0, fxml.length() - 5);
        switch (fxml) {
            case "Login.fxml", "Register.fxml", "AdminLogin.fxml" -> {
                currentStage.setMinHeight(430);
                currentStage.setHeight(430);
                currentStage.setMinWidth(620);
                currentStage.setWidth(620);
                currentStage.setTitle(sceneTitle);
            }
            case "Recommendation.fxml", "StudentRecord.fxml", "Timetable.fxml", "Help.fxml", "Admin.fxml", "AdminTimetable.fxml" -> {
                currentStage.setMinHeight(820);
                currentStage.setMinWidth(1020);
                currentStage.setTitle(sceneTitle);

            }
        }
        Parent screen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        currentStage.getScene().setRoot(screen);
    }

    public static void main(String[] args) {
        launch();
    }
}
