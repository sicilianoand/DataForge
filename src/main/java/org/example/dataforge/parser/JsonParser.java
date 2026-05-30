package org.example.dataforge.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.example.dataforge.exception.FileParseException;
import org.example.dataforge.model.DataTable;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class JsonParser extends Parser {
    public JsonParser(File file) {
        super(file);
    }

    @Override
    public DataTable parse() {
        Gson gson = new Gson();
        List<String> columns = new ArrayList<>();
        List<String[]> rows = new ArrayList<>();

        try (JsonReader reader = gson.newJsonReader(new FileReader(file))) {
            reader.beginArray();
            rows.add(parseFirstRow(reader, columns));
            while (reader.hasNext()) {
                rows.add(parseRow(reader));
            }
            reader.endArray();
        } catch (IOException e) {
            throw new FileParseException(e.getMessage(), e);
        }

        return new DataTable(file.getName(), columns, rows);
    }

    private String[] parseFirstRow(JsonReader reader, List<String> columns) throws IOException {
        List<String> values = new ArrayList<>();
        reader.beginObject();
        while (reader.hasNext()) {
            columns.add(reader.nextName());
            values.add(reader.nextString());
        }
        reader.endObject();
        return values.toArray(new String[0]);
    }

    private String[] parseRow(JsonReader reader) throws IOException {
        List<String> values = new ArrayList<>();
        reader.beginObject();
        while (reader.hasNext()) {
            reader.nextName();
            values.add(reader.nextString());
        }
        reader.endObject();
        return values.toArray(new String[0]);
    }
}
