package DAO;
import java.sql.*;
public abstract class SQL
{
    public static Connection getConnection() throws SQLException {
        //this is a method that is used to get the connection to the database.
        //it returns a connection object that is used to interact with the database in DAO classes.
        //if the connection fails, it throws an exception, but surely it will not fail.
        //because the connection is made to the local database and about password and username, they are correct.
        //so the connection will be successful.
        return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/project" ,
                "root",
                "@Po18061385"
        );
    }
}
