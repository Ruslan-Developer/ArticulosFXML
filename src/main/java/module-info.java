module com.ruslan.articulosfxml {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;

    opens com.ruslan.articulosfxml to javafx.fxml;
    exports com.ruslan.articulosfxml;
    exports com.ruslan.articulosfxml.modelo;
    opens com.ruslan.articulosfxml.modelo to javafx.fxml;
}