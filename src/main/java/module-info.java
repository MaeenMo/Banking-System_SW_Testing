module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.jfoenix;
    requires java.desktop;

    opens org.example to javafx.fxml;
    exports org.example;
}