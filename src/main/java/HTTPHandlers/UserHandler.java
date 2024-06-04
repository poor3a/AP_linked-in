package HTTPHandlers;

import java.io.*;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.sql.SQLException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.UserController;
import exceptions.UserDAOException;
import org.eclipse.jetty.http.HttpField;
import org.json.JSONObject;

public class UserHandler implements HttpHandler{
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		try {
			String req = exchange.getRequestMethod();
		    String path = exchange.getRequestURI().getPath();
		    String[] pathElements = path.split("/");
		    String feedback = "not initialized yet"; //we will initialize it later
		    UserController uC = new UserController();
		    try {
		    	if (req.equals("GET")) {
		    		if (pathElements.length == 3) {
		    			try {
		    				feedback = uC.getUser(pathElements[2]);
		    				exchange.sendResponseHeaders(200, feedback.length());
		    			}
		    			catch (UserDAOException e) {
		    				feedback = "User not found";
		                    exchange.sendResponseHeaders(404, feedback.length());
		    			}
		    		}
		    		else {
		                feedback = "Path is not valid";
		                exchange.sendResponseHeaders(400, feedback.length());
		            }
		    	}
		    	else if (req.equals("POST")) {
		    		if (pathElements.length == 2) {
		                JSONObject jsonObject = Methods.getJSON(exchange);
		                if (Methods.isValidUserJson(jsonObject)) {
		                    uC.createUser(
		                            jsonObject.getString("username"),
		                            jsonObject.getString("password"),
		                            jsonObject.getString("email"));
		                    feedback = "User added successfully";
		                    exchange.sendResponseHeaders(200, feedback.length());
		                } else {
		                    feedback = "Request isn't in the right format";
		                    exchange.sendResponseHeaders(400, feedback.length());
		                }
		            } else {
		                feedback = "Path is not valid";
		                exchange.sendResponseHeaders(400, feedback.length());
		            }
		    	}
		    	else if (req.equals("DELETE")) {
		    		if (pathElements.length == 4) {
		    			uC.deleteUser(pathElements[2],pathElements[3]);
		                feedback = "User got deleted";
		                exchange.sendResponseHeaders(200, feedback.length());
		    		}
		    		else {
		    			feedback = "Path is not valid";
		    			exchange.sendResponseHeaders(400, feedback.length());
		    		}
		    	}
		    	else {
		    		feedback = "This method is not supported";
		    		exchange.sendResponseHeaders(405, feedback.length());
		    	}
		    	
		    }
		    catch (Exception e){
		    	feedback = "Something went wrong with the seerver";
	            exchange.sendResponseHeaders(500, feedback.length());
		    }
		    finally {
		    	OutputStream os = exchange.getResponseBody();
		    	os.write(feedback.getBytes());
		    	os.close();
		    	exchange.close();
		    }
		}
		catch (SQLException e) {
			
		}
	}
}

