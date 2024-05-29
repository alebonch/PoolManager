package ORM;
import DomainModel.TimeRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void addTimeRecord(){}
    public void removeTimeRecord(){}
    public TimeRecord SelectTimeRecord(String sql){}
}
