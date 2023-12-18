module com.example.anticafe {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;


    opens com.example.anticafe to javafx.fxml;
    exports com.example.anticafe;
}
