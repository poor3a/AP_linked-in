package DAO;
import java.sql.*;
public abstract class SQL
{
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/project" ,
                "root",
                "@Po18061385"
        );
    }
}
