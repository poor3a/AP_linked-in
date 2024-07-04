package poorsa.org.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class CreateSchoolingController {
    @FXML
    java.awt.TextField schoolName;
    @FXML
    java.awt.TextField fieldOfStudy;
    @FXML
    java.awt.TextField grade;
    @FXML
    java.awt.TextField degree;
    @FXML
    DatePicker start;
    @FXML
    DatePicker end;
    @FXML
    java.awt.TextArea activities;
    @FXML
    TextArea description;
    @FXML
    Button confirm;
    @FXML
    Button back;

    @FXML
    public void initialize()
    {
        schoolName.setOnMouseClicked(event -> Animations.getHeartbeat(schoolName).play());
        fieldOfStudy.setOnMouseClicked(event -> Animations.getHeartbeat(fieldOfStudy).play());
        grade.setOnMouseClicked(event -> Animations.getHeartbeat(grade).play());
        degree.setOnMouseClicked(event -> Animations.getHeartbeat(degree).play());
        start.setOnMouseClicked(event -> Animations.getHeartbeat(start).play());
        end.setOnMouseClicked(event -> Animations.getHeartbeat(end).play());
        activities.setOnMouseClicked(event -> Animations.getHeartbeat(activities).play());
        description.setOnMouseClicked(event -> Animations.getHeartbeat(description).play());
    }
    public void confirmOnAction() throws IOException {
        //#write here(post request for schooling)
    	String SN = schoolName.getText();
    	String FOS = fieldOfStudy.getText();
    	String GR = grade.getText();
    	String DE = degree.getText();
    	String S = start.getText();
    	String E = end.getText();
    	String AC = activities.getText();
    	String DES = description.getText();
    	
    	try {
			URL url = new URL(frontMethods.URLFirstPart + "schooling");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			JSONObject json = new JSONObject();
			json.put("schoolName", SN);
			json.put("fieldOfStudy", FOS);
			json.put("grade", GR);
			json.put("degree", DE);
			json.put("start", S);
			json.put("end", E);
			json.put("activities", AC);
			json.put("description", DES);
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
        Parent root = FXMLLoader.load(getClass().getResource("CreateJobStatement.fxml"));
        Scene scene = new Scene(root , 800 ,500);
        Stage stage = (Stage) confirm.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
        Animations.fadeScene(stage, scene);
    }
    public void backOnAction() throws IOException {
        Animations.buttonAnimation(back);
        Parent root = FXMLLoader.load(getClass().getResource("contactInfo.fxml"));
        Scene scene = new Scene(root, 800, 500);
        Stage stage = (Stage) confirm.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
        Animations.fadeScene(stage, scene);
    }
}