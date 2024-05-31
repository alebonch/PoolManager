package ORM;
import DomainModel.Chair;
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
    public void insertPostation(int number , int n_chair, int n_table, int n_umbrella, int n_deckchair, int n_sunbed, int location)
        throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO Postation (number, n_chair, n_deckchair, n_sunbed, n_table, n_umbrella, zone)" +
                "VALUES ('%d', '%d', '%d', '%d', '%d', '%d', '%d')",number, n_chair, n_deckchair, n_sunbed, n_table, n_umbrella, location);
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

    private ArrayList<Postation> SelectPostation(String sql) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Postation> positions = new ArrayList<>();
        ArrayList<DomainModel.Object> objects = null;
        ObjectDAO objectDAO = new ObjectDAO();
        LocationDAO locationDAO = new LocationDAO();
        while (rs.next()) {
            int number = rs.getInt("number");
            int n_chair = rs.getInt("n_chair"); 
            int n_table = rs.getInt("n_deckchair");
            int n_umbrella = rs.getInt("n_sunbed"); 
            int n_deckchair = rs.getInt("n_table"); 
            int n_sunbed = rs.getInt("n_umbrella"); 
            int location = rs.getInt("zone");
            DomainModel.Object tmp = objectDAO.getObject("chair");
            tmp.setTotal(n_chair);
            objects.add(tmp);
            tmp = objectDAO.getObject("deckchair");
            tmp.setTotal(n_deckchair);
            objects.add(tmp);
            tmp = objectDAO.getObject("sunbed");
            tmp.setTotal(n_sunbed);
            objects.add(tmp);
            tmp = objectDAO.getObject("table");
            tmp.setTotal(n_table);
            objects.add(tmp);
            tmp = objectDAO.getObject("umbrella");
            tmp.setTotal(n_umbrella);
            objects.add(tmp);
            Postation pos = new Postation(number, objects, locationDAO.getLocation(location).get(0));
            positions.add(pos);
            
        }
        ps.close();
        return positions;
    }

    public void removePos(int id) throws SQLException, ClassNotFoundException {
        String sql = String.format("DELETE FROM Postation WHERE number = '%d'",id);
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Position removed successfully");
        }catch (SQLException e){
            System.err.println("Error occurred while removing from database: " + e.getMessage());
        }
    }

    public ArrayList<Postation> getPostation(int id) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM Postation  WHERE number = '%d'", id);
        return SelectPostation(sql);
    }
    private void updatePostation(String sql, String msg) throws SQLException, ClassNotFoundException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println(msg);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public void updateChairs(int id, int chairs) throws SQLException, ClassNotFoundException{
        String sql = String.format("UPDATE Postation SET n_chair = %d WHERE id = %d", chairs,id);
        updatePostation(sql, "Postation's chairs updated");
    }
    public void updateDeckchairs(int id, int deckchair) throws SQLException, ClassNotFoundException{
        String sql = String.format("UPDATE Postation SET n_deckchair = %d WHERE id = %d", deckchair,id);
        updatePostation(sql, "Postation's deckchairs updated");
    }
    public void updateSunbed(int id, int sunbed) throws SQLException, ClassNotFoundException{
        String sql = String.format("UPDATE Postation SET n_sunbed = %d WHERE id = %d", sunbed,id);
        updatePostation(sql, "Postation's sunbeds updated");
    }
    public void updateTable(int id, int table) throws SQLException, ClassNotFoundException{
        String sql = String.format("UPDATE Postation SET n_table = %d WHERE id = %d", table,id);
        updatePostation(sql, "Postation's tables updated");
    }
    public void updateUmbrella(int id, int umbrella) throws SQLException, ClassNotFoundException{
        String sql = String.format("UPDATE Postation SET n_umbrella = %d WHERE id = %d", umbrella,id);
        updatePostation(sql, "Postation's umbrellas updated");
    }
}
