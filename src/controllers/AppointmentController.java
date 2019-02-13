package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.AppointmentHistory;
import util.AppointmentsUtil;
import util.DatabaseUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class AppointmentController implements Initializable {

    AppointmentsUtil appointmentsUtil = new AppointmentsUtil();

    /**
     * Appointments table and column definition
     */
    @FXML
    private TableView<Appointment> tableViewAppointments;
    @FXML
    private TableColumn<Appointment, Date> dateAppointment;
    @FXML
    private TableColumn<Appointment, Integer> idAppointment;
    @FXML
    private TableColumn<Appointment, String> typeAppointment;

    /**
     * Appointments fields definition
     */
    @FXML
    private TextField animalName;
    @FXML
    private TextField animalType;
    @FXML
    private TextField doctorName;
    @FXML
    private TextArea description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateAppointmentsTable();
    }

    // Appointment list and appointment detail
    public void populateAppointmentsTable() {

        // start DB connection
        DatabaseUtil db = new DatabaseUtil();
        db.setup();
        db.startTransaction();

        // get appointment list
        List<Appointment> appointmentDBList = appointmentsUtil.appointmentList();

        //map columns to appointment properties
        dateAppointment.setCellValueFactory(new PropertyValueFactory<>("dateAppointment"));
        idAppointment.setCellValueFactory(new PropertyValueFactory<>("idAppointments"));
        typeAppointment.setCellValueFactory(new PropertyValueFactory<>("typeAppointment"));


        //send items to table
        tableViewAppointments.setItems(getAppointmentsList(appointmentDBList));

        /*lambda expression :)

         */
//        tableViewAppointments.setOnMousePressed(event -> animalName.setText(tableViewAppointments.
//                getSelectionModel().
//                getSelectedItem().
//                getAnimal().
//                getNameAnimal())
//        );

        //load appointments details on mouse pressed

        tableViewAppointments.setOnMousePressed(event -> {
            // animal type
            try {
                animalType.setText(tableViewAppointments.
                        getSelectionModel().
                        getSelectedItem().
                        getAnimal().
                        getTypeAnimalBean().
                        getType());
                // animal name
                animalName.setText(tableViewAppointments.
                        getSelectionModel().
                        getSelectedItem().
                        getAnimal().
                        getNameAnimal());
                //doctor name
                doctorName.setText(tableViewAppointments.
                        getSelectionModel().
                        getSelectedItem().
                        getDoctor().
                        getNameDoctor());
                description.setText(tableViewAppointments.
                        getSelectionModel().
                        getSelectedItem().
                        getTypeAppointment());
            } catch (Exception e) {
                System.out.println(" no item selected in the list");
            }

        });

        db.stopEntityManager();
    }

    // Appointment Table observable list
    public ObservableList<Appointment> getAppointmentsList(List<Appointment> appointmentList) {

        ObservableList<Appointment> fXAppointmentsList = FXCollections.observableArrayList();

        for (Appointment a : appointmentList) {
            //System.out.println(a);
            fXAppointmentsList.add(a);
        }
        return fXAppointmentsList;
    }

    //Appointment History observable list
    public ObservableList<String> getAppointmentHistoryList(List<AppointmentHistory> appointmentHistoryList) {

        ObservableList<String> FXAppointmentsList = FXCollections.observableArrayList();

        for (AppointmentHistory a : appointmentHistoryList) {
            FXAppointmentsList.add(a.getName() + a.getDescription());
        }
        return FXAppointmentsList;
    }

    //New appointemnt
    public void handleNewAppointemnt(ActionEvent actionEvent) throws IOException {
        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/controllers/NewAppointmentView.fxml"));
        Scene scene = new Scene(root, 400, 480);

        Stage stage = new Stage();
        stage.setTitle("New appointment");
        stage.setScene(scene);
        stage.show();
    }

    //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //About
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("This is a laboratory project for Java");
        alert.setContentText("Displays a list of appointments and lets you create a new one for a veterinary cabinet" + "\n" + "by Petrisor Dima");
        alert.show();
    }
}
