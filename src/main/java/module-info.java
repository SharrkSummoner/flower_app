module com.example.flower_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;
    requires itextpdf;
    requires mybatis;


    opens com.example.flower_app to javafx.fxml;
    exports com.example.flower_app;
}