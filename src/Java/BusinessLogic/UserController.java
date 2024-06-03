package BusinessLogic;

import DomainModel.Postation;
import DomainModel.Reservation;
import DomainModel.User;
import ORM.ObjectDAO;
import ORM.PostationDAO;
import ORM.ReservationDAO;
import ORM.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    public ArrayList<Postation> getAllPostations() throws ClassNotFoundException, SQLException{
        PostationDAO postationDAO = new PostationDAO();
        return postationDAO.selectAllPostations();
    }
    public ArrayList<DomainModel.Object> getResources() throws ClassNotFoundException, SQLException{
        ObjectDAO objectDAO = new ObjectDAO();
        return objectDAO.getAllObjects();
    }
    public ArrayList<Reservation> getUserReservations(String username) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByUser(username);
    }
    //updaters
    public void updatePassword(String mail, String pwd) throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        userDAO.updatePassword(mail, pwd);
    }
    public void updateTelephone(String mail, String telephone) throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        userDAO.updateTelephone(mail, telephone);
    }
    //getter
    public User getUser(String username) throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        return userDAO.getUser(username);
    }
}

