import DAO.*;
import exceptions.UserDAOException;
import java.sql.SQLException;
import HTTPHandlers.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import utils.JWTController;

//this is the main class of the server in case to run it

public class Main {
	public static void main(String[] args) throws SQLException, UserDAOException {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

			server.createContext("/user", new UserHandler());
			server.createContext("/schooling", new SchoolingHandler());
			server.createContext("/contact", new ContactHandler());
			server.createContext("/comment", new CommentHandler());
			server.createContext("/like", new LikeHandler());
			server.createContext("/connection", new ConnectionHandler());
			server.createContext("/follow", new FollowHandler());
			server.createContext("/post", new PostHandler());
			server.createContext("/jobStatement", new JobStatementHandler());
			server.createContext("/profile", new ProfileHandler());
			server.createContext("/search", new SearchHandler());
			
			server.start();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
