package org.example.dataforge;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("import-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 250);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/example/dataforge/style.css")).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/dataforge/icon.png"))));
        stage.setTitle("Scegli un file!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
