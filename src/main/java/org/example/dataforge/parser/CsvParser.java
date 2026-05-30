package org.example.dataforge.parser;

import org.example.dataforge.model.DataTable;

import java.io.File;

public class CsvParser extends Parser {
    private final String delimiter;

    public CsvParser(File file) {
        this(file, ",");
    }

    public CsvParser(File file, String delimiter) {
        super(file);
        this.delimiter = delimiter;
    }

    @Override
    public DataTable parse() {
        return parseDelimited(delimiter);
    }
}
