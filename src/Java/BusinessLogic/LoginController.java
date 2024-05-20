package BusinessLogic;

import java.sql.SQLException;

import DomainModel.User;
import ORM.UserDAO;

public class LoginController {
    public void register(String username, String pwd, String name, String surname) throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(name, surname, username, pwd);
    }
    public void register(String username, String pwd, String name, String surname, String telephone) throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(name, surname,telephone, username, pwd);
    }
    public User login(String username, String pwd) throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        return userDAO.checkPassword(username, pwd);
    }
}
