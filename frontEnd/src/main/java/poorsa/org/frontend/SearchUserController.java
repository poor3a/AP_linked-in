package poorsa.org.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import models.User;
public class SearchUserController
{
    @FXML
    ImageView icon;
    @FXML
    java.awt.TextField searchBar;
    @FXML
    Button search;
    @FXML
    Button back;

    @FXML
    public void initialize() {
        searchBar.setOnMouseClicked(event -> Animations.getHeartbeat(searchBar).play());
        search.setOnMouseClicked(event -> Animations.getHeartbeat(search).play());
        back.setOnMouseClicked(event -> Animations.getHeartbeat(back).play());
    }

    public void setSearchOnAction() {
        // #write here(search for user)
        String searchInput = frontMethods.getTextField(searchBar);
        try {
        	URL url = new URL(frontMethods.URLFirstPart + "search/user/" + searchInput);
        	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            String response = frontMethods.getResponse(connection);
            if (response.contains("Error")) {
                //continue here
            } else {
                //and here
            	Gson gson = new Gson();
            	Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            	//this arraylist down here give you the list of users that have been found
            	ArrayList<User> usersFound = gson.fromJson(response, listType);
            }
        } catch (Exception e) {
			// TODO: handle exception
		}
    }
}
