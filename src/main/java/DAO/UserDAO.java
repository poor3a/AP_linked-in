package DAO;

import exceptions.UserDAOException;

import java.sql.*;

public class UserDAO {
    Connection connection;
    Statement statement;

    public UserDAO() throws SQLException {
            this.connection = SQL.getConnection();
            this.statement = connection.createStatement();
    }

    public void createUser(String username, String password, String email) throws UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(username ,password ,email) values (?,?,?)"
            );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error creating user");
        }
    }

    public boolean userExist_username(String username) throws UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new UserDAOException("Error checking if user exists by username");
        }
    }

    public boolean userExist_email(String email) throws UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE email = ?"
            );
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new UserDAOException("Error checking if user exists by email");
        }
    }

    public boolean checkUserPassword(String username, String password) throws UserDAOException {
        if (!userExist_username(username)) {
            throw new UserDAOException("User does not exist");
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND password = ?"
            );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new UserDAOException("Error checking user password");
        }
    }

    public int getUserId(String username) throws UserDAOException {
        if (!userExist_username(username)) {
            throw new UserDAOException("User does not exist");
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id FROM users WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new UserDAOException("Error getting user id by username");
        }
    }
    public String getUserPassword(String username) throws UserDAOException {
        if (!userExist_username(username)) {
            throw new UserDAOException("User does not exist");
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT password FROM users WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("password");
        } catch (SQLException e) {
            throw new UserDAOException("Error getting user password by username");
        }
    }

    public String getUserEmail(String username) throws UserDAOException {
        if (!userExist_username(username)) {
            throw new UserDAOException("User does not exist");
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT email FROM users WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("email");
        } catch (SQLException e) {
            throw new UserDAOException("Error getting user email by username");
        }
    }

    public void deleteUser(String username , String password) throws UserDAOException {
        try {
            if (!checkUserPassword(username, password)) {
                throw new UserDAOException("Access denied");
            }else if (!userExist_username(username)){
                throw new UserDAOException("User does not exist");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE username = ? AND password = ?"
            );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error deleting user");
        }
    }
    public void updatePassword(String username , String newPassword) throws UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET password = ? WHERE username = ?"
            );
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error updating user password");
        }
    }
    public void updateEmail(String username , String newEmail) throws UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET email = ? WHERE username = ?"
            );
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error updating user email");
        }
    }
}