package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import model.Animal;
import model.Doctor;
import util.AnimalUtil;
import util.DatabaseUtil;
import util.DoctorUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewAppointemntController implements Initializable {

    /**
     * Appointments fields definition
     */
    @FXML
    private TextArea description;

    @FXML
    private ChoiceBox animal;

    @FXML
    private ChoiceBox doctor;

    @FXML
    private DatePicker date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        populateDoctorDropdown();
        populateAnimalDropdown();
    }


    // Fill Doctor list
    public void populateDoctorDropdown() {

        // start DB connection
        DatabaseUtil db = new DatabaseUtil();
        db.setup();
        db.startTransaction();

        // get appointment list
        List<Doctor> doctorList = DoctorUtil.getdoctorList();

        doctor.setItems(getDoctorList(doctorList));

        db.stopEntityManager();
    }

    //Doctor observable list
    public ObservableList<String> getDoctorList(List<Doctor> doctorList) {

        ObservableList<String> FXDoctorList = FXCollections.observableArrayList();

        for (Doctor a : doctorList) {
            FXDoctorList.add(a.getNameDoctor());
        }
        return FXDoctorList;
    }

    // Fill Animal list
    public void populateAnimalDropdown() {

        // start DB connection
        DatabaseUtil db = new DatabaseUtil();
        db.setup();
        db.startTransaction();

        // get appointment list
        List<Animal> animalList = AnimalUtil.animalList();

        animal.setItems(getAnimalList(animalList));
        db.stopEntityManager();
    }

    //Animal observable list
    public ObservableList<String> getAnimalList(List<Animal> animalList) {

        ObservableList<String> FXAnimalList = FXCollections.observableArrayList();

        for (Animal a : animalList) {
            FXAnimalList.add(a.getNameAnimal());
        }
        return FXAnimalList;
    }


}
