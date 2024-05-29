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
import java.util.ArrayList;


public class ObjectDAO{
    Connection connection;
    public ObjectDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public ArrayList<Object> getObject(String name) throws SQLException, ClassNotFoundException{
         String sql = String.format("SELECT * FROM Object  WHERE name='%s'", name);
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Object> objects = null;
        if (rs.next()) {
            String nameSQL = rs.getString("name");
            int totalnumber= rs.getInt("totalnumber");
            int price=rs.getInt("price");
            String material = rs.getString("material");
            String color = rs.getString("color");
            Object object=null;
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
            objects.add(object);
        }
        return objects;
    }

    public void updateTotalNumber(int newtotalNumber){
        
    }
}