package org.example.dataforge.service;

import org.example.dataforge.exception.FileErrorException;
import org.example.dataforge.model.DataTable;
import org.example.dataforge.repository.FileRepository;

public class DataService {
    public DataTable load(String pathname) throws FileErrorException {
        return new FileRepository(pathname)
                .getParser()
                .parse();
    }
}
