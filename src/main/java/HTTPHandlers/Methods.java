package HTTPHandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public class Methods {
	public static JSONObject getJSON(HttpExchange exchange) throws IOException{
		try (BufferedReader reqReader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));) {
			StringBuilder reqBody = new StringBuilder();
			String bodyLine;
			while ((bodyLine = reqReader.readLine()) != null) {
				reqBody.append(bodyLine);
			}
			return new JSONObject(reqBody.toString());
		}
	}
	   public static boolean isValidUserJson(JSONObject jsonObject) {
	        return jsonObject.has("firstname") && jsonObject.has("lastname") &&
	                jsonObject.has("email") && jsonObject.has("password") &&
	                jsonObject.has("country") && jsonObject.has("city") &&
	                jsonObject.has("additionalname");
	    }
	   public static boolean isValidProfileJson(JSONObject jsonObject) {
		   return jsonObject.has("id") && jsonObject.has("firstName") && jsonObject.has("lastName") && 
				   jsonObject.has("additionalName") && jsonObject.has("birthDate") && jsonObject.has("profilePicture") &&
				   jsonObject.has("bg_picture") && jsonObject.has("title") && jsonObject.has("place") &&
				   jsonObject.has("career") && jsonObject.has("jobAiming");  
	   }
}
