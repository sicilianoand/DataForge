package org.example.dataforge.exporter;

import org.example.dataforge.exception.ExportException;
import org.example.dataforge.model.DataTable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvExporter extends DataExporter {
    private static final String PREFIX = "CsvExport_";
    private static final String DELIMITER = ",";

    public CsvExporter(DataTable table) {
        super(table);
    }

    @Override
    public void export(String pathname) {
        String finalPath = getFinalPath(pathname, PREFIX);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(finalPath))) {
            writeHeader(bw, DELIMITER);
            writeRows(bw, DELIMITER);
        } catch (IOException e) {
            throw new ExportException(e.getMessage());
        }
    }
}
