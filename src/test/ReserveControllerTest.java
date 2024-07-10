package test;

import BusinessLogic.AdminController;
import BusinessLogic.ReserveController;
import DomainModel.Reservation;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;

public class ReserveControllerTest{

    @Test
    public void removeReservationTest() throws SQLException, ClassNotFoundException{
        AdminController adminController = new AdminController();
        adminController.generateDefaultDatabase();
        int size= adminController.getAllReservations().size();
        ReserveController reserveController = new ReserveController();
        reserveController.removeReservation("user2@example.com", 2);
        ArrayList<Reservation> reservations = adminController.getAllReservations();
        assertEquals(size-1, reservations.size());
        adminController.generateDefaultDatabase();
    }

}
