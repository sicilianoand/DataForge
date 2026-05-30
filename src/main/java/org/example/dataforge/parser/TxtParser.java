package org.example.dataforge.parser;

import org.example.dataforge.model.DataTable;

import java.io.File;

public class TxtParser extends Parser {
    private final String delimiter;

    public TxtParser(File file) {
        this(file, "\t");
    }

    public TxtParser(File file, String delimiter) {
        super(file);
        this.delimiter = delimiter;
    }

    @Override
    public DataTable parse() {
        return parseDelimited(delimiter);
    }
}
