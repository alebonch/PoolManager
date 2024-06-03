package BusinessLogic;
import DomainModel.*;
import ORM.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alebonch
 */
public class AdminController {
    //adders
    public void AddUser(String mail, String name, String surname, String telephone, String password) throws SQLException, ClassNotFoundException{
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(name, surname,telephone, mail, password);
    }
    //Removers
    public void removePostation(int number) throws SQLException, ClassNotFoundException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.removePos(number);
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
        UserController userController = new UserController();
        return userController.getAllPostations();
    }
    public ArrayList<Reservation> getAllReservations() throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectAllReservations();
    }
    public ArrayList<Reservation> getUserReservations(String username) throws ClassNotFoundException, SQLException{
        UserController userController = new UserController();
        return userController.getUserReservations(username);
    }
    public ArrayList<Reservation> getDateReservations(int date) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByDate(date);
    }
    public ArrayList<Reservation> getDateUserReservations(int date, String username) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByUserAndDate(username,date);
    }
    public ArrayList<Reservation> getPostationReservation(int pos) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByPostation(pos);
    }
    public ArrayList<Reservation> getDatePostationReservation(int pos,int date) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectReservationsByPostationAndDate(pos, date);
    }
    //extra actions
    public void updatePassword(String pwd) throws ClassNotFoundException, SQLException{
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.updatePassword(pwd);
    }
    public void generateDefaultDatabase() throws ClassNotFoundException, SQLException{
        resetDatabase();

        StringBuilder sql_tmp = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("../sql/default.sql"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) { sql_tmp.append(line).append("\n"); }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        String sql = sql_tmp.toString();
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.generateDefaultDatabase(sql);
    }
    public void resetDatabase() throws ClassNotFoundException, SQLException{

            StringBuilder sql_tmp = new StringBuilder();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("../sql/reset.sql"))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) { sql_tmp.append(line).append("\n"); }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                return;
            }
            String sql = sql_tmp.toString();
            AdminDAO adminDAO = new AdminDAO();
            adminDAO.resetDatabase(sql);
    
        
    }
}
