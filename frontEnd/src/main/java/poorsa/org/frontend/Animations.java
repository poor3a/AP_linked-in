package poorsa.org.frontend;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class Animations
{

    public static void buttonAnimation(Button createAccountButton) {
        createAccountButton.setDisable(true);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), createAccountButton);
        scaleTransition.setByX(0.1);
        scaleTransition.setByY(0.1);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }
    public static ScaleTransition getHeartbeat(Node node) {
        ScaleTransition heartbeat = new ScaleTransition(Duration.millis(75), node);
        heartbeat.setByX(0.15);
        heartbeat.setByY(0.15);
        heartbeat.setAutoReverse(true);
        heartbeat.setCycleCount(2);
        return heartbeat;
    }


}
