module com.example.labb3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.labb3 to javafx.fxml;
    exports com.example.labb3;
    exports model;
    opens model to javafx.fxml;
    exports FileSaver;
    opens FileSaver to javafx.fxml;
}