package org.example.dataforge.transformer;

import org.example.dataforge.model.DataTable;

import java.util.*;

public class DeduplicateTrasformer implements DataTransformer{
    private final String column;
    private final DataTable table;

    public DeduplicateTrasformer(String column, DataTable table) {
        this.column = column;
        this.table = table;
    }

    @Override
    public DataTable transform() {
        Set<String> seen = new HashSet<>();
        List<String[]> filteredRows = new ArrayList<>();

        int columnIndex = table.getColumns().indexOf(column);

        for (String[] row : table.getRows()) {
            String val = row[columnIndex];

            if (!seen.contains(val)) {
                seen.add(val);
                filteredRows.add(row);
            }
        }

        return new DataTable(table.getName(), table.getColumns(), filteredRows);
    }
}
