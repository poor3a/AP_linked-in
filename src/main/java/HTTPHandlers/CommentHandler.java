package HTTPHandlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.UserController;
import exceptions.ProfileDAOException;
import exceptions.UserDAOException;
import utils.JWTController;
import org.json.JSONObject;
import controllers.PostController;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class CommentHandler implements HttpHandler {

	private PostController postController;

	public CommentHandler() throws SQLException {
		this.postController = new PostController();
	}

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
			case "PUT":
				response = handlePutRequest(pathElements, exchange);
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

	public String handlePostRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 3) {
				JSONObject jsonObject = Methods.getJSON(exchange);
				if (jsonObject.has("text")) {
					postController.commentPost(JWTController.verifyToken(exchange), Integer.parseInt(pathElements[2]),
							jsonObject.getString("text"));
					return "Comment added successfully";
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
				return postController.getComment(Integer.parseInt(pathElements[2]));
			} else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String handleDeleteRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 3) {
				postController.deleteComment(Integer.parseInt(pathElements[2]), JWTController.verifyToken(exchange));
				return "Comment deleted";
			} else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String handlePutRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 3) {
				JSONObject jsonObject = Methods.getJSON(exchange);
				if (jsonObject.has("text")) {
					postController.editComment(JWTController.verifyToken(exchange), Integer.parseInt(pathElements[2]),
							jsonObject.getString("text"));
					return "Comment edited successfully";
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
}
