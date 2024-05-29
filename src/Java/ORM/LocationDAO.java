package ORM;
import DomainModel.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LocationDAO{
    Connection connection;
    public LocationDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void removeLocation(int id) throws SQLException, ClassNotFoundException{
        String sql = String.format("DELETE FROM Location WHERE id = '%d'",id);
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            System.out.println("Location removed successfully");
        }catch (SQLException e){
            System.err.println("Error occurred while removing from database: " + e.getMessage());
        }
    }

    public void addLocation(int id, String description){
        String sql = String.format("INSERT INTO Location (id,description) " +
                                   "VALUES (%d, '%s')", id, description);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Location added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public ArrayList<Location> getAllLocations() throws SQLException, ClassNotFoundException{
        String sql = String.format("SELECT * FROM Location");
        return selectLocations(sql);
    }

    public ArrayList<Location> getLocation(int id) throws SQLException, ClassNotFoundException{
        String sql = String.format("SELECT * FROM Location WHERE id = %d",id);
        return selectLocations(sql);
    }

    public ArrayList<Location> selectLocations(String sql) throws SQLException, ClassNotFoundException{
        ArrayList<Location> locations;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            locations = new ArrayList<>();
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String description = rs.getString("description");
                Location location = new Location(Id, description);
                locations.add(location);
            }
        }
        return locations;
    }
}