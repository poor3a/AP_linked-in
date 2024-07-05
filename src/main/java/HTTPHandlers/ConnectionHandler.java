package HTTPHandlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.UserController;
import exceptions.ProfileDAOException;
import exceptions.UserDAOException;
import utils.JWTController;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class ConnectionHandler implements HttpHandler {
	private UserController userController;

	public ConnectionHandler() throws SQLException {
		this.userController = new UserController();
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		String requestMethod = exchange.getRequestMethod();
		String path = exchange.getRequestURI().getPath();
		String[] pathElements = path.split("/");
		String response;

		try {
			switch (requestMethod) {
			case "GET":
				response = handleGetRequest(pathElements);
				break;
			case "POST":
				response = handlePostRequest(pathElements,exchange);
				break;
			case "DELETE":
				response = handleDeleteRequest(pathElements,exchange);
				break;
			default:
				response = "This method is not supported";
				break;
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Error"))
				response = "Something went wrong with the server";
			else
				response = e.getMessage();
		}

		System.out.println(response);
		exchange.sendResponseHeaders(200, response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
		exchange.close();
		
	}
	
	public String handleDeleteRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 3) {
				userController.unfollowUser(JWTController.verifyToken(exchange), pathElements[2]);
				return "User unfollowed";
			}
			else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String handlePostRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 3) {
				userController.followUser(JWTController.verifyToken(exchange), pathElements[2]);
				return "User followed";
			}
			else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String handleGetRequest(String[] pathElements) {
		try {
			if (pathElements.length == 4) {
				if (pathElements[2] == "followers") {
					return userController.getFollowers(pathElements[3]);
				}
				else if (pathElements[2] == "followings") {
					return userController.getFollowings(pathElements[3]);
				}
				else {
					throw new IOException("Path is not valid");
				}
			}
			else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}