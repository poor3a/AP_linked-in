package HTTPHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.ProfileController;
import exceptions.ProfileDAOException;
import exceptions.UserDAOException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import utils.JWTController;

public class ProfileHandler implements HttpHandler {
	private ProfileController profileController;

	public ProfileHandler() throws SQLException {
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
			if (Methods.isValidProfileJson(jsonObject)) {
				profileController.createprofile(JWTController.verifyToken(exchange), jsonObject.getString("firstName"),
						jsonObject.getString("lastName"), jsonObject.getString("additionalName"),
						jsonObject.getString("birthDate"), jsonObject.getString("profilePicture"),
						jsonObject.getString("bg_picture"), jsonObject.getString("title"),
						jsonObject.getString("place"), jsonObject.getString("career"),
						jsonObject.getString("jobAiming"));
				return "Profile added successfully";
			} else {
				throw new IOException("Request isn't in the right format");
			}
		} else {
			throw new IOException("Path is not valid");
		}
	}

	private String handleGetRequest(String[] pathElements) throws IOException, ProfileDAOException, UserDAOException {
		if (pathElements.length == 3) {
			return profileController.getprofile(pathElements[2]);
		} else {
			throw new IOException("Path is not valid");
		}
	}

	private String handleDeleteRequest(String[] pathElements, HttpExchange exchange)
			throws IOException, ProfileDAOException, UserDAOException {
		if (pathElements.length == 2) {
			profileController.deleteProfile(JWTController.verifyToken(exchange));
			return "Profile deleted successfully";
		} else {
			throw new IOException("Path is not valid");
		}
	}
}