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
        String sql = String.format("INSERT INTO users (mail, password, name, surname, telephone) VALUES "+
                "('%s', '%s', '%s', '%s', '%s')", mail, password, name, surname, telephone);
    try{
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
        System.out.println("User added successfully");
}   catch (SQLException e){
        System.err.println("Error during the registration: " + e.getMessage());
}
}
public void insertUser(String name, String surname, String mail, String password)
        throws SQLException, ClassNotFoundException {
        String sql = String.format("INSERT INTO users (mail, password, name, surname) VALUES "+
                "('%s', '%s', '%s', '%s')", mail, password, name, surname);
    try{
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
        System.out.println("User added successfully");
}   catch (SQLException e){
        System.err.println("Error during the registration: " + e.getMessage());
}}

public User checkPassword(String username, String password) throws SQLException, ClassNotFoundException {
    String sql = String.format("SELECT * FROM users WHERE username = '%s'", username);
    PreparedStatement ps = connection.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        String passwordFromDatabase = rs.getString("pwd");
        if (password.equals(passwordFromDatabase)) {
            System.out.println("Login Succeded!");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String mail = rs.getString("mail");
            String Telephone = rs.getString("telephone");
            return new User(mail, password, name, surname, Telephone);
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    } else {
        System.out.println("Couldn't find username.");
        return null;
    }
}
public void removeUser(String mail) throws SQLException, ClassNotFoundException {

    String sql = String.format("DELETE FROM \"User\" WHERE username = '%s'", mail);

    PreparedStatement preparedStatement = null;

    // remove Reservations (CASCADE DELETE)
    removeReservations(mail);

    try {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        System.out.println("User removed successfully.");
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    } finally {
        if (preparedStatement != null) { preparedStatement.close(); }
    }

}
public void removeReservations(String user){}

public User getUser(String username) throws SQLException, ClassNotFoundException {

    User user = null;

    String sql = String.format("SELECT * FROM \"User\" WHERE username = '%s'", username);

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
            user = new User(username, name,surname,telephone,password);

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

    String sql = String.format("SELECT * FROM \"User\" ORDER BY id ASC");

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
            users.add(new User(username, name,surname,telephone,password));
        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    } finally {
        if (preparedStatement != null) { preparedStatement.close(); }
        if (resultSet != null) { resultSet.close(); }
    }

    return users;

}
}
