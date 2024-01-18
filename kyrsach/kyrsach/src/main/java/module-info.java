module com.example.kyrsach {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;


    opens com.example.kyrsach to javafx.fxml;
    exports com.example.kyrsach;
}