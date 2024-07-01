package poorsa.org.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContactInfoController
{
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
    Button confirm;
    @FXML
    Button back;
    @FXML
    public void initialize()
    {
        address.setOnMouseClicked(event -> Animations.getHeartbeat(address).play());
        email.setOnMouseClicked(event -> Animations.getHeartbeat(email).play());
        pnh.setOnMouseClicked(event -> Animations.getHeartbeat(pnh).play());
        pnw.setOnMouseClicked(event -> Animations.getHeartbeat(pnw).play());
        pnp.setOnMouseClicked(event -> Animations.getHeartbeat(pnp).play());
        sm1.setOnMouseClicked(event -> Animations.getHeartbeat(sm1).play());
        sm2.setOnMouseClicked(event -> Animations.getHeartbeat(sm2).play());
        sm3.setOnMouseClicked(event -> Animations.getHeartbeat(sm3).play());
        website.setOnMouseClicked(event -> Animations.getHeartbeat(website).play());
//        email.setText();

    }
    public void confirmOnAction()
    {
        Animations.buttonAnimation(confirm);

    }
    public void backOnAction()
    {
        Animations.buttonAnimation(back);
    }

}
