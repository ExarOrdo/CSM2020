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

        /*Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CSM2020");
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(400.0);
        stage.setMinWidth(600.0);
        /*widthChangeListener = (observable, oldValue, newValue) -> {
            stage.heightProperty().removeListener(heightChangeListener);
            stage.setHeight(newValue.doubleValue() / 2.0);
            stage.heightProperty().addListener(heightChangeListener);

        };
        heightChangeListener = (observable, oldValue, newValue) -> {
            stage.widthProperty().removeListener(widthChangeListener);
            stage.setWidth(newValue.doubleValue() * 2.0);
            stage.widthProperty().addListener(widthChangeListener);
        };
        stage.widthProperty().addListener(widthChangeListener);
        stage.heightProperty().addListener(heightChangeListener);*/
    }

    public void changeScene(String fxml) throws IOException{
        if (Objects.equals(fxml, "Recommendation.fxml")){
            //currentStage.setMinHeight(1280);
            currentStage.setMinHeight(550);
            currentStage.setMinWidth(600);
        }else{
            currentStage.setMinHeight(400);
        }
        Parent screen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        currentStage.getScene().setRoot(screen);
    }

    public static void main(String[] args) {
        launch();
    }
}
