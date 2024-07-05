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

public class JobStatementHandler implements HttpHandler {

	private ProfileController profileController;

	public JobStatementHandler() throws SQLException {
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
				response = handlePutRequest(pathElements, exchange);
				break;
			case "Delete":
				response = handleDeleteRequest(pathElements, exchange);
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

	public String handlePostRequest(String[] pathElements, HttpExchange exchange)
			throws IOException, ProfileDAOException, UserDAOException {
		try {
			if (pathElements.length == 2) {
				JSONObject jsonObject = Methods.getJSON(exchange);
				if (Methods.isValidJobStatementJson(jsonObject)) {
					profileController.createJob_Statement(JWTController.verifyToken(exchange),
							jsonObject.getString("title"), jsonObject.getString("workingState"),
							jsonObject.getString("companyName"), jsonObject.getString("companyAddress"),
							jsonObject.getString("workingType"), jsonObject.getBoolean("isWorking"),
							jsonObject.getString("start"), jsonObject.getString("end"),
							jsonObject.getString("description"));
					return "Job Statement added successfully";
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

	private String handlePutRequest(String pathElements[], HttpExchange exchange)
			throws IOException, ProfileDAOException, UserDAOException {
		try {
			JSONObject jsonObject = Methods.getJSON(exchange);
			if (pathElements.length == 2) {
				if (Methods.isValidJobStatementJson(jsonObject)) {
					profileController.updateJob_Statement(JWTController.verifyToken(exchange),
							jsonObject.getString("title"), jsonObject.getString("workingState"),
							jsonObject.getString("companyName"), jsonObject.getString("companyAddress"),
							jsonObject.getString("workingType"), jsonObject.getBoolean("isWorking"),
							jsonObject.getString("start"), jsonObject.getString("end"),
							jsonObject.getString("description"));
					return "Job statement updated successfully";
				} else {
					throw new IOException("Request isn't in the right format");
				}
			} else if (pathElements.length == 3) {
				if (pathElements[2] == "change") {
					if (jsonObject.has("title")) {
						profileController.changeCurrent_job(jsonObject.getString("title"),
								JWTController.verifyToken(exchange));
						return "Current job changed successfully";
					} else {
						throw new IOException("Request isn't in the right format");
					}
				} else {
					throw new IOException("Path is not valid");
				}
			} else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	private String handleGetRequest(String[] pathElements) throws IOException, ProfileDAOException, UserDAOException {
		try {
			if (pathElements.length == 3) {
				return profileController.getJob_Statement(pathElements[2]);
			} else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	private String handleDeleteRequest(String[] pathElements, HttpExchange exchange)
			throws IOException, ProfileDAOException, UserDAOException {
		try {
			if (pathElements.length == 3) {
				profileController.deleteJob_Statement(pathElements[2], JWTController.verifyToken(exchange));
				return "Job statement deleted successfully";
			} else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}