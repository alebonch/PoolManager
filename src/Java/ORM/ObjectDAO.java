package ORM;
import DomainModel.Chair;
import DomainModel.Deckchair;
import DomainModel.Object;
import DomainModel.Sunbed;
import DomainModel.Table;
import DomainModel.Umbrella;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ObjectDAO{
    Connection connection;
    public ObjectDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public Object getObject(String name) throws SQLException, ClassNotFoundException{
         String sql = String.format("SELECT * FROM Object  WHERE name='%s'", name);
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Object object=null;
        if (rs.next()) {
            String nameSQL = rs.getString("name");
            int totalnumber= rs.getInt("totalnumber");
            int price=rs.getInt("price");
            String material = rs.getString("material");
            String color = rs.getString("color");
            if(name=="chair"){
                object = new Chair(name, totalnumber,color,price);
            }
            if(name=="deckchair"){
                object = new Deckchair(color, material, totalnumber, name, price);
            }
            if(name=="sunbed"){
                object = new Sunbed(material, totalnumber, price, name);
            }
            if(name=="umbrella"){
                object = new Umbrella(color, totalnumber, price, name);
            }
            if(name=="table"){
                object = new Table(color, material, totalnumber, price, name);
            }
        }
        return object;
    }

    public void updateTotalNumber(int newtotalNumber, String name){
        String sql = String.format("UPDATE Object SET totalnumber = %d WHERE name = '%s'", newtotalNumber,name); 
        String msg = "Total number correctly updated";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println(msg);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}