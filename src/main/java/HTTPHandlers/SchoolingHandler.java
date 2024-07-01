package HTTPHandlers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpHandler;
import controllers.ProfileController;
import exceptions.ProfileDAOException;
import exceptions.UserDAOException;
import utils.JWTController;

import com.sun.net.httpserver.HttpExchange;

public class SchoolingHandler implements HttpHandler {
	private ProfileController profileController;

	public SchoolingHandler() throws SQLException {
		this.profileController = new ProfileController();
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
				response = handlePostRequest(pathElements, exchange);
				break;
			case "DELETE":
				response = handleDeleteRequest(pathElements, exchange);
				break;
			default:
				response = "This method is not supported";
				exchange.sendResponseHeaders(405, response.length());
				break;
			}
		} catch (Exception e) {
			response = "Something went wrong with the server";
			exchange.sendResponseHeaders(500, response.length());
		}

		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
		exchange.close();
	}

	private String handlePostRequest(String[] pathElements, HttpExchange exchange)
			throws IOException, ProfileDAOException, UserDAOException {
		if (pathElements.length == 2) {
			JSONObject jsonObject = Methods.getJSON(exchange);
			if (Methods.isValidSchoolingJson(jsonObject)) {
				profileController.createSchooling(JWTController.verifyToken(exchange),
						jsonObject.getString("schoolName"), jsonObject.getString("fieldOfStudy"),
						jsonObject.getString("degree"), jsonObject.getString("start"),
						jsonObject.getString("end"), jsonObject.getDouble("grade"),
						jsonObject.getString("description"), jsonObject.getString("activities"));
				return "Schooling added successfully";
			} else {
				throw new IOException("Request isn't in the right format");
			}
		} else {
			throw new IOException("Path is not valid");
		}
	}
	
	private String handleGetRequest(String[] pathElements) throws IOException, ProfileDAOException, UserDAOException {
		if (pathElements.length == 3) {
			return profileController.getSchooling(pathElements[2]);
		} else {
			throw new IOException("Path is not valid");
		}
	}
	private String handleDeleteRequest(String[] pathElements, HttpExchange exchange)
			throws IOException, ProfileDAOException, UserDAOException {
		if (pathElements.length == 3) {
			profileController.deleteSchooling(pathElements[2],JWTController.verifyToken(exchange));
			return "Schooling deleted successfully";
		} else {
			throw new IOException("Path is not valid");
		}
	}
	
}