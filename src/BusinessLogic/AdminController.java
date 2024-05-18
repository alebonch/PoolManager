package BusinessLogic;
import ORM.*;

import java.sql.SQLException;
import java.util.ArrayList;

import DomainModel.*;
public class AdminController {
    //adders
    public void addPostation(int number, String zone, int type) throws SQLException, ClassNotFoundException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.insertPostation(number, type, zone);
    }
    public void AddTypology(int n_chairs, int n_deckchairs, int n_sunbeds, String m_sunbeds, boolean gazebo)throws SQLException, ClassNotFoundException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.insertTypology(n_chairs, m_sunbeds, n_deckchairs, n_sunbeds, gazebo);
    }
    public void AddUser(String mail, String name, String surname, String telephone, String password) throws SQLException, ClassNotFoundException{
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(name, surname,telephone, mail, password);
    }
    //Removers
    public void removePostation(int number) throws SQLException, ClassNotFoundException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.removePos(number);
    }
    public void removeTypology(int id)throws SQLException, ClassNotFoundException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.removeTypology(id);
    }
    public void removeUser(String mail) throws SQLException, ClassNotFoundException{
        UserDAO userDAO = new UserDAO();
        userDAO.removeUser(mail);
    }
    //getters
    public ArrayList<User> getAllUsers() throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        return userDAO.getAllUsers();
    }
    public User getUser(String mail) throws ClassNotFoundException, SQLException{
        UserDAO userDAO = new UserDAO();
        return userDAO.getUser(mail);
    }
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
    public ArrayList<Reservation> getAllReservations() throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectAllReservations();
    }
    public ArrayList<Reservation> getUserReservations(String username) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByUser(username);
    }
    public ArrayList<Reservation> getDateReservations(String date) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByDate(date);
    }
    public ArrayList<Reservation> getDateUserReservations(String date, String username) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByUserAndDate(username,date);
    }
    public ArrayList<Reservation> getPostationReservation(int pos) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByPostation(pos);
    }
    public ArrayList<Reservation> getDatePostationReservation(int pos,String date) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByPostationAndDate(pos, date);
    }
    // updaters
    public void updateSunbeds(int code, int n) throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.updateSunbeds(code,n);
    }
    public void updateDeckchairs(int code, int n) throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.updateDeckchairs(code,n);
    }
    public void updateChairs(int code, int n) throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.updateChairs(code,n);
    }
    public void updateMaterialSunbeds(int code, String material) throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.updateMaterialSunbeds(code,material);
    }
    public void updateGazebo(int code, boolean gazebo) throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.updateGazebo(code,gazebo);
    }
    public void updateZone(int number, String zone) throws ClassNotFoundException, SQLException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.updateZone(number, zone);
    }
    public void updateTypology(int number, int posId) throws ClassNotFoundException, SQLException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.updateTypology(number, posId);
    }
    public void updateAvailability(int posId, boolean available) throws ClassNotFoundException, SQLException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.updateAvailability(posId, available);
    }
    
}
