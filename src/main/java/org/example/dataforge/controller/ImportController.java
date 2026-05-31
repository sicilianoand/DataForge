package org.example.dataforge.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.example.dataforge.exception.FileErrorException;
import org.example.dataforge.model.DataTable;
import org.example.dataforge.service.DataService;

import java.io.File;
import java.io.IOException;

import java.util.List;

public class ImportController {
    @FXML
    private Button browseButton;
    @FXML
    private Label dropLabel;

    @FXML
    private StackPane rootPane;

    @FXML
    private void initialize() {
        rootPane.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        rootPane.setOnDragDropped(event -> {
            List<File> files = event.getDragboard().getFiles();

            if (!files.isEmpty()) loadFile(files.get(0));
        });
    }

    @FXML
    private void onBrowse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("File supportati", "*.csv", "*.json", "*.txt"));

        File file = fileChooser.showOpenDialog(browseButton.getScene().getWindow());
        if (file != null) loadFile(file);
    }

    private void loadFile(File file) {
        final String pathTableView = "/org/example/dataforge/table-view.fxml";
        try {
            DataService service = new DataService();
            DataTable table = service.load(file.getAbsolutePath());

            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathTableView));
            Scene scene = new Scene(loader.load());
            TableController controller = loader.getController();
            System.out.println(controller);
            controller.setTable(table);
            controller.setPreview(table.preview(10));
            controller.renderTable(controller.getPreview());

            Stage stage = (Stage) browseButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (FileErrorException | IOException e) {
            System.err.println(e.getMessage() + " " + e.getCause());
        }
    }
}
