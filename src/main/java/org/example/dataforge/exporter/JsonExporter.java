package org.example.dataforge.exporter;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import org.example.dataforge.exception.ExportException;
import org.example.dataforge.model.DataTable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonExporter extends DataExporter {
    private final static String PREFIX = "JsonExport_";
    private final static String FORMAT = ".json";

    public JsonExporter(DataTable table) {
        super(table);
    }

    @Override
    public void export(String pathname) {
        String finalPath = getFinalPath(pathname, PREFIX);
        finalPath += FORMAT;
        Gson gson = new Gson();

        List<String> columns = table.getColumns();
        List<String[]> rows = table.getRows();

        try (JsonWriter writer = gson.newJsonWriter(new FileWriter(finalPath))) {
            writer.setIndent(" ");
            writer.beginArray();
            for (String[] row : rows) {
                writer.beginObject();
                for (int j = 0; j < columns.size(); j++) {
                    writer.name(columns.get(j));
                    writer.value(row[j]);
                }
                writer.endObject();
            }
            writer.endArray();
        } catch (IOException e) {
            throw new ExportException(e.getMessage());
        }
    }
}
