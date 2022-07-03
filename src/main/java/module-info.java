module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.demo2 to javafx.fxml;
    opens com.example.demo2.Controller to javafx.fxml;
    exports com.example.demo2;
    exports com.example.demo2.Controller;

}