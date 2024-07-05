package HTTPHandlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.UserController;
import controllers.SearchController;
import exceptions.ProfileDAOException;
import exceptions.UserDAOException;
import utils.JWTController;
import org.json.JSONObject;
import controllers.PostController;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import controllers.SearchController;

public class PostHandler implements HttpHandler {

	private PostController postController;
	private SearchController searchController;

	public PostHandler() throws SQLException {
		this.postController = new PostController();
		this.searchController = new SearchController();
	}

	public void handle(HttpExchange exchange) throws IOException {

		String requestMethod = exchange.getRequestMethod();
		String path = exchange.getRequestURI().getPath();
		String[] pathElements = path.split("/");
		String response;

		try {
			switch (requestMethod) {
			case "GET":
				response = handleGetRequest(pathElements, exchange);
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
			if (pathElements.length == 2) {
				JSONObject jsonObject = Methods.getJSON(exchange);
				if (Methods.isValidPostJson(jsonObject)) {
					postController.createPost(JWTController.verifyToken(exchange), jsonObject.getString("text"),
							jsonObject.getString("image_path"));
					ArrayList<String> hashtags = Methods.extractHashtags(jsonObject.getString("text"));
					for (String i : hashtags) {
						searchController.addHashtag(i, 0); //this need id to put insteadof 0
					}
					return "Post added successfully";
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

	public String handleGetRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 3) {
				if (pathElements[2] == "feed") {
					return postController.getFeedPosts(JWTController.verifyToken(exchange));
				}
				return postController.getPost(Integer.parseInt(pathElements[2]));
			} else if (pathElements.length == 4) {
				if (pathElements[2] == "all") {
					return postController.getUserPosts(pathElements[3]);
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

	public String handleDeleteRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 3) {
				postController.deletePost(Integer.parseInt(pathElements[2]), JWTController.verifyToken(exchange));
				return "Post deleted";
			} else {
				throw new IOException("Path is not valid");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String handlePutRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 2) {
				JSONObject jsonObject = Methods.getJSON(exchange);
				if (Methods.isValidPostJson(jsonObject)) {
					postController.editPost(JWTController.verifyToken(exchange), jsonObject.getString("text"),
							jsonObject.getString("image_path"));
					return "Post edited successfully";
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
