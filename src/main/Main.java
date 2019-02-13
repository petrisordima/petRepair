package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//Main class which extends from Application Class
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            //root stage appointment view with the associated fxml file
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/controllers/AppointmentView.fxml"));
            Scene scene = new Scene(root, 1200, 800);
            primaryStage.setTitle("PetRepair");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

