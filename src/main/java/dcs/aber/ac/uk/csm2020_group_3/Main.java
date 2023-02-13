package dcs.aber.ac.uk.csm2020_group_3;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    /*private ChangeListener<? super Number> widthChangeListener;
    private ChangeListener<? super Number> heightChangeListener;*/

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Register.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Recommendation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CSM2020");
        stage.setScene(scene);
        stage.show();
        //stage.setHeight(420.0);
        //stage.setWidth(620.0);
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

    public static void main(String[] args) {
        launch();
    }
}
