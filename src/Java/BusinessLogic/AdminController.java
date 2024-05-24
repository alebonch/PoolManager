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
    public void addPostation(int number, String zone, String type) throws SQLException, ClassNotFoundException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.insertPostation(number, type, zone);;
    }
    public void AddTypology(String id, int n_chairs, int n_deckchairs, int n_sunbeds, String m_sunbeds, boolean gazebo)throws SQLException, ClassNotFoundException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.insertTypology(id ,n_chairs, m_sunbeds, n_deckchairs, n_sunbeds, gazebo);
    }
    public void AddUser(String mail, String name, String surname, String telephone, String password) throws SQLException, ClassNotFoundException{
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(name, surname,telephone, mail, password);
    }
    public void addReservation(String username, int postation, int paymentMethod, String date) throws SQLException, ClassNotFoundException{
        UserController userController = new UserController();
        userController.addReservation(username, postation, paymentMethod, date);
    }
    public void addReservation(String username, int postation, String date) throws SQLException, ClassNotFoundException{
        UserController userController = new UserController();
        userController.addReservation(username, postation, date);
    }
    //Removers
    public void removePostation(int number) throws SQLException, ClassNotFoundException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.removePos(number);
    }
    public void removeTypology(String id)throws SQLException, ClassNotFoundException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.removeTypology(id);
    }
    public void removeUser(String mail) throws SQLException, ClassNotFoundException{
        UserDAO userDAO = new UserDAO();
        userDAO.removeUser(mail);
    }
    public void removeReservation(String username, String date) throws SQLException, ClassNotFoundException{
        UserController userController = new UserController();
        userController.removeReservation(username, date);
    }
    public void removeAllReservationsByDate(String date) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.removeAllReservationsByDate(date);
    }
    public void removeAllReservationsByUser(String username) throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.removeAllReservationsByUser(username);
    }
    //getters
    public Typology getTypology(String name) throws  ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        return typologyDAO.getTypology(name);
    }
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
    public ArrayList<Postation> getAvailablePostations() throws ClassNotFoundException, SQLException{
        UserController userController = new UserController();
        return userController.getAvailablePostations();
    }
    public ArrayList<Typology> getTypologies() throws ClassNotFoundException, SQLException{
        UserController userController = new UserController();
        return userController.getTypologies();
    }
    public ArrayList<Reservation> getAllReservations() throws ClassNotFoundException, SQLException{
        ReservationDAO reservationDAO = new ReservationDAO();
        return reservationDAO.SelectAllReservations();
    }
    public ArrayList<Reservation> getUserReservations(String username) throws ClassNotFoundException, SQLException{
        UserController userController = new UserController();
        return userController.getUserReservations(username);
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
    public ArrayList<Typology> getAllTypologies() throws SQLException, ClassNotFoundException{
        TypologyDAO typologyDAO = new TypologyDAO();
        return  typologyDAO.selectAllTypologies();
    }
    // updaters
    public void updateSunbeds(String code, int n) throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.updateSunbeds(code,n);
    }
    public void updateDeckchairs(String code, int n) throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.updateDeckchairs(code,n);
    }
    public void updateChairs(String code, int n) throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.updateChairs(code,n);
    }
    public void updateMaterialSunbeds(String code, String material) throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        typologyDAO.updateMaterialSunbeds(code,material);
    }
    public void updateGazebo(String code) throws ClassNotFoundException, SQLException{
        TypologyDAO typologyDAO = new TypologyDAO();
        Typology typology = typologyDAO.getTypology(code);
        typologyDAO.updateGazebo(code,typology.getGazebo());
    }
    public void updateZone(int number, String zone) throws ClassNotFoundException, SQLException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.updateZone(number, zone);
    }
    public void updateTypology(String typo, int posId) throws ClassNotFoundException, SQLException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.updateTypology(typo, posId);;
    }
    public void updateAvailability(int posId, boolean available) throws ClassNotFoundException, SQLException{
        PostationDAO postationDAO = new PostationDAO();
        postationDAO.updateAvailability(posId, available);
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
