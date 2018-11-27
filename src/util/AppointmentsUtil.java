package util;

import model.Appointment;
import model.AppointmentHistory;

import java.util.List;

public class AppointmentsUtil {

    public static List<Appointment> appointmentList() {
        List<Appointment> results = DatabaseUtil.entityManager.createNativeQuery(
                "Select * from petshop.appointments", Appointment.class).getResultList();
        return results;
    }

    public static List<AppointmentHistory> appointmentHistList() {
        List<AppointmentHistory> results = DatabaseUtil.entityManager.createNativeQuery(
                "select * from appointment_history where idAppointment = 1", AppointmentHistory.class).getResultList();
        return results;
    }

    public static void printAppointmentHistory() {

        List<AppointmentHistory> results = DatabaseUtil.entityManager.createNativeQuery(
                "select * from appointment_history where idAppointment = 1",
                AppointmentHistory.class).getResultList();

        for (AppointmentHistory hist : results) {
            System.out.println(
                    hist.getIdAppointment_history()
                            + " App History name is :  " + hist.getName()
                            + " and Description is : " + hist.getDescription());
        }
    }
}
