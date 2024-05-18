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
    public void insertTypology(int n_chairs, String m_sunbeds, int n_deckchairs, int n_sunbeds, boolean gazebo)
            throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO Typology (n_sunbeds, n_chairs, n_deckchairs, m_sunbeds, gazebo)" +
                "VALUES ('%d', '%d', '%s', true)",n_sunbeds, n_chairs, n_deckchairs,m_sunbeds,gazebo);
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
            int id = rs.getInt("number");
            int n_chairs = rs.getInt("type");
            int n_deckchairs= rs.getInt("zone");
            int n_sunbeds=rs.getInt("n_sunbeds");
            String m_sunbeds=rs.getString("m_sunbeds");
            boolean gazebo = rs.getBoolean("gazebo");
            
            Typology typology = new Typology(id,n_chairs,n_deckchairs,n_sunbeds,m_sunbeds,gazebo);
            typologies.add(typology);
        }
        ps.close();
        return typologies;
    }

    public void removeTypology(int id) throws SQLException, ClassNotFoundException {
        String sql = String.format("DELETE FROM Typology WHERE code = '%d'",id);
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Position removed successfully");
        }catch (SQLException e){
            System.err.println("Error occurred while removing from database: " + e.getMessage());
        }
    }

    public Typology getTypology(int id) throws SQLException, ClassNotFoundException {
        String sql = String.format("SELECT * FROM Typology  WHERE code='%d'", id);
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Typology typology = null;
        if (rs.next()) {
            int n_chairs = rs.getInt("type");
            int n_deckchairs= rs.getInt("zone");
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
    public void updateSunbeds(int code, int n_sunbeds) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Typology SET n_sunbeds = %d WHERE code = %d", n_sunbeds, code); 
        String msg = "Sunbeds correctly updated";
        updateTypology(sql, msg);
    }
    public void updateDeckchairs(int code, int n_deckchairs) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Typology SET n_deckchairs = %d WHERE code = %d", n_deckchairs, code); 
        String msg = "Deckchairs correctly updated";
        updateTypology(sql, msg);
    }
    public void updateChairs(int code, int n_chairs) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Typology SET n_chairs = %d WHERE code = %d", n_chairs, code); 
        String msg = "Chairs correctly updated";
        updateTypology(sql, msg);
    }
    public void updateMaterialSunbeds(int code, String m_sunbeds) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Typology SET m_sunbeds = %s WHERE code = %d", m_sunbeds, code); 
        String msg = "Material of sunbeds correctly updated";
        updateTypology(sql, msg);
    }
    public void updateGazebo(int code, boolean gazebo) throws ClassNotFoundException, SQLException{
        String sql = String.format("UPDATE Typology SET gazebo = %b WHERE code = %d", gazebo, code); 
        String msg = "Gazebo correctly updated";
        updateTypology(sql, msg);
    }
}
