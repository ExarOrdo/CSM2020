package dcs.aber.ac.uk.csm2020_group_3;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    // voodoo request processor for magic backend handling
    //USE IT!
    private RequestProcessor requestProcessor;
    private static Stage currentStage;


    @Override
    public void start(Stage stage) throws IOException {
        currentStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        currentStage.setScene(new Scene(root));
        currentStage.show();
        currentStage.setMinHeight(400.0);
        currentStage.setMinWidth(600.0);
    }

    public void changeScene(String fxml) throws IOException{

        switch (fxml) {
            case "Login.fxml", "Register.fxml" -> {
                currentStage.setMinHeight(420);
                currentStage.setHeight(400);
            }
            case "Recommendation.fxml" -> {
                currentStage.setMinHeight(650);
                currentStage.setMinWidth(665);
            }
            case "StudentRecord.fxml", "Timetable.fxml", "Admin.fxml" -> {
                currentStage.setMinHeight(540);
                currentStage.setHeight(540);
            }
        }
        Parent screen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        currentStage.getScene().setRoot(screen);
    }

    public static void main(String[] args) {
        launch();
    }
}
