package org.example.dataforge.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.dataforge.model.DataTable;
import org.example.dataforge.transformer.FilterTransformer;

import java.util.function.Consumer;

public class FilterController {
    private DataTable table;
    private Consumer<DataTable> onResultTable;
    private Button filterBtn;

    @FXML
    private ComboBox<String> columnCombo;

    @FXML
    private TextField valueField;

    public void setTable(DataTable table) {
        this.table = table;
        columnCombo.getItems().addAll(table.getColumns());
    }

    public void setOnResultTable(Consumer<DataTable> onResultTable) {
        this.onResultTable = onResultTable;
    }

    public void setFilterBtn(Button filterBtn) {
        this.filterBtn = filterBtn;
    }

    @FXML
    private void filter() {
        String item = columnCombo.getValue();
        String value = valueField.getText();

        FilterTransformer transformer = new FilterTransformer(item, value, table);
        onResultTable.accept(transformer.transform());
        filterBtn.setVisible(true);
        filterBtn.setManaged(true);



        ((Stage) columnCombo.getScene().getWindow()).close();
    }
}
