package org.example.dataforge.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.dataforge.exception.FileErrorException;
import org.example.dataforge.model.DataTable;
import org.example.dataforge.service.DataService;

import java.io.File;
import java.io.IOException;

public class ImportController {
    @FXML
    private Button browseButton;
    @FXML
    private Label dropLabel;

    @FXML
    private void onBrowse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("File supportati", "*.csv", "*.json", "*.txt"));

        File file = fileChooser.showOpenDialog(browseButton.getScene().getWindow());
        if (file != null) {
            final String pathTableView = "/org/example/dataforge/table-view.fxml";
            try {
                DataService service = new DataService();
                DataTable table = service.load(file.getAbsolutePath());

                FXMLLoader loader = new FXMLLoader(getClass().getResource(pathTableView));
                Scene scene = new Scene(loader.load());
                TableController controller = loader.getController();
                controller.setTable(table);

                Stage stage = (Stage) browseButton.getScene().getWindow();
                stage.setScene(scene);
            } catch (FileErrorException | IOException e) {
                System.err.println(e.getMessage() + " " + e.getCause());
            }
        }
    }
}
