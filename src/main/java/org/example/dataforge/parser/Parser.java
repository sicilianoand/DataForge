package org.example.dataforge.parser;

import org.example.dataforge.exception.FileParseException;
import org.example.dataforge.model.DataTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Parser {
    protected File file;

    public Parser(File file) {
        this.file = file;
    }

    public abstract DataTable parse();

    public DataTable parseDelimited(String delimiter) {
        List<String> nameTable;
        List<String[]> nameCell = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            nameTable = new ArrayList<>(Arrays.asList(line.split(delimiter)));

            while ((line = br.readLine()) != null) {
                nameCell.add(line.split(delimiter));
            }

        } catch (Exception e) {
            throw new FileParseException(e.getMessage());
        }

        return new DataTable(file.getName(), nameTable, nameCell);
    }
}
