package DAO;
import java.sql.*;
public class UserDAO
{
    Connection connection;
    Statement statement;
    public UserDAO() throws SQLException
    {
        this.connection = SQL.getConnection();
        this.statement = connection.createStatement();
    }
    public void createUser(String username ,String password ,String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users(username ,password ,email) values (?,?,?)"
        );
        preparedStatement.setString(1 ,username);
        preparedStatement.setString(2 ,password);
        preparedStatement.setString(3 ,email);
        preparedStatement.execute();
        
    }
}
