package poorsa.org.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class CreateSchoolingController {
    @FXML
    TextField schoolName;
    @FXML
    TextField fieldOfStudy;
    @FXML
    TextField grade;
    @FXML
    TextField degree;
    @FXML
    DatePicker start;
    @FXML
    DatePicker end;
    @FXML
    TextArea activities;
    @FXML
    TextArea description;
    @FXML
    Label resultLabel;
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
    	String SN = frontMethods.getTextField(schoolName);
    	String FOS = frontMethods.getTextField(fieldOfStudy);
    	String GR =frontMethods.getTextField( grade);
    	String DE =frontMethods.getTextField( degree);
    	String S = frontMethods.getDate(start);
    	String E = frontMethods.getDate(end);
    	String AC = frontMethods.getTextArea(activities);
    	String DES = frontMethods.getTextArea(description);
    	
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
                Animations.buttonAnimation(confirm);
                Parent root = FXMLLoader.load(getClass().getResource("CreateJobStatement.fxml"));
                Scene scene = new Scene(root , 800 ,500);
                Stage stage = (Stage) confirm.getScene().getWindow();
                scene.getStylesheets().add(getClass().getResource("createProfile.css").toExternalForm());
                Animations.fadeScene(stage, scene);
			}
		} catch (Exception e) {
			resultLabel.setText("Something went wrong with the server");
		}

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