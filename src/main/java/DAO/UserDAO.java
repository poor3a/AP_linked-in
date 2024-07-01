package DAO;

import exceptions.UserDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.User;

public class UserDAO {
    Connection connection;
    Statement statement;

    public UserDAO() throws SQLException {
            this.connection = SQL.getConnection();//this is a method that is used to get the connection to the database.
            this.statement = connection.createStatement();//this is a method that is used to create a statement object that will be used to execute sql queries.
    }

    public void createUser(String email, String password) throws UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(password ,email) values (?,?)"
            );
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getMessage().contains("pass"))
                throw new UserDAOException("change password");
            throw new UserDAOException("Error creating user");
        }
    }

    public boolean userExist(String email) throws UserDAOException {
        //this method is like the previous one ,but it checks if the user exists by email.
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

    public boolean checkUserPassword(String email, String password) throws UserDAOException {
        //this method is like the previous one ,but it checks if the password is correct.
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE email = ? AND password = ?"
            );
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new UserDAOException("Error checking user password");
        }
    }

    public int getUserId(String email) throws UserDAOException {
        if (!userExist(email)) {
            throw new UserDAOException("User does not exist");
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id FROM users WHERE email = ?"
            );
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new UserDAOException("Error getting user id by email");
        }
    }
    public String getUserPassword(String email) throws UserDAOException {
        if (!userExist(email)) {
            throw new UserDAOException("User does not exist");
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT password FROM users WHERE email = ?"
            );
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("password");
        } catch (SQLException e) {
            throw new UserDAOException("Error getting user password by email");
        }
    }


    public String getUserCreationDate(String email) throws UserDAOException {
        if (!userExist(email)) {
            throw new UserDAOException("User does not exist");
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT creation_date FROM users WHERE email = ?"
            );
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("createing_date");
        } catch (SQLException e) {
            throw new UserDAOException("Error getting user creation date by email");
        }
    }
    //for simplify the code ,previous methods didn't need password to check the user.
    //but for deleting and updating the user we need the password.
    //and checking that may throw an exception that we need to handle in server or client side.
    public void deleteUser(String email , String password) throws UserDAOException {
        try {
            if (!checkUserPassword(email, password)) {
                throw new UserDAOException("Access denied");
            }else if (!userExist(email)){
                throw new UserDAOException("User does not exist");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE email = ? AND password = ?"
            );
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error deleting user");
        }
    }
    public void updatePassword(String email , String newPassword) throws UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET password = ? WHERE email = ?"
            );
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error updating user password");
        }
    }
    public void updateEmail(String email , String newEmail) throws UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET email = ? WHERE email = ?"
            );
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error updating user email");
        }
    }
    public boolean following_sysContains(String follower , String followed) throws UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM following_sys WHERE follower = ? AND followed = ?"
            );
            preparedStatement.setString(1, follower);
            preparedStatement.setString(2, followed);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new UserDAOException("Error checking if following_sys contains follower and followed");
        }
    }
    public void followUser(String email , String followedEmail) throws UserDAOException {
        try {
            if (!userExist(email)) {
                throw new UserDAOException("User does not exist");
            }else if (!userExist(followedEmail)){
                throw new UserDAOException("Followed user does not exist");
            } else if (following_sysContains(email , followedEmail)) {
                throw new UserDAOException("Already following user");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO following_sys(follower ,followed) values (?,?)"
            );
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, followedEmail);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error following user");
        }
    }
    public void unfollowUser(String email , String followedEmail) throws UserDAOException {
        try {
            if (!userExist(email)) {
                throw new UserDAOException("User does not exist");
            }else if (!userExist(followedEmail)){
                throw new UserDAOException("Followed user does not exist");
            } else if (!following_sysContains(email , followedEmail)) {
                throw new UserDAOException("Not following user");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM following_sys WHERE follower = ? AND followed = ?"
            );
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, followedEmail);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error unfollowing user");
        }
    }
    public User[] getFollowers(String email) throws UserDAOException {
        try {
            if (!userExist(email)) {
                throw new UserDAOException("User does not exist");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT follower FROM following_sys WHERE followed = ?"
            );
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int size = resultSet.getRow();
            resultSet.beforeFirst();
            User[] followers = new User[size];
            int i = 0;
            while (resultSet.next()) {
                followers[i] = new User(resultSet.getInt("id"), resultSet.getString("password"), resultSet.getString("email"));
                i++;
            }
            return followers;
        } catch (SQLException e) {
            throw new UserDAOException("Error getting followers");
        }
    }
    public User[] getFollowings(String email) throws UserDAOException {
        try {
            if (!userExist(email)) {
                throw new UserDAOException("User does not exist");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT followed FROM following_sys WHERE follower = ?"
            );
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int size = resultSet.getRow();
            resultSet.beforeFirst();
            User[] following = new User[size];
            int i = 0;
            while (resultSet.next()) {
                following[i] = new User(resultSet.getInt("id"), resultSet.getString("password"), resultSet.getString("email"));
                i++;
            }
            return following;
        } catch (SQLException e) {
            throw new UserDAOException("Error getting following");
        }
    }
    public void createUserConnection(String user1 ,String user2) throws UserDAOException {
        try {
            if (!userExist(user1)) {
                throw new UserDAOException("User1 does not exist");
            }else if (!userExist(user2)){
                throw new UserDAOException("User2 does not exist");
            } else if (user1.equals(user2)) {
                throw new UserDAOException("Users are the same");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user_connections(user1 ,user2) values (?,?)"
            );
            preparedStatement.setString(1, user1);
            preparedStatement.setString(2, user2);
            preparedStatement.execute();
            preparedStatement.setString(1, user2);
            preparedStatement.setString(2, user1);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error creating user connection");
        }
    }
    public boolean connection_exist(String user1 , String user2) throws UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM user_connections WHERE user1 = ? AND user2 = ?"
            );
            preparedStatement.setString(1, user1);
            preparedStatement.setString(2, user2);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            return (resultSet1.next());
        } catch (SQLException e) {
            throw new UserDAOException("Error checking if connection exists");
        }
    }
    public void deleteUserConnection(String user1 , String user2) throws UserDAOException {
        try {
            if (!connection_exist(user1 , user2)) {
                throw new UserDAOException("Connection does not exist");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM user_connections WHERE user1 = ? AND user2 = ?"
            );
            preparedStatement.setString(1, user1);
            preparedStatement.setString(2, user2);
            preparedStatement.execute();
        } catch (SQLException e) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM user_connections WHERE user1 = ? AND user2 = ?"
                );
                preparedStatement.setString(1, user2);
                preparedStatement.setString(2, user1);
                preparedStatement.execute();
            }catch (SQLException e1)
            {
                throw new UserDAOException("Error deleting user connection");
            }
        }
    }
    public User[] getConnections(String email) throws UserDAOException {
        try {
            if (!userExist(email)) {
                throw new UserDAOException("User does not exist");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT user2 FROM user_connections WHERE user1 = ?"
            );
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int size = resultSet.getRow();
            resultSet.beforeFirst();
            User[] connections = new User[size];
            int i = 0;
            while (resultSet.next()) {
                connections[i] = new User(resultSet.getInt("id"), resultSet.getString("password"), resultSet.getString("email"));
                i++;
            }
            return connections;
        } catch (SQLException e) {
            throw new UserDAOException("Error getting connections");
        }
    }
    public void deleteAllConnections(String email) throws UserDAOException {
        try {
            if (!userExist(email)) {
                throw new UserDAOException("User does not exist");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM user_connections WHERE user1 = ?"
            );
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM user_connections WHERE user2 = ?"
            );
            preparedStatement.setString(1, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error deleting all connections");
        }
    }
    public void deleteAllFollowersAndFollowings(String email) throws UserDAOException {
        try {
            if (!userExist(email)) {
                throw new UserDAOException("User does not exist");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM following_sys WHERE follower = ?"
            );
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM following_sys WHERE followed = ?"
            );
            preparedStatement.setString(1, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UserDAOException("Error deleting all followers and followings");
        }
    }
}