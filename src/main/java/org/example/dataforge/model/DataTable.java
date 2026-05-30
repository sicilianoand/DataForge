package org.example.dataforge.model;

import java.util.ArrayList;
import java.util.List;

public class DataTable {
    private final String name;

    private final List<String> columns;
    private final List<String[]> rows;

    public DataTable(String name, List<String> columns, List<String[]> rows) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<String[]> getRows() {
        return rows;
    }

    public String[] getRow(int index) {
        return rows.get(index);
    }

    public String[] getColumn(String name) {
        int index = columns.indexOf(name);
        if (index == -1) throw new IllegalArgumentException("Colonna non trovata");

        List<String> list = new ArrayList<>();

        for (String[] row : rows) {
            list.add(row[index]);
        }

        return list.toArray(new String[0]);
    }
}
