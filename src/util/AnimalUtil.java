package util;

import model.Animal;

import java.util.List;

public class AnimalUtil {

    public static void saveAnimal(Animal animal) {

        DatabaseUtil.entityManager.persist(animal);

    }


    public static List<Animal> animalList() {
        //noinspection UnnecessaryLocalVariable
        List<Animal> results = DatabaseUtil.entityManager.createNativeQuery(
                "Select * from petshop.animal", Animal.class).getResultList();
        return results;
    }


    public static void printAllAnimalsFromDb() {

        List<Animal> results = DatabaseUtil.entityManager.createNativeQuery("Select * from petshop.animal", Animal.class).getResultList();

        for (Animal animal : results) {
            System.out.println("Animal: " + animal.getNameAnimal() + "has type id: " + animal.getTypeAnimalBean().getIdtypeAnimal());
        }
    }
}
