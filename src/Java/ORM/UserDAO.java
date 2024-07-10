package ORM;
import DomainModel.*;
import java.sql.*;
import java.util.ArrayList;
public class UserDAO {

    private Connection connection;

    public UserDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
    public void insertUser(String name, String surname, String telephone, String mail, String password)
        throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO Users (mail, password, name, surname, telephone) VALUES "+
                "('%s', '%s', '%s', '%s', '%s')", mail, password, name, surname, telephone);
    try{
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.executeUpdate();
            }
        System.out.println("User added successfully");
}   catch (SQLException e){
        System.err.println("Error during the registration: " + e.getMessage());
}
}
public void insertUser(String name, String surname, String mail, String password)
        throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO Users (mail, password, name, surname) VALUES "+
                "('%s', '%s', '%s', '%s')", mail, password, name, surname);
    try{
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.executeUpdate();
            }
        System.out.println("User added successfully");
}   catch (SQLException e){
        System.err.println("Error during the registration: " + e.getMessage());
}}

public User checkPassword(String username, String password) throws SQLException, ClassNotFoundException {
    String sql = String.format("SELECT * FROM Users WHERE mail = '%s'", username);
    PreparedStatement ps = connection.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        String passwordFromDatabase = rs.getString("password");
        if (password.equals(passwordFromDatabase)) {
            System.out.println("Login Succeded!");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String telephone = rs.getString("telephone");
            password = rs.getString("password");
            return  new User(username, telephone,name,surname,password);
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    } else {
        System.out.println("Couldn't find username.");
        return null;
    }
}


public User getUser(String username) throws SQLException, ClassNotFoundException {

    User user = null;

    String sql = String.format("SELECT * FROM Users WHERE mail = '%s'", username);

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String telephone = resultSet.getString("telephone");
            String password = resultSet.getString("password");
            user = new User(username, telephone,name,surname,password);

        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    } finally {
        if (preparedStatement != null) { preparedStatement.close(); }
        if (resultSet != null) { resultSet.close(); }
    }

    return user;

}

public ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException {

    ArrayList<User> users = new ArrayList<>();

    String sql = String.format("SELECT * FROM Users");

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String username = resultSet.getString("mail");
            String telephone = resultSet.getString("telephone");
            String password = resultSet.getString("password");
            users.add(new User(username, telephone,name,surname,password));
        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    } finally {
        if (preparedStatement != null) { preparedStatement.close(); }
        if (resultSet != null) { resultSet.close(); }
    }

    return users;

}
public void removeUser(String username) throws SQLException, ClassNotFoundException {
    Connection con = ConnectionManager.getInstance().getConnection();
    String sql = String.format("DELETE FROM Users WHERE mail = '%s'", username);
    try{
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
        }
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.removeAllReservationsByUser(username);
        System.out.println("User removed successfully");
    }catch (SQLException e){
        System.err.println("Error occurred while removing from database: " + e.getMessage());
    }
}

public void updateUser(String sql, String msg) throws SQLException, ClassNotFoundException{
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.executeUpdate();
        System.out.println(msg);
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    }
}
public void updatePassword(String mail, String newPwd) throws ClassNotFoundException, SQLException{
    String sql = String.format("UPDATE Users SET password = '%s' WHERE mail = '%s'", newPwd, mail); 
    String msg = "Password correctly updated";
    updateUser(sql, msg);
}
public void updateTelephone(String mail, String telephone) throws ClassNotFoundException, SQLException{
    String sql = String.format("UPDATE Users SET telephone = '%s' WHERE mail = '%s'", telephone, mail); 
    String msg = "Telephone correctly updated";
    updateUser(sql, msg);
}
}

