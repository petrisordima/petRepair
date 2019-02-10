package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.AppointmentHistory;
import util.AppointmentsUtil;
import util.DatabaseUtil;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class AppointmentController implements Initializable {

    AppointmentsUtil au = new AppointmentsUtil();

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        populateAppointmentsTable();


    }

    public void populateAppointmentsTable() {

        // start DB connection
        DatabaseUtil db = new DatabaseUtil();
        db.setup();
        db.startTransaction();

        // get appointment list
        List<Appointment> appointmentDBList = au.appointmentList();

        //map columns to appointment properties
        dateAppointment.setCellValueFactory(new PropertyValueFactory<>("dateAppointment"));
        idAppointment.setCellValueFactory(new PropertyValueFactory<>("idAppointments"));
        typeAppointment.setCellValueFactory(new PropertyValueFactory<>("typeAppointment"));


        //send items to table
        tableViewAppointments.setItems(getAppointmentsList(appointmentDBList));

        /*lambda expression :)

         */
        tableViewAppointments.setOnMousePressed(event -> animalName.setText(tableViewAppointments.
                getSelectionModel().
                getSelectedItem().
                getAnimal().
                getNameAnimal())


        );

        tableViewAppointments.setOnMousePressed(event -> {
            // animal type
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

        });


        db.stopEntityManager();
    }

    /**
     * Appointment Table
     */


    public ObservableList<Appointment> getAppointmentsList(List<Appointment> appointmentList) {

        ObservableList<Appointment> fXAppointmentsList = FXCollections.observableArrayList();

        for (Appointment a : appointmentList) {
            System.out.println(a);
            fXAppointmentsList.add(a);
        }
        return fXAppointmentsList;
    }

    /**
     * Appointment History
     */

    public ObservableList<String> getAppointmentHistoryList(List<AppointmentHistory> appointmentHistoryList) {

        ObservableList<String> FXAppointmentsList = FXCollections.observableArrayList();

        for (AppointmentHistory a : appointmentHistoryList) {
            FXAppointmentsList.add(a.getName()  + a.getDescription());
        }
        return FXAppointmentsList;
    }
}
