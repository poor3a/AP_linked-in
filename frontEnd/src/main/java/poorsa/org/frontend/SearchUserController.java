package poorsa.org.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SearchUserController
{
    @FXML
    ImageView icon;
    @FXML
    TextField searchBar;
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
    }
}
