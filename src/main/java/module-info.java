module com.example.demowithfxhibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;

    opens com.example.demowithfxhibernate.model;
    exports com.example.demowithfxhibernate;
    opens com.example.demowithfxhibernate to javafx.fxml;

}