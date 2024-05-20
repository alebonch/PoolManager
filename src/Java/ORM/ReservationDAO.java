package ORM;

import DomainModel.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAO {

    private Connection connection;

    public ReservationDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void addReservation(String username, int postation, int paymentMethod, String date) throws SQLException, ClassNotFoundException {

        String sql = String.format("INSERT INTO Reservation (userId, postation, date, paymentMethod) " +
                                   "VALUES (%s, %d, %s,%d)", username, postation, date, paymentMethod);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Participation added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
    public void addReservation(String username, int postation, String date) throws SQLException, ClassNotFoundException {

        String sql = String.format("INSERT INTO Reservation (userId, postation, date) " +
                                   "VALUES (%s, %d, %s)", username, postation, date);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Participation added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void removeReservation(String username, String date) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM Reservation WHERE userId = %s AND date = %s", username, date);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Participation removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void removeAllReservationsByUser(String userId) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM Reservation WHERE userId = %s", userId);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Participations removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void removeAllReservationsByDate(String date) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM Reservation WHERE date = %s", date);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Participations removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
    private ArrayList<Reservation> SelectReservations(String sql) throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> reservations;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            reservations = new ArrayList<>();
            while (rs.next()) {
                String userId = rs.getString("userId");
                UserDAO userDAO = new UserDAO();
                int postation = rs.getInt("postation");
                PostationDAO positionDAO = new PostationDAO();
                String date = rs.getString("date");
                Reservation reservation = new Reservation(userDAO.getUser(userId), positionDAO.getPostation(postation), date);
                reservations.add(reservation);
            }
        }
        return reservations;
    }
    public ArrayList<Reservation> SelectAllReservations() throws SQLException, ClassNotFoundException{
        String sql= "SELECT * FROM Reservation";
        return SelectReservations(sql);
    }
    public ArrayList<Reservation> SelectReservationsByDate(String date) throws SQLException, ClassNotFoundException{
        String sql= String.format("SELECT * FROM Reservation WHERE date = %s", date);
        return SelectReservations(sql);
    }
    public ArrayList<Reservation> SelectReservationsByUser(String username) throws SQLException, ClassNotFoundException{
        String sql= String.format("SELECT * FROM Reservation WHERE userId = %s", username);
        return SelectReservations(sql);
    }
    public ArrayList<Reservation> SelectReservationsByUserAndDate(String username, String date) throws SQLException, ClassNotFoundException{
        String sql= String.format("SELECT * FROM Reservation WHERE userId = %s AND date = %s", username, date);
        return SelectReservations(sql);
    }
    public ArrayList<Reservation> SelectReservationsByPostation(int postation) throws SQLException, ClassNotFoundException{
        String sql= String.format("SELECT * FROM Reservation WHERE postation = %d", postation);
        return SelectReservations(sql);
    }
    public ArrayList<Reservation> SelectReservationsByPostationAndDate(int postation, String date) throws SQLException, ClassNotFoundException{
        String sql= String.format("SELECT * FROM Reservation WHERE postation = %d AND date = %s", postation, date);
        return SelectReservations(sql);
    }
    public void updatePaymentMethods(String mail, String date, int paymentMethod) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Reservation SET PaymentMethods = %d WHERE mail = %s AND date = %s", paymentMethod, mail, date); 
        String msg = "PaymentMethods correctly updated";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println(msg);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}