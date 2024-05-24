package ORM;
import DomainModel.Postation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostationDAO{
    private Connection connection;
    public PostationDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
    public void insertPostation(int number , String type, String zone)
            throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO Postation (number, type, zone, availability)" +
                "VALUES ('%d', '%s', '%s', true)",number, type, zone);
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Postation has been added successfully");
        }catch (SQLException e){
            System.err.println("Error occurred while adding to the database: " + e.getMessage());
        }
    }

    public ArrayList<Postation> selectAllPostations() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Postation ORDER BY number";
        return SelectPostation(sql);
    }

    public ArrayList<Postation> selectAvailablePostations() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Postation WHERE availability ORDER BY number";
        return SelectPostation(sql);
    }

    private ArrayList<Postation> SelectPostation(String sql) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Postation> positions = new ArrayList<>();
        while (rs.next()) {
            int number = rs.getInt("number");
            String type = rs.getString("type");
            String zone = rs.getString("zone");
            boolean available = rs.getBoolean("availability");
            TypologyDAO typo= new TypologyDAO();
            Postation pos = new Postation(number, typo.getTypology(type), zone, available);
            positions.add(pos);
        }
        ps.close();
        return positions;
    }

    public void removePos(int id) throws SQLException, ClassNotFoundException {
        String sql = String.format("DELETE FROM cars_view WHERE number = '%d'",id);
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Position removed successfully");
        }catch (SQLException e){
            System.err.println("Error occurred while removing from database: " + e.getMessage());
        }
    }

    public Postation getPostation(int id) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getInstance().getConnection();
        String sql = String.format("SELECT * FROM Postation  WHERE number = '%d'", id);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Postation position = null;
        if (rs.next()) {
            int number = rs.getInt("number");
            String type = rs.getString("type");
            String zone = rs.getString("zone");
            TypologyDAO typo=new TypologyDAO();
            position = new Postation(number, typo.getTypology(type), zone);

        }
        return position;
    }
    private void updatePostation(String sql, String msg) throws SQLException, ClassNotFoundException{
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println(msg);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }
    }
    public void updateTypology(String id, int posId) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Postation SET type = '%s' WHERE number = %d", id, posId); 
        String msg = "Typology correctly updated";
        updatePostation(sql, msg);
    }
    public void updateZone(int id, String zone) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Postation SET zone = %s WHERE number = %d", zone, id); 
        String msg = "Zone correctly updated";
        updatePostation(sql, msg);
    }
    public void updateAvailability(int id, boolean availability)throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Postation SET availability = %b WHERE number = %d", availability, id); 
        String msg = "Availability correctly updated";
        updatePostation(sql, msg);
    }
}
