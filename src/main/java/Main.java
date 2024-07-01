import DAO.*;
import exceptions.UserDAOException;
import java.sql.SQLException;
import HTTPHandlers.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
//this is the main class of the server in case to run it

public class Main
{
    public static void main(String[] args) throws SQLException, UserDAOException
    {
    	try {
			HttpServer server = HttpServer.create(new InetSocketAddress(3306),0);

            server.createContext("/user", new UserHandler());

            server.createContext("/profile", new ProfileHandler());
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
}
