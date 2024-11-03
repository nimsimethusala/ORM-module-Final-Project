module org.example.ormcourseworkfinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ormcourseworkfinal to javafx.fxml;
    exports org.example.ormcourseworkfinal;
    exports org.example.ormcourseworkfinal.controller;
    opens org.example.ormcourseworkfinal.controller to javafx.fxml;
}