package poorsa.org.frontend;


import poorsa.org.frontend.models.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.regex.Pattern;

public class frontMethods {
	public static String URLFirstPart = "http://localhost:8080/";

	public static boolean patternMatches(String emailAddress) {
		String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		return Pattern.compile(regexPattern).matcher(emailAddress).matches();
	}

	public static String getResponse(HttpURLConnection connection) throws IOException {
		InputStream inputStream = connection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String inputline;
		StringBuilder response = new StringBuilder();
		while ((inputline = in.readLine()) != null) {
			response.append(inputline);
		}
		in.close();
		return response.toString();
	}

	public static void sendResponse(HttpURLConnection connection, String response) throws IOException {
		connection.getOutputStream().write(response.getBytes());
	}

	public static void saveUser(String email, String password, String token) {
		HelloApplication.user = new User(email, password, token);
	}

	public static void saveToken(String token) {
		try {
            File file = new File("user.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(token);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured while saving the token");
            e.printStackTrace();
        }
	}
}
