package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the animal database table.
 * 
 */
@Entity
@NamedQuery(name="Animal.findAll", query="SELECT a FROM Animal a")
public class Animal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAnimal;

	private String nameAnimal;

	//bi-directional many-to-one association to TypeAnimal
	@ManyToOne
	@JoinColumn(name="type_animal")
	private TypeAnimal typeAnimalBean;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="animal")
	private List<Appointment> appointments;

	public Animal() {
	}

	public int getIdAnimal() {
		return this.idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getNameAnimal() {
		return this.nameAnimal;
	}

	public void setNameAnimal(String nameAnimal) {
		this.nameAnimal = nameAnimal;
	}

	public TypeAnimal getTypeAnimalBean() {
		return this.typeAnimalBean;
	}

	public void setTypeAnimalBean(TypeAnimal typeAnimalBean) {
		this.typeAnimalBean = typeAnimalBean;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setAnimal(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setAnimal(null);

		return appointment;
	}

}