package test;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.Test;

import BusinessLogic.LoginController;
import BusinessLogic.AdminController;
import DomainModel.User;

public class LoginControllerTest {
    
    @Test
    public void LoginTest() throws SQLException, ClassNotFoundException{
         // Test case 1: this is a known user in the database
        String username = "user1@example.com";
        String password = "password1";

        LoginController loginController = new LoginController();

        try {
            User user = loginController.login(username, password);
            assertNotNull(user);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        // Test case 2: Incorrect password
        String username2 = "user2@example.com";
        String password2 = "Password2";

        try {
            User user2 = loginController.login(username2, password2);
            assertNull(user2);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    @Test
    public void RegisterTest() throws SQLException, ClassNotFoundException{
        String username = "tommasofredducci@example.com"; 
        String password = "redelghiaccio";
        String name = "Tommaso";
        String surname = "Fredducci";
        AdminController adminController = new AdminController();
        adminController.generateDefaultDatabase();
        LoginController loginController = new LoginController();
            
        try {
            User user = loginController.register(username, password, name, surname);
            assertNotNull(user);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        adminController.removeUser(username);;

    }

}
