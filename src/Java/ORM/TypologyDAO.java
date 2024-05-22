package ORM;
import DomainModel.*;
import java.sql.*;
import java.util.ArrayList;
public class TypologyDAO {
    private Connection connection;
    public TypologyDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
    public void insertTypology(String name, int n_chairs, String m_sunbeds, int n_deckchairs, int n_sunbeds, boolean gazebo)
            throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO Typology (typename, n_sunbeds, n_chairs, n_deckchairs, m_sunbeds, gazebo)" +
                "VALUES ('%s','%d','%d', '%d', '%s', true)",name,n_sunbeds, n_chairs, n_deckchairs,m_sunbeds,gazebo);
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Typology has been added successfully");
        }catch (SQLException e){
            System.err.println("Error occurred while adding to the database: " + e.getMessage());
        }
    }

    public ArrayList<Typology> selectAllTypologies() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Typology";
        return SelectTypology(sql);
    }


    private ArrayList<Typology> SelectTypology(String sql) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Typology> typologies = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("typename");
            int n_chairs = rs.getInt("n_chairs");
            int n_deckchairs= rs.getInt("n_deckchairs");
            int n_sunbeds=rs.getInt("n_sunbeds");
            String m_sunbeds=rs.getString("m_sunbeds");
            boolean gazebo = rs.getBoolean("gazebo");
            
            Typology typology = new Typology(id,n_chairs,n_deckchairs,n_sunbeds,m_sunbeds,gazebo);
            typologies.add(typology);
        }
        ps.close();
        return typologies;
    }

    public void removeTypology(String id) throws SQLException, ClassNotFoundException {
        String sql = String.format("DELETE FROM Typology WHERE typename = '%s'",id);
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Position removed successfully");
        }catch (SQLException e){
            System.err.println("Error occurred while removing from database: " + e.getMessage());
        }
    }

    public Typology getTypology(String id) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM Typology  WHERE typename='%s'", id);
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Typology typology = null;
        if (rs.next()) {
            int n_chairs = rs.getInt("n_chairs");
            int n_deckchairs= rs.getInt("n_deckchairs");
            int n_sunbeds=rs.getInt("n_sunbeds");
            String m_sunbeds=rs.getString("m_sunbeds");
            boolean gazebo = rs.getBoolean("gazebo");
            
            typology = new Typology(id,n_chairs,n_deckchairs,n_sunbeds,m_sunbeds,gazebo);

        }
        return typology;
    }
    public void updateTypology(String sql, String msg) throws SQLException, ClassNotFoundException{
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
    public void updateSunbeds(String code, int n_sunbeds) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Typology SET n_sunbeds = %d WHERE typename = '%s'", n_sunbeds, code); 
        String msg = "Sunbeds correctly updated";
        updateTypology(sql, msg);
    }
    public void updateDeckchairs(String code, int n_deckchairs) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Typology SET n_deckchairs = %d WHERE typename = '%s'", n_deckchairs, code); 
        String msg = "Deckchairs correctly updated";
        updateTypology(sql, msg);
    }
    public void updateChairs(String code, int n_chairs) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Typology SET n_chairs = %d WHERE typename = '%s'", n_chairs, code); 
        String msg = "Chairs correctly updated";
        updateTypology(sql, msg);
    }
    public void updateMaterialSunbeds(String code, String m_sunbeds) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Typology SET m_sunbeds = %s WHERE typename = '%s'", m_sunbeds, code); 
        String msg = "Material of sunbeds correctly updated";
        updateTypology(sql, msg);
    }
    public void updateGazebo(String code, boolean gazebo) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Typology SET gazebo = %b WHERE typename = '%s'", !gazebo, code); 
        String msg = "Gazebo correctly updated";
        updateTypology(sql, msg);
    }
}
