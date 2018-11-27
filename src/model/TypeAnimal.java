package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the type_animal database table.
 * 
 */
@Entity
@Table(name="type_animal")
@NamedQuery(name="TypeAnimal.findAll", query="SELECT t FROM TypeAnimal t")
public class TypeAnimal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idtype_animal")
	private int idtypeAnimal;

	private String type;

	//bi-directional many-to-one association to Animal
	@OneToMany(mappedBy="typeAnimalBean")
	private List<Animal> animals;

	public TypeAnimal() {
	}

	public int getIdtypeAnimal() {
		return this.idtypeAnimal;
	}

	public void setIdtypeAnimal(int idtypeAnimal) {
		this.idtypeAnimal = idtypeAnimal;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Animal> getAnimals() {
		return this.animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public Animal addAnimal(Animal animal) {
		getAnimals().add(animal);
		animal.setTypeAnimalBean(this);

		return animal;
	}

	public Animal removeAnimal(Animal animal) {
		getAnimals().remove(animal);
		animal.setTypeAnimalBean(null);

		return animal;
	}

}