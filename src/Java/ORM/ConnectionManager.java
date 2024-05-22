package ORM;

import java.sql.*;

public class ConnectionManager {

    private static final String url = "jdbc:postgresql://localhost:5432/PoolManagerDB";
    private static final String username = "poolUser";
    private static final String password = "poolUser";
    private static Connection connection = null;

    // singleton instance
    private static ConnectionManager instance = null;

    private ConnectionManager(){}

    public static ConnectionManager getInstance() {


        if (instance == null) { instance = new ConnectionManager(); }

        return instance;

    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        System.out.println("Entrati in getConnection()");

        if (connection == null){
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        return connection;

    }

}