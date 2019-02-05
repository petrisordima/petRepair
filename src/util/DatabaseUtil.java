package util;


import com.sun.rowset.CachedRowSetImpl;
import model.Appointment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseUtil {

    // will handle the connection
    public static EntityManagerFactory entityManagerFactory;

    // will handle the transactions
    public static EntityManager entityManager;
    //Connection
    private static Connection conn = null;


    //sets up the connection to the database from PetShop JPA


    public void setup() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("PetShop");
            entityManager = entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            //System.out.println("Could not connect to DB ");
            e.printStackTrace();
        }
    }


    public  void startTransaction() {

        entityManager.getTransaction().begin();

    }


    public void commitTransaction() {

        entityManager.getTransaction().commit();

    }


    public  void stopEntityManager() {

        entityManager.close();

    }


}
