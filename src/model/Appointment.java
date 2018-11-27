package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the appointments database table.
 * 
 */
@Entity
@Table(name="appointments")
@NamedQuery(name="Appointment.findAll", query="SELECT a FROM Appointment a")
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAppointments;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAppointment;

	private String typeAppointment;

	//bi-directional many-to-one association to AppointmentHistory
	@OneToMany(mappedBy="appointment")
	private List<AppointmentHistory> appointmentHistories;

	//bi-directional many-to-one association to Animal
	@ManyToOne
	@JoinColumn(name="idAnimal")
	private Animal animal;

	//bi-directional many-to-one association to Doctor
	@ManyToOne
	@JoinColumn(name="idDoctor")
	private Doctor doctor;

	public Appointment() {
	}

	public int getIdAppointments() {
		return this.idAppointments;
	}

	public void setIdAppointments(int idAppointments) {
		this.idAppointments = idAppointments;
	}

	public Date getDateAppointment() {
		return this.dateAppointment;
	}

	public void setDateAppointment(Date dateAppointment) {
		this.dateAppointment = dateAppointment;
	}

	public String getTypeAppointment() {
		return this.typeAppointment;
	}

	public void setTypeAppointment(String typeAppointment) {
		this.typeAppointment = typeAppointment;
	}

	public List<AppointmentHistory> getAppointmentHistories() {
		return this.appointmentHistories;
	}

	public void setAppointmentHistories(List<AppointmentHistory> appointmentHistories) {
		this.appointmentHistories = appointmentHistories;
	}

	public AppointmentHistory addAppointmentHistory(AppointmentHistory appointmentHistory) {
		getAppointmentHistories().add(appointmentHistory);
		appointmentHistory.setAppointment(this);

		return appointmentHistory;
	}

	public AppointmentHistory removeAppointmentHistory(AppointmentHistory appointmentHistory) {
		getAppointmentHistories().remove(appointmentHistory);
		appointmentHistory.setAppointment(null);

		return appointmentHistory;
	}

	public Animal getAnimal() {
		return this.animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}