package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the appointment_history database table.
 * 
 */
@Entity
@Table(name="appointment_history")
@NamedQuery(name="AppointmentHistory.findAll", query="SELECT a FROM AppointmentHistory a")
public class AppointmentHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAppointment_history;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Lob
	private String description;

	private String name;

	//bi-directional many-to-one association to Appointment
	@ManyToOne
	@JoinColumn(name="idAppointment")
	private Appointment appointment;

	public AppointmentHistory() {
	}

	public int getIdAppointment_history() {
		return this.idAppointment_history;
	}

	public void setIdAppointment_history(int idAppointment_history) {
		this.idAppointment_history = idAppointment_history;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Appointment getAppointment() {
		return this.appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

}