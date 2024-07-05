package HTTPHandlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownServiceException;
import java.sql.SQLException;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpHandler;
import controllers.ProfileController;
import exceptions.ProfileDAOException;
import exceptions.UserDAOException;
import utils.JWTController;

import com.sun.net.httpserver.HttpExchange;

public class ContactHandler implements HttpHandler {
	private ProfileController profileController;

	public ContactHandler() throws SQLException {
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
			case "PUT":
				response = handlePostRequest(pathElements, exchange);
				break;
			default:
				response = "This method is not supported";
				break;
			}
		} catch (Exception e) {
			response = "Something went wrong with the server";
		}
		exchange.sendResponseHeaders(200, response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
		exchange.close();
	}

	public String handlePostRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 2) {
				JSONObject jsonObject = Methods.getJSON(exchange);
				if (Methods.isValidContactInfoJson(jsonObject)) {
					profileController.addContact_info(JWTController.verifyToken(exchange),
							jsonObject.getString("address"), jsonObject.getString("email"),
							jsonObject.getString("phoneNumber_home"), jsonObject.getString("phoneNumber_work"),
							jsonObject.getString("phoneNumber_personal"), jsonObject.getString("socialMedia1"),
							jsonObject.getString("socialMedia2"), jsonObject.getString("socialMedia3"),
							jsonObject.getString("website"));
					return "Contact info added successfully";
				} else {
					throw new IOException("Request isn't in the right format");
				}
			} else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage() + "Error";
		}
	}

	public String handlePutRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 2) {
				JSONObject jsonObject = Methods.getJSON(exchange);
				if (Methods.isValidContactInfoJson(jsonObject)) {
					profileController.updateContact_info(JWTController.verifyToken(exchange),
							jsonObject.getString("address"), jsonObject.getString("email"),
							jsonObject.getString("phoneNumber_home"), jsonObject.getString("phoneNumber_work"),
							jsonObject.getString("phoneNumber_personal"), jsonObject.getString("socialMedia1"),
							jsonObject.getString("socialMedia2"), jsonObject.getString("socialMedia3"),
							jsonObject.getString("website"));
					return "Contact info updated successfully";
				} else {
					throw new IOException("Request isn't in the right format");
				}
			} else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String handleGetRequest(String[] pathElements) {
		try {
			if (pathElements.length == 3) {
				return profileController.getContact_info(pathElements[2]);
			} else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
