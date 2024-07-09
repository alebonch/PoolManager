package ORM;
import DomainModel.TimeRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TimeRecordDAO{

    Connection connection;
    public TimeRecordDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
        public int addTimeRecord(String date, String turno) throws SQLException {
    String insert = "INSERT INTO TimeRecord (date, turno) VALUES (?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, date);
        stmt.setString(2, turno);
        stmt.executeUpdate();
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating TimeRecord failed, no ID obtained.");
            }
        }
    }
}

    public ArrayList<TimeRecord> getAllTimeRecord() throws SQLException, ClassNotFoundException{
        String sql = String.format("SELECT * FROM TimeRecord ORDER BY id");
        ArrayList<TimeRecord> timeRecords;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            timeRecords = new ArrayList<>();
            while (rs.next()) {
                int Id = rs.getInt("id");
                String date = rs.getString("date");
                String turno = rs.getString("turno");
                TimeRecord timeRecord = new TimeRecord(Id,date,turno);
                timeRecords.add(timeRecord);
            }
        }
        return timeRecords;

    }

    public TimeRecord getTimeRecord(String date, String turno) throws SQLException, ClassNotFoundException{
        String sql = String.format("SELECT * FROM TimeRecord WHERE date = '%s' AND turno = '%s'", date, turno);
        return selectTimeRecord(sql);
    }
    public TimeRecord getTimeRecord(int id) throws SQLException, ClassNotFoundException{
        String sql = String.format("SELECT * FROM TimeRecord WHERE id = '%d'", id);
        return selectTimeRecord(sql);
    }

    public TimeRecord selectTimeRecord(String sql) throws SQLException, ClassNotFoundException{
        TimeRecord timeRecord = new TimeRecord(0,"a","a");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int Id = rs.getInt("id");
                String date = rs.getString("date");
                String turno = rs.getString("turno");
                timeRecord = new TimeRecord(Id,date,turno);
            }
        }
        return timeRecord;
    }
    public void removeTimeRecord(int id){
        String sql = String.format("DELETE FROM TimeRecord WHERE id = '%d'",id);
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("TimeRecord removed successfully");
        }catch (SQLException e){
            System.err.println("Error occurred while removing from database: " + e.getMessage());
        }
    }
    public void removeTimeRecord(String date, String turno){
        String sql = String.format("DELETE FROM TimeRecord WHERE date = '%s' AND turno = '%s'",date, turno);
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("TimeRecord removed successfully");
        }catch (SQLException e){
            System.err.println("Error occurred while removing from database: " + e.getMessage());
        }
    }
}
