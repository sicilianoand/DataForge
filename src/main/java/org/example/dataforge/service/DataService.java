package org.example.dataforge.service;

import org.example.dataforge.exception.FileErrorException;
import org.example.dataforge.exporter.CsvExporter;
import org.example.dataforge.exporter.DataExporter;
import org.example.dataforge.exporter.JsonExporter;
import org.example.dataforge.exporter.TxtExporter;
import org.example.dataforge.model.DataTable;
import org.example.dataforge.repository.FileRepository;

public class DataService {
    public DataTable load(String pathname) throws FileErrorException {
        return new FileRepository(pathname)
                .getParser()
                .parse();
    }

    public DataExporter getExporter(String format, DataTable table) {
        return switch(format) {
            case "CSV" -> new CsvExporter(table);
            case "JSON" -> new JsonExporter(table);
            case "TXT" -> new TxtExporter(table);
            default -> throw new IllegalArgumentException("Formato non supportato: " + format);
        };
    }
}
