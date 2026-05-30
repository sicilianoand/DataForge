package org.example.dataforge.transformer;

import org.example.dataforge.model.DataTable;

import java.util.ArrayList;
import java.util.List;

public class FilterTransformer implements DataTransformer {
    private final String column;
    private final String value;

    private final DataTable table;

    public FilterTransformer(String column, String value, DataTable table) {
        this.column = column;
        this.value = value;
        this.table = table;
    }

    @Override
    public DataTable transform() {
        List<String[]> rows = table.getRows();
        List<String[]> filteredRows = new ArrayList<>();

        int indexColumn = table.getColumns().indexOf(column);
        for (String[] row : rows) {
            String info = row[indexColumn];
            if (info.equals(value)) filteredRows.add(row);
        }

        return new DataTable(table.getName(), table.getColumns(), filteredRows);
    }
}
