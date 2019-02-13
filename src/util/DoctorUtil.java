package util;

import model.Doctor;

import java.util.List;

public class DoctorUtil {

    public static void saveDoctor(Doctor doctor) {

        DatabaseUtil.entityManager.persist(doctor);

    }

    public static List getdoctorList() {

        return DatabaseUtil.entityManager.createNativeQuery(
                "Select * from petshop.doctor", Doctor.class)
                .getResultList();
    }
}
