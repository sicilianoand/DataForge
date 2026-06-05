package org.example.dataforge.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.dataforge.model.DataTable;

import java.io.IOException;
import java.util.Objects;

public class TableController {
    @FXML
    public BorderPane borderPane;
    private DataTable table;
    private DataTable preview;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button transformBtn;
    @FXML
    private Button exportBtn;

    @FXML
    private Label previewLabel;

    @FXML
    private TableView<String[]> tableView;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button removeFilterBtn;

    public DataTable getTable() {
        return table;
    }

    public void setTable(DataTable table) {
        this.table = table;
    }

    public DataTable getPreview() {
        return preview;
    }

    public void setPreview(DataTable preview) {
        this.preview = preview;
    }

    public void renderTable(DataTable table) {
        tableView.getColumns().clear();
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        for (int i = 0; i < table.getColumns().size(); i++) {
            final int colIndex = i;
            TableColumn<String[], String> column = new TableColumn<>(table.getColumns().get(i));
            column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[colIndex]));
            tableView.getColumns().add(column);
        }

        ObservableList<String[]> rows = FXCollections.observableArrayList(table.getRows());
        tableView.setItems(rows);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    @FXML
    private void onClick(ActionEvent event) {
        if (event.getSource() == confirmBtn) {
            renderTable(table);

            borderPane.setTop(menuBar);

            menuBar.setVisible(true);
            menuBar.setManaged(true);

            Stage stage = (Stage) confirmBtn.getScene().getWindow();
            stage.setTitle(table.getName());
            borderPane.setBottom(null);
        } else if (event.getSource() == cancelBtn) goToImport();
    }

    private void goToImport() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dataforge/import-view.fxml"));
            Scene scene = new Scene(loader.load(), 300, 250);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/example/dataforge/style.css")).toExternalForm());
            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle("Scegli un file!");
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onOpen(ActionEvent event) {
        goToImport();
    }

    @FXML
    private void onExport(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dataforge/export-view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Esporta");
            stage.setWidth(400);
            stage.setHeight(300);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            ExportController controller = loader.getController();
            controller.setTable(this.table);

            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onFilter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dataforge/filter-view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Filtra");
            stage.initModality(Modality.APPLICATION_MODAL);

            FilterController controller = loader.getController();
            controller.setTable(this.table);
            controller.setOnResultTable(this::renderTable);
            controller.setFilterBtn(this.removeFilterBtn);

            stage.showAndWait();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    @FXML
    private void onSort(ActionEvent event) {
    }

    @FXML
    private void onDeduplicate(ActionEvent event) {
    }

    @FXML
    private void onRename(ActionEvent event) {

    }

    public void onRemoveFilter(ActionEvent actionEvent) {
        renderTable(this.table);
        removeFilterBtn.setVisible(false);
        removeFilterBtn.setManaged(false);
    }
}
