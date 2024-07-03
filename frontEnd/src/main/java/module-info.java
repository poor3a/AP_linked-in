module poorsa.org.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens poorsa.org.frontend to javafx.fxml;
    exports poorsa.org.frontend;
}