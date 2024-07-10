package test;
import BusinessLogic.AdminController;
import BusinessLogic.ReserveController;
import DomainModel.Reservation;
import DomainModel.User;
import java.lang.reflect.Array;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

public class AdminControllerTest{

    @Test
    public void generateDefaultDatabaseTest() throws SQLException, ClassNotFoundException{
        AdminController adminController = new AdminController();
        String username = "tommasofredducci@example.com"; 
        adminController.generateDefaultDatabase();
        adminController.AddUser(username, "Tommaso", "Fredducci", "123", "redelghiaccio");
        assertTrue(adminController.getUser(username)!=null);
        ArrayList<Reservation> reservations = adminController.getUserReservations(username);
        assertEquals(0, reservations.size());
        ReserveController reserveController = new ReserveController();
        reserveController.Reserve(1, 1, 1, 1, 1, "2024-06-19", "Mattina", 1, username);
        assertEquals(1, adminController.getUserReservations(username).size());
        adminController.generateDefaultDatabase();

    }
}
