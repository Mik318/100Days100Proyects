module com.example.day3 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.day3 to javafx.fxml;
    exports com.example.day3;
    exports com.example.day3.controllers;
    opens com.example.day3.controllers to javafx.fxml;
}