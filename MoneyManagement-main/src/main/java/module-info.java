module com.tugasbesar.tugasbesar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.tugasbesar.tugasbesar to javafx.fxml;
    exports com.tugasbesar.tugasbesar;
}