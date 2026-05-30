package org.example.dataforge.transformer;

import org.example.dataforge.model.DataTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortTrasformer implements DataTransformer {
    private final SortOrder order;
    private final String column;

    private final DataTable table;

    public SortTrasformer(SortOrder order, String column, DataTable table) {
        this.order = order;
        this.column = column;
        this.table = table;
    }

    @Override
    public DataTable transform() {
        int indexColumn = table.getColumns().indexOf(column);
        List<String[]> rows = new ArrayList<>(table.getRows());

        Comparator<String[]> comparator = (row1, row2) -> {
            String val1 = row1[indexColumn];
            String val2 = row2[indexColumn];
            if (order == SortOrder.ASC) return val1.compareTo(val2);
            else return val2.compareTo(val1);
        };

        rows.sort(comparator);

        return new DataTable(table.getName(), table.getColumns(), rows);
    }
}
