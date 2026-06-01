module org.example.dataforge {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires com.google.gson;
    requires java.desktop;

    opens org.example.dataforge to javafx.fxml;
    opens org.example.dataforge.controller to javafx.fxml;
    exports org.example.dataforge;
}