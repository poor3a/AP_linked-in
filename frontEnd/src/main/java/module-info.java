module poorsa.org.frontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens poorsa.org.frontend to javafx.fxml;
    exports poorsa.org.frontend;
}