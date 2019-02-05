package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Animal;
import model.Appointment;
import model.AppointmentHistory;
import util.AnimalUtil;
import util.AppointmentsUtil;
import util.DatabaseUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static util.AppointmentsUtil.appointmentHistList;
import static util.AppointmentsUtil.appointmentList;

public class MainController implements Initializable {
    /*
    Animal List
     */
    @FXML
    private ListView<String> listViewAnimals;
    /*
    Appointment List
    */
    @FXML
    private ListView<String> listViewAppointments;


    /*
    AppointmentHistory List
    */
    @FXML
    private ListView<String> listViewAppointmentHistory;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        populateAppointmentsList();

    }

    /**
     * Animal List
     */

    public void populateAnimalList() {
        DatabaseUtil db = new DatabaseUtil();
        db.setup();
        db.startTransaction();
        AnimalUtil animalUtil = new AnimalUtil();

        List<Animal> animalDBList = AnimalUtil.animalList();
        ObservableList<String> animalsNameList = getAnimalNames(animalDBList);
        listViewAnimals.setItems(animalsNameList);
        listViewAnimals.refresh();

        db.stopEntityManager();
    }

    public ObservableList<String> getAnimalNames(List<Animal> animalList) {

        ObservableList<String> FXAnimalList = FXCollections.observableArrayList();
        for (Animal a : animalList) {
            int idTypeAnimal = a.getTypeAnimalBean().getIdtypeAnimal();
            FXAnimalList.add(a.getNameAnimal() + " " + a.getTypeAnimalBean().getIdtypeAnimal());
        }
        return FXAnimalList;
    }

    /**
     * Appointment List
     */

    public void populateAppointmentsList() {
        DatabaseUtil db = new DatabaseUtil();
        db.setup();
        db.startTransaction();
        List<Appointment> appointmentDBList = appointmentList();
        ObservableList<String> appointmentList = getAppointmentList(appointmentDBList);
        listViewAppointments.setItems(appointmentList);
        listViewAppointments.refresh();


        db.stopEntityManager();
    }

    public ObservableList<String> getAppointmentList(List<Appointment> appointmentList) {

        ObservableList<String> FXAppointmentsList = FXCollections.observableArrayList();

        for (Appointment a : appointmentList) {
            FXAppointmentsList.add(a.getAnimal().getTypeAnimalBean().getType() +
                    " named : " + a.getAnimal().getNameAnimal()
                    + " with " + a.getTypeAppointment());
        }
        return FXAppointmentsList;
    }


    /**
     * Appointment History
     */

    public void populateAppointmentsHistoryList() {
        DatabaseUtil db = new DatabaseUtil();
        db.setup();
        db.startTransaction();

        AppointmentsUtil appointmentsUtil = new AppointmentsUtil();

        List<AppointmentHistory> appointmentHistoryDBList = appointmentHistList();
        ObservableList<String> appointmentHistoryList = getAppointmentHistoryList(appointmentHistoryDBList);
        listViewAppointmentHistory.setItems(appointmentHistoryList);
        listViewAppointmentHistory.refresh();


        db.stopEntityManager();
    }

    public ObservableList<String> getAppointmentHistoryList(List<AppointmentHistory> appointmentHistoryList) {

        ObservableList<String> FXAppointmentsList = FXCollections.observableArrayList();

        for (AppointmentHistory a : appointmentHistoryList) {
            FXAppointmentsList.add(a.getName() + "  Descriere : " + a.getDescription());
        }
        return FXAppointmentsList;
    }


}
