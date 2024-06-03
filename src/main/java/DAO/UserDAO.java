package DAO;

import java.sql.*;

public class UserDAO {
    Connection connection;
    Statement statement;

    public UserDAO() throws SQLException {
        this.connection = SQL.getConnection();
        this.statement = connection.createStatement();
    }//in this constructor we connect to the database using the getConnection method from the SQL class

public String createUser(String username, String password, String email)
{
    //user just need username and password and email for being created
    //other things like name and family etc... can be added later
    //id is auto increment, so we don't need to add it
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users(username ,password ,email) values (?,?,?)"
        );
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, email);
        preparedStatement.execute();
    } catch (SQLException e) {
        if (e.getMessage().contains("password length"))
        {
            return "short password";//I used this to check if the password is short in mysql app
        } else {
           return "error";
        }
    }
        return "success";
}

    public boolean userExist_username(String username) throws SQLException
    {
        //this method is used to check if the username is already exist in the database
        //if it exists, it will return true, otherwise it will return false
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM users WHERE username = ?"
        );
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public boolean userExist_email(String email) throws SQLException
    {
        //this is the same as the userExist_username method, but it checks the email instead of the username
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM users WHERE email = ?"
        );
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    public boolean checkUserPassword(String username, String password) throws SQLException
    {
        //this method is used to check if the password is correct or not
        //it will return true if the password is correct, otherwise it will return false
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM users WHERE username = ? AND password = ?"
        );
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    public int getUserId_username(String username) throws SQLException
    {
        //this method is used to get the id of the user
        //it will return the id of the user
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id FROM users WHERE username = ?"
        );
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }
    public String getEmail_username(String username) throws SQLException
    {
        //this method is used to get the email of the user
        //it will return the email of the user
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT email FROM users WHERE username = ?"
        );
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("email");
    }
    public String deleteUser(String username , String password) throws SQLException
    {
        //this method is used to delete the account of the user
        //it will return "success" if the account is deleted successfully
        //it will return "error" if there is an error
        if (!checkUserPassword(username, password))
        {
            return "access denied";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM users WHERE username = ? AND password = ?"
        );
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
        return "success";
    }
    public String changePassword(String username , String oldPassword , String newPassword) throws SQLException
    {
        //this method is used to change the password of the user
        //it will return "success" if the password is changed successfully
        //it will return "error" if there is an error
        if (!checkUserPassword(username, oldPassword))
        {
            return "access denied";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE users SET password = ? WHERE username = ?"
        );
        preparedStatement.setString(1, newPassword);
        preparedStatement.setString(2, username);
        preparedStatement.execute();
        return "success";
    }
    public String changeEmail(String username , String password , String newEmail) throws SQLException
    {
        //this method is used to change the email of the user
        //it will return "success" if the email is changed successfully
        //it will return "error" if there is an error
        if (!checkUserPassword(username, password))
        {
            return "access denied";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE users SET email = ? WHERE username = ?"
        );
        preparedStatement.setString(1, newEmail);
        preparedStatement.setString(2, username);
        preparedStatement.execute();
        return "success";
    }
}
