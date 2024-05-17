package ORM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DomainModel.Position;

public class PositionDAO{
    private Connection connection;
    public PositionDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
    public void insertPosition(int number , int type, String zone)
            throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO Postation (number, type, zone, availability)" +
                "VALUES ('%d', '%d', '%s', true)",number, type, zone);
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Postation has been added successfully");
        }catch (SQLException e){
            System.err.println("Error occurred while adding to the database: " + e.getMessage());
        }
    }

    public ArrayList<Position> selectAllPositions() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Postation";
        return SelectPosition(sql);
    }

    public ArrayList<Position> selectAvailablePositions() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Postation WHERE availability";
        return SelectPosition(sql);
    }

    private ArrayList<Position> SelectPosition(String sql) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<Position> positions = new ArrayList<>();
        while (rs.next()) {
            int number = rs.getInt("number");
            int type = rs.getInt("type");
            String zone = rs.getString("zone");
            boolean available = rs.getBoolean("availability");
            TypologyDAO typo= new TypologyDAO();
            Position pos = new Position(number, typo.getTypology(type), zone, available);
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

    public Position getPosition(int id) throws SQLException, ClassNotFoundException {
        Connection con = ConnectionManager.getInstance().getConnection();
        String sql = String.format("SELECT * FROM Postation  WHERE number='%d'", id);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Position position = null;
        if (rs.next()) {
            int number = rs.getInt("number");
            int type = rs.getInt("type");
            String zone = rs.getString("zone");
            TypologyDAO typo=new TypologyDAO();
            position = new Position(number, typo.getTypology(type), zone);

        }
        return position;
    }
}
