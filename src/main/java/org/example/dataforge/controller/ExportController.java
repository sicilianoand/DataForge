package org.example.dataforge.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import org.example.dataforge.exporter.DataExporter;
import org.example.dataforge.model.DataTable;
import org.example.dataforge.service.DataService;

import java.io.File;

public class ExportController {
    private DataTable table;

    private ToggleGroup group;
    @FXML
    private RadioButton csvRadio;
    @FXML
    private RadioButton jsonRadio;
    @FXML
    private RadioButton txtRadio;

    @FXML
    private Button browseButton;

    @FXML
    private Label label;

    @FXML
    private TextField pathField;

    public void setTable(DataTable table) {
        this.table = table;
    }

    @FXML
    private void initialize() {
        group = new ToggleGroup();
        csvRadio.setToggleGroup(group);
        jsonRadio.setToggleGroup(group);
        txtRadio.setToggleGroup(group);
    }

    @FXML
    private void browse() {
        DirectoryChooser chooser = new DirectoryChooser();

        File file = chooser.showDialog(browseButton.getScene().getWindow());
        pathField.setText(file.getAbsolutePath());
    }

    @FXML
    private void export(ActionEvent event) {
        DataService service = new DataService();
        RadioButton button = (RadioButton) group.getSelectedToggle();
        DataExporter exporter = service.getExporter(button.getText(), table);
        exporter.export(pathField.getText());
    }
}
