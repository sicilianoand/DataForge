package org.example.dataforge.exception;

public class FileParseException extends RuntimeException {
    public FileParseException(String cause) {
        super(cause);
    }
    public FileParseException(String cause, Exception e) {
        super(cause + e.getCause().toString());
    }
}
