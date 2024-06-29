package poorsa.org.frontend;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LoginController {


    @FXML
    private Label in2;
    @FXML
    private Label sign;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Button createAccountButton;
    @FXML
    private ImageView keypic;
    @FXML
    private ImageView emailpic;
    @FXML
    public void initialize() {
        password.setOnMouseEntered(event -> {
            keypic.setImage(new Image(getClass().getResource("/whitekey.png").toString()));
        });

        password.setOnMouseExited(event -> {
            keypic.setImage(new Image(getClass().getResource("/icons8-key-48.png").toString()));
        });
        username.setOnMouseEntered(event -> {
            emailpic.setImage(new Image(getClass().getResource("/white email.png").toString()));
        });
        username.setOnMouseExited(event -> {
            emailpic.setImage(new Image(getClass().getResource("/icons8-email-64.png").toString()));
        });
        username.setOnMouseClicked(event -> {
            getHeartbeat(username).play();
        });
        password.setOnMouseClicked(event -> {
            getHeartbeat(password).play();
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), event -> {
                }),
                new KeyFrame(Duration.millis(1600), event -> {
                    in2.setOpacity(1);
                }),
                new KeyFrame(Duration.millis(1625), event -> {
                    in2.setOpacity(.75);
                }),
                new KeyFrame(Duration.millis(1750), event -> {
                    in2.setOpacity(.5);
                    sign.translateXProperty().set(0);
                }),
                new KeyFrame(Duration.millis(1875), event -> {
                    in2.setOpacity(.25);
                    sign.translateXProperty().set(-5);
                }),
                new KeyFrame(Duration.millis(2000), event -> {
                    in2.setOpacity(0);
                    sign.translateXProperty().set(-10);
                }),
                new KeyFrame(Duration.millis(2125), event -> {
                    sign.translateXProperty().set(-20);
                }),
                new KeyFrame(Duration.millis(2250), event -> {
                    sign.translateXProperty().set(-24);
                }),
                new KeyFrame(Duration.millis(3000), event -> {
                })
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        username.setFocusTraversable(false);
        password.setFocusTraversable(false);
    }

    private static ScaleTransition getHeartbeat(Node node) {
        ScaleTransition heartbeat = new ScaleTransition(Duration.millis(75), node);
        heartbeat.setByX(0.15);
        heartbeat.setByY(0.15);
        heartbeat.setAutoReverse(true);
        heartbeat.setCycleCount(2);
        return heartbeat;
    }

    public void loginButtonOnAction() {
        buttonAnimation(loginButton);
        createAccountButton.setDisable(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), event -> {
                    loginButton.setText("Logging in");
                }),
                new KeyFrame(Duration.millis(333), event -> {
                    loginButton.setText("Logging in.");
                }),
                new KeyFrame(Duration.millis(666), event -> {
                    loginButton.setText("Logging in..");
                }),
                new KeyFrame(Duration.millis(1000), event -> {
                    loginButton.setText("Logging in...");
                }),
                new KeyFrame(Duration.millis(1500), event -> {
                    loginButton.setText("Logging in...");
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    public void createAccountButtonOnAction() {
        buttonAnimation(createAccountButton);
        loginButton.setDisable(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), event -> {
                    createAccountButton.setText("Creating Account");
                }),
                new KeyFrame(Duration.millis(333), event -> {
                    createAccountButton.setText("Creating Account.");
                }),
                new KeyFrame(Duration.millis(666), event -> {
                    createAccountButton.setText("Creating Account..");
                }),
                new KeyFrame(Duration.millis(1000), event -> {
                    createAccountButton.setText("Creating Account...");
                }),
                new KeyFrame(Duration.millis(1500), event -> {
                    createAccountButton.setText("Creating Account...");
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void buttonAnimation(Button createAccountButton) {
        createAccountButton.setDisable(true);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), createAccountButton);
        scaleTransition.setByX(0.1);
        scaleTransition.setByY(0.1);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }
}