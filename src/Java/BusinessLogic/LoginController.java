package BusinessLogic;

import DomainModel.User;
import ORM.UserDAO;
import java.sql.SQLException;

public class LoginController {

    public User register(String username, String pwd, String name, String surname) throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(name, surname, username, pwd);
        return userDAO.checkPassword(username, pwd);
    }
    public User register(String username, String pwd, String name, String surname, String telephone) throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(name, surname,telephone, username, pwd);
        return userDAO.checkPassword(username, pwd);
    }
    public User login(String username, String pwd) throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        return userDAO.checkPassword(username, pwd);
    }
    public User adminLogin(String password) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        return userDAO.checkPassword("admin@admin.com",password);

    }
}
