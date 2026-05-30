package org.example.dataforge.exporter;

import org.example.dataforge.exception.ExportException;
import org.example.dataforge.model.DataTable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DataExporter {
    protected DataTable table;

    public DataExporter(DataTable table) {
        this.table = table;
    }

    public abstract void export(String pathname);

    public void writeHeader(BufferedWriter bw, String delimiter) throws IOException {
        for (String col : table.getColumns()) bw.write(col + delimiter);
        bw.newLine();
    }

    public void writeRows(BufferedWriter bw, String delimiter) throws IOException {
        for (String[] row : table.getRows()) {
            for (String data : row) bw.write(data + delimiter);
            bw.newLine();
        }
    }

    protected String getFinalPath(String pathname, String prefix) {
        if (pathname.isEmpty()) throw new ExportException("Il percorso non può essere vuoto");

        File dest = new File(pathname);

        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = prefix + table.getName() + "_" + today;
        String finalPath;

        if (dest.isFile()) return dest.getPath();
        return pathname + File.separator + fileName;
    }
}
