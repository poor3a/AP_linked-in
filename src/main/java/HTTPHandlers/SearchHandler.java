package HTTPHandlers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import org.json.JSONObject;
import com.sun.net.httpserver.HttpHandler;
import controllers.SearchController;
import exceptions.ProfileDAOException;
import exceptions.UserDAOException;
import utils.JWTController;

import com.sun.net.httpserver.HttpExchange;

public class SearchHandler implements HttpHandler {
	private SearchController searchController;

	public SchoolingHandler() throws SQLException {
		this.searchController = new SearchController();
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
	
	public String handleGetRequest(String[] pathElements) {
		try {
			if (pathElements.length == 4) {
				if (pathElements[2] == "user") {
					return searchController.searchUser(pathElements[3]);
				}
				else if (pathElements[2] == "hashtag") {
					return searchController.searchHashtag(pathElements[3]);
				}
				else {
					throw new IOException("Error: path is not valid"); 
				}
			}
			else {
				throw new IOException("Error: path is not valid");
			}
		}catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String handlePostRequest(String[] pathElements, HttpExchange exchange) {
		try {
			if (pathElements.length == 4) {
				searchController.addHashtag(pathElements[2], Integer.parseInt(pathElements[3]));
				return "Hashtag added successfully";
			}
			else {
				throw new IOException("Error: path is not valid");
			}
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}