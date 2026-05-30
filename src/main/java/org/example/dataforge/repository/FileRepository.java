package org.example.dataforge.repository;

import org.example.dataforge.exception.FileErrorException;
import org.example.dataforge.exception.FileNotExistsException;
import org.example.dataforge.exception.FileNotReadableException;

import org.example.dataforge.exception.UnsupportedFormatException;
import org.example.dataforge.parser.CsvParser;
import org.example.dataforge.parser.JsonParser;
import org.example.dataforge.parser.Parser;
import org.example.dataforge.parser.TxtParser;

import java.io.File;

public class FileRepository {
    private final File file;

    public FileRepository(String pathname) throws FileErrorException {
        file = new File(pathname);
        validate();
    }
    
    private void validate() throws FileErrorException {
        if (!file.exists()) throw new FileNotExistsException("Impossibile trovare il file " + file.getPath());
        if (!file.canRead()) throw new FileNotReadableException("Impossibile leggere il file " + file.getPath());
    }

    public Parser getParser() {
        String name = file.getName();
        if (name.endsWith(".csv")) return new CsvParser(file);
        if (name.endsWith(".json")) return new JsonParser(file);
        if (name.endsWith(".txt")) return new TxtParser(file);
        throw new UnsupportedFormatException("Formato non supportato" + name);
    }
}

