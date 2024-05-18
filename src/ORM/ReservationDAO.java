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

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participation added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeParticipation(int username, String date) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM Reservation WHERE userId = %s AND date = %s", username, date);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participation removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeAllReservationsByUser(String userId) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM Reservation WHERE userId = %s", userId);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participations removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeAllReservationsByDate(String date) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM Reservation WHERE date = %s", date);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participations removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }
    private ArrayList<Reservation> SelectReservations(String sql) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Reservation> reservations = new ArrayList<>();
        while (rs.next()) {
            String userId = rs.getString("userId");
            UserDAO userDAO = new UserDAO();
            int postation = rs.getInt("postation");
            PostationDAO positionDAO = new PostationDAO();
            String date = rs.getString("date");
            Reservation reservation = new Reservation(userDAO.getUser(userId), positionDAO.getPostation(postation), date);
            reservations.add(reservation);
        }
        ps.close();
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
}