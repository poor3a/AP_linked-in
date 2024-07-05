package poorsa.org.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class ContactInfoController {
	@FXML
	TextArea address;
	@FXML
	TextField email;
	@FXML
	TextField pnh;
	@FXML
	TextField pnw;
	@FXML
	TextField pnp;
	@FXML
	TextField sm1;
	@FXML
	TextField sm2;
	@FXML
	TextField sm3;
	@FXML
	TextField website;
	@FXML
	Label resultLabel;
	@FXML
	Button confirm;
	@FXML
	Button back;

	@FXML
	public void initialize() {
		address.setOnMouseClicked(event -> Animations.getHeartbeat(address).play());
		email.setOnMouseClicked(event -> Animations.getHeartbeat(email).play());
		pnh.setOnMouseClicked(event -> Animations.getHeartbeat(pnh).play());
		pnw.setOnMouseClicked(event -> Animations.getHeartbeat(pnw).play());
		pnp.setOnMouseClicked(event -> Animations.getHeartbeat(pnp).play());
		sm1.setOnMouseClicked(event -> Animations.getHeartbeat(sm1).play());
		sm2.setOnMouseClicked(event -> Animations.getHeartbeat(sm2).play());
		sm3.setOnMouseClicked(event -> Animations.getHeartbeat(sm3).play());
		website.setOnMouseClicked(event -> Animations.getHeartbeat(website).play());

	}

	public void confirmOnAction() throws IOException {
		String ad = address.getText();
		String em = email.getText();
		String phoneNH = pnh.getText();
		String phoneNW = pnw.getText();
		String phoneNP = pnp.getText();
		String SM1 = sm1.getText();
		String SM2 = sm2.getText();
		String SM3 = sm3.getText();
		String web = website.getText();
		// #write here(post request for contact info)
		try {
			URL url = new URL(frontMethods.URLFirstPart + "contact");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			JSONObject json = new JSONObject();
			json.put("address", ad);
			json.put("email", em);
			json.put("phoneNumber_home", phoneNH);
			json.put("phoneNumber_work", phoneNW);
			json.put("phoneNumber_personal", phoneNP);
			json.put("socialMedia1", SM1);
			json.put("socialMedia2", SM2);
			json.put("socialMedia3", SM3);
			json.put("website", web);
			String token = frontMethods.getToken();
			connection.setRequestProperty("Authorization", token);
			frontMethods.sendRequest(connection, json.toString());
			String response = frontMethods.getResponse(connection);
			if (response.contains("Error")) {
				resultLabel.setText(response.toString());
			} else {
				// continue
			}
		} catch (Exception e) {
			resultLabel.setText("Something went wrong with the server");
		}
		Animations.buttonAnimation(confirm);
		Parent root = FXMLLoader.load(getClass().getResource("CreateSchooling.fxml"));
		Scene scene = new Scene(root, 800, 500);
		Stage stage = (Stage) confirm.getScene().getWindow();
		scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
		Animations.fadeScene(stage, scene);
	}

	public void backOnAction() throws IOException {
		Animations.buttonAnimation(back);
		Parent root = FXMLLoader.load(getClass().getResource("createProfile.fxml"));
		Scene scene = new Scene(root, 800, 500);
		Stage stage = (Stage) confirm.getScene().getWindow();
		scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
		Animations.fadeScene(stage, scene);
	}

}
