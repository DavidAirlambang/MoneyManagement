module com.tugasbesar.tugasbesar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires com.jfoenix;
    requires com.google.gson;

    opens com.tugasbesar.tugasbesar.model;
    opens com.tugasbesar.tugasbesar.dao;
    opens com.tugasbesar.tugasbesar.utility;
    opens com.tugasbesar.tugasbesar to javafx.fxml;
    opens com.tugasbesar.tugasbesar.controller to javafx.fxml;
    exports com.tugasbesar.tugasbesar;
    exports com.tugasbesar.tugasbesar.model;
    exports com.tugasbesar.tugasbesar.dao;
    exports com.tugasbesar.tugasbesar.utility;
    exports com.tugasbesar.tugasbesar.controller;
}