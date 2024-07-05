package poorsa.org.frontend;


import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

	public static void sendRequest(HttpURLConnection connection, String response) throws IOException {
		connection.getOutputStream().write(response.getBytes());
	}

	public static void saveUser(String email, String password, String token) {
		HelloApplication.user = new User(email, password, token);
	}
	public static String getUserEmail()
	{
		return HelloApplication.user.getEmail();
	}

	public static void saveToken(String token) {
		try {
            File file = new File("src/main/java/poorsa/org/frontend/user.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(token);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured while saving the token");
            e.printStackTrace();
        }
	}
	public static String getToken()  {
		try{
			File file = new File("src/main/java/poorsa/org/frontend/user.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String token = br.readLine();
			br.close();
			return token;
		}
		catch (IOException e) {
			System.out.println("An error occured while getting the token");
			e.printStackTrace();
			return null;
		}
	}
	public static String getTextField(TextField textField)
	{
		if(textField.getText().isEmpty())
			return "null";
		else
			return textField.getText();
	}
	public static String getDate(DatePicker datePicker)
	{
		if (datePicker.getValue() == null)
			return "null";
		else
			return datePicker.getValue().toString().replaceAll("/" ,"-");
	}
	public static String getTextArea(TextArea textArea)
	{
		if(textArea.getText().isEmpty())
			return "null";
		else
			return textArea.getText();
	}
}
