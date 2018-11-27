package util;

import model.Animal;
import model.Doctor;

public class DoctorUtil {

    public static void saveDoctor(Doctor doctor) {

        DatabaseUtil.entityManager.persist(doctor);

    }

//    public static String getNameDoctor(int id){
//
//        String q = "Select * from petshop.animal" + id;
//
//        String doctorName = DatabaseUtil.entityManager.createNativeQuery(q, Animal.class).get;
//    }
}
