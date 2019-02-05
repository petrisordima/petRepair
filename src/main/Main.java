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

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/controllers/MainView.fxml"));
            Scene scene = new Scene(root, 1200, 800);
            primaryStage.setTitle("PetShopRepair");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

//public abstract class Main extends Application{
//
//    public static void main(String[] args) throws Exception{
//
//
//        DatabaseUtil dbUtil = new DatabaseUtil();
//        Animal animal = new Animal();
//        Doctor doctor = new Doctor();
//        Appointment appointment = new Appointment();
//        AppointmentsUtil ape = new AppointmentsUtil();
//
//
//
//
//
////        animal.setIdAnimal(2);
////        animal.setNameAnimal("Bonana");
////
////        doctor.setIdDoctor(1);
////        doctor.setNameDoctor("Doctor 1");
//
//        dbUtil.setup();
//        dbUtil.startTransaction();
//        ape.printAppointmentHistory();
//
////        dbUtil.saveAnimal(animal);
////        dbUtil.commitTransaction();
//        //AnimalUtil.printAllAnimalsFromDb();
//        dbUtil.stopEntityManager();
//
//    }

//}

