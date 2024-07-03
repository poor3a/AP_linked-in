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

public class UserHandler implements HttpHandler {
	private UserController userController;

	public UserHandler() throws SQLException {
		System.out.println("kir");
		this.userController = new UserController();
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
<<<<<<< HEAD

		String requestMethod = exchange.getRequestMethod();
		String path = exchange.getRequestURI().getPath();
		String[] pathElements = path.split("/");
		String response;
=======
		System.out.println("mooooo");
			String requestMethod = exchange.getRequestMethod();
			String path = exchange.getRequestURI().getPath();
			String[] pathElements = path.split("/");
			String response;
>>>>>>> 468d7529401b3f467c745cd73dcc40dae9a2771f

		try {
			switch (requestMethod) {
			case "GET":
				response = handleGetRequest(pathElements, exchange);
				break;
			case "POST":
				response = handlePostRequest(pathElements, exchange);
				break;
			case "DELETE":
				response = handleDeleteRequest(pathElements);
				break;
			case "PUT":
				response = handlePutRequest(pathElements, exchange);
				break;
			default:
				response = "This method is not supported";
				exchange.sendResponseHeaders(405, response.length());
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

	private String handleGetRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 4) {
				String email = pathElements[2];
				String password = pathElements[3];
				userController.checkLogin(email, password);
				String token = JWTController.createToken(email);
				Headers responseHeaders = exchange.getResponseHeaders();
				responseHeaders.add("Authorization", token);
				System.out.println(token);
				return userController.getUser(email);
			} else {
				throw new IOException("Path is not valid");
			}
<<<<<<< HEAD
		} catch (Exception e) {
			return e.getMessage();
=======
		}catch (Exception e){
			return e.getMessage() + "Error";
>>>>>>> 468d7529401b3f467c745cd73dcc40dae9a2771f
		}
	}

	private String handlePostRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 2) {
				JSONObject jsonObject = Methods.getJSON(exchange);
				if (Methods.isValidUserJson(jsonObject)) {
					userController.createUser(jsonObject.getString("email"), jsonObject.getString("password"));
					return "User added successfully";
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

	private String handleDeleteRequest(String[] pathElements) {
		try {
			if (pathElements.length == 4) {
				userController.deleteUser(pathElements[2], pathElements[3]);
				return "User got deleted";
			} else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

<<<<<<< HEAD
	private String handlePutRequest(String[] pathElements, HttpExchange exchange)  {
		try {
			if (pathElements.length == 3) {
				JSONObject jsonObject = Methods.getJSON(exchange);
				if (pathElements[2].equals("password")) {
					if (jsonObject.has("password") && jsonObject.has("newPassword")) {
						userController.updatePassword(JWTController.verifyToken(exchange),
								jsonObject.getString("password"), jsonObject.getString("newPassword"));
						return "Password updated";
					} else {
						throw new IOException("Request isn't in the right format");
					}
				} else if (pathElements[2].equals("email")) {
					if (jsonObject.has("password") && jsonObject.has("newEmail")) {
						userController.updateEmail(JWTController.verifyToken(exchange),
								jsonObject.getString("password"), jsonObject.getString("newEmail"));
						return "Email updated";
					} else {
						throw new IOException("Request isn't in the right format");
					}
				} else {
					throw new IOException("Path is not valid");
=======
	private String handlePutRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 3) {
				JSONObject jsonObject = Methods.getJSON(exchange);
				if (pathElements[2].equals("password") & jsonObject.has("password") & jsonObject.has("newPassword")) {
					userController.updatePassword(JWTController.verifyToken(exchange), jsonObject.getString("password"), jsonObject.getString("newPassword"));
					return "Password updated";

				} else if (pathElements[2].equals("email") && jsonObject.has("password") && jsonObject.has("newEmail")) {
					userController.updateEmail(JWTController.verifyToken(exchange), jsonObject.getString("password"), jsonObject.getString("newEmail"));
					return "Email updated";

				} else {
					throw new IOException("Path is not valid or request isn't in the right format");
>>>>>>> 468d7529401b3f467c745cd73dcc40dae9a2771f
				}
			} else {
				throw new IOException("Path is not valid");
			}
<<<<<<< HEAD
		} catch (Exception e) {
=======
		}catch (Exception e)
		{
>>>>>>> 468d7529401b3f467c745cd73dcc40dae9a2771f
			return e.getMessage();
		}
	}
}