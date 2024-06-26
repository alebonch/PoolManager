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

    public void addReservation(String username, int postation,  int date) throws SQLException, ClassNotFoundException {
        
        PostationDAO postationDAO = new PostationDAO();
        ArrayList<DomainModel.Object> objects = postationDAO.getPostation(postation).get(0).getObjects();
        int sumcost=0;
        TimeRecordDAO timeRecordDAO = new TimeRecordDAO();
        String turno = timeRecordDAO.getTimeRecord(date).getTurno();
        for (DomainModel.Object object : objects){
            sumcost += (object.getPrice() * object.getNumber());
        }
        if(turno.equals("Pomeriggio")) {
        sumcost=(int) Math.round(sumcost*0.6);
        }
        String sql = String.format("INSERT INTO Reservation (userId, postation, date, cost) " +
                                   "VALUES ('%s', %d ,%d,%d)", username, postation, date,sumcost);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Participation added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void updateCosts(int postation, int date) throws SQLException, ClassNotFoundException{
        PostationDAO postationDAO = new PostationDAO();
        ArrayList<DomainModel.Object> objects = postationDAO.getPostation(postation).get(0).getObjects();
        int sumcost=0;
        TimeRecordDAO timeRecordDAO = new TimeRecordDAO();
        String turno = timeRecordDAO.getTimeRecord(date).getTurno();
        for (DomainModel.Object object : objects){
            sumcost += (object.getPrice() * object.getNumber());
        }
        if(turno.equals("Pomeriggio")) {
        sumcost=(int) Math.round(sumcost*0.6);
        }
        String sql = String.format("UPDATE Reservation SET cost = %d WHERE postation = %d AND date = %d", sumcost,postation,date);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Cost updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void removeReservation(String username, int  date) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM Reservation WHERE userId = '%s' AND date = '%d'", username, date);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Participation removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void removeAllReservationsByUser(String userId) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM Reservation WHERE userId = '%s'", userId);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Participations removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void removeAllReservationsByDate(int  date) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM Reservation WHERE date = '%d'", date);

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
                int date = rs.getInt("date");
                TimeRecordDAO timeRecordDAO = new TimeRecordDAO();
                Reservation reservation = new Reservation(userDAO.getUser(userId), positionDAO.getPostation(postation).get(0), timeRecordDAO.getTimeRecord(date));
                reservations.add(reservation);
            }
        }
        return reservations;
    }
    public ArrayList<Reservation> SelectAllReservations() throws SQLException, ClassNotFoundException{
        String sql= "SELECT * FROM Reservation";
        return SelectReservations(sql);
    }
    public ArrayList<Reservation> SelectReservationsByDate(int date) throws SQLException, ClassNotFoundException{
        String sql= String.format("SELECT * FROM Reservation WHERE date = '%d'", date);
        return SelectReservations(sql);
    }
    public ArrayList<Reservation> SelectReservationsByUser(String username) throws SQLException, ClassNotFoundException{
        String sql= String.format("SELECT * FROM Reservation WHERE userId = '%s'", username);
        return SelectReservations(sql);
    }
    public ArrayList<Reservation> SelectReservationsByUserAndDate(String username, int date) throws SQLException, ClassNotFoundException{
        String sql= String.format("SELECT * FROM Reservation WHERE userId = '%s' AND date = '%d'", username, date);
        return SelectReservations(sql);
    }
    public ArrayList<Reservation> SelectReservationsByPostation(int postation) throws SQLException, ClassNotFoundException{
        String sql= String.format("SELECT * FROM Reservation WHERE postation = %d", postation);
        return SelectReservations(sql);
    }
    public ArrayList<Reservation> SelectReservationsByPostationAndDate(int postation, int date) throws SQLException, ClassNotFoundException{
        String sql= String.format("SELECT * FROM Reservation WHERE postation = %d AND date = '%d'", postation, date);
        return SelectReservations(sql);
    }
}