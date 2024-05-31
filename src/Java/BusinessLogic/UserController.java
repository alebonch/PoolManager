package BusinessLogic;

import DomainModel.Postation;
import DomainModel.Reservation;
import DomainModel.Typology;
import DomainModel.User;
import ORM.PostationDAO;
import ORM.ReservationDAO;
import ORM.TypologyDAO;
import ORM.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    //metodi in comune con Admin
    public ArrayList<Postation> getAllPostations() throws ClassNotFoundException, SQLException{
        PostationDAO postationDAO = new PostationDAO();
        return postationDAO.selectAllPostations();
    }
    public ArrayList<Postation> getAvailablePostations() throws ClassNotFoundException, SQLException{
        PostationDAO postationDAO = new PostationDAO();
        return postationDAO.selectAvailablePostations();
    }
    public ArrayList<Typology> getTypologies() throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        return typologyDAO.selectAllTypologies();
    }
    public void addReservation(String username, int postation, int paymentMethod, String date) throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.addReservation(username, postation, paymentMethod, date);
    }
    public void addReservation(String username, int postation, String date) throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.addReservation(username, postation, date);
    }
    public void removeReservation(String username, String date) throws SQLException, ClassNotFoundException{
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.removeReservation(username, date);
    }
    public ArrayList<Reservation> getUserReservations(String username) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByUser(username);
    }
    public void changePaymentMethods(String username, String date, int PaymentMethods) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.updatePaymentMethods(username, date, PaymentMethods);
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

