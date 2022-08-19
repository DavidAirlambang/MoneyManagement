module com.tugasbesar.tugasbesar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.tugasbesar.tugasbesar.model;
    opens com.tugasbesar.tugasbesar to javafx.fxml;
    exports com.tugasbesar.tugasbesar;
}