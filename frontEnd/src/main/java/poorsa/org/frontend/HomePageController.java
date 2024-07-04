package poorsa.org.frontend;

import java.net.HttpURLConnection;
import java.net.URL;

public class HomePageController {

	try {
		URL url = new URL(frontMethods.URLFirstPart + "post" + "/" + "feed");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Authorization", token);
		String response = frontMethods.getResponse(connection);
		if (response.contains("Error")) {
			resultLabel.setText(response.toString());
		} else {
			// continue
		}
	} catch (Exception e) {
		resultLabel.setText("Something went wrong with the server");
	}

    //write here(first write a get request to get the postFeed ,and then I will set the data in the fields)
}
