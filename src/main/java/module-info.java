module org.example.ormcourseworkfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;
    requires spring.security.crypto;

    //exports org.example.ormcourseworkfinal.controller;
    opens org.example.ormcourseworkfinal.entity to org.hibernate.orm.core;
    opens org.example.ormcourseworkfinal.tm to javafx.base;
    opens org.example.ormcourseworkfinal.controller to javafx.fxml;

    exports org.example.ormcourseworkfinal;
}