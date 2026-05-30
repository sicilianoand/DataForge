package org.example.dataforge.transformer;

import org.example.dataforge.model.DataTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RenameColumnTransformer implements DataTransformer {

    private final String columnToReplace;
    private final String column;

    private final DataTable table;

    public RenameColumnTransformer(String columnToReplace, String column, DataTable table) {
        this.columnToReplace = columnToReplace;
        this.column = column;
        this.table = table;
    }

    @Override
    public DataTable transform() {
        List<String> columnList = new ArrayList<>(table.getColumns());
        int columnIndex = columnList.indexOf(columnToReplace);

        columnList.set(columnIndex, column);
        return new DataTable(table.getName(), columnList, table.getRows());
    }
}
