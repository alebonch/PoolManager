package test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.SQLException;
import org.junit.Test;

import BusinessLogic.AdminController;
import BusinessLogic.LoginController;
import BusinessLogic.UserController;
import DomainModel.User;
public class UserControllerTest {
    
    @Test
    public void updatePasswordTest() throws SQLException, ClassNotFoundException{
        String username = "user1@example.com"; 
        String password = "password1";
        AdminController adminController = new AdminController();
        adminController.generateDefaultDatabase();
        User user = adminController.getUser(username);
        assertEquals(password, user.getPwd());
        UserController userController = new UserController();
        password = "iceking";
        userController.updatePassword(username, password);
        user = adminController.getUser(username);
        assertEquals(password, user.getPwd());
        adminController.generateDefaultDatabase();
    }
}
