package org.example.dataforge.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.dataforge.model.DataTable;

public class TableController {
    private DataTable table;
    private DataTable preview;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<String[]> tableView;

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

        for (int i = 0; i < table.getColumns().size(); i++) {
            final int colIndex = i;
            TableColumn<String[], String> column = new TableColumn<>(table.getColumns().get(i));
            column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[colIndex]));
            tableView.getColumns().add(column);
        }

        ObservableList<String[]> rows = FXCollections.observableArrayList(table.getRows());
        tableView.setItems(rows);
    }
}
