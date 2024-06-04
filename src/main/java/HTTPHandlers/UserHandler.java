package HTTPHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.UserController;
import exceptions.UserDAOException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class UserHandler implements HttpHandler {
    private UserController userController;

    public UserHandler() throws SQLException, SQLException {
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
                    response = handlePostRequest(pathElements, exchange);
                    break;
                case "DELETE":
                    response = handleDeleteRequest(pathElements);
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

    private String handleGetRequest(String[] pathElements) throws IOException, UserDAOException {
        if (pathElements.length == 3) {
            return userController.getUser(pathElements[2]);
        } else {
            throw new IOException("Path is not valid");
        }
    }

    private String handlePostRequest(String[] pathElements, HttpExchange exchange) throws IOException, UserDAOException {
        if (pathElements.length == 2) {
            JSONObject jsonObject = Methods.getJSON(exchange);
            if (Methods.isValidUserJson(jsonObject)) {
                userController.createUser(
                        jsonObject.getString("username"),
                        jsonObject.getString("password"),
                        jsonObject.getString("email"));
                return "User added successfully";
            } else {
                throw new IOException("Request isn't in the right format");
            }
        } else {
            throw new IOException("Path is not valid");
        }
    }

    private String handleDeleteRequest(String[] pathElements) throws IOException, UserDAOException {
        if (pathElements.length == 4) {
            userController.deleteUser(pathElements[2], pathElements[3]);
            return "User got deleted";
        } else {
            throw new IOException("Path is not valid");
        }
    }
}