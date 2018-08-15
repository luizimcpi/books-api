package com.devlhse.booksapi.exception;

public class FieldEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FieldEmptyException() {
        super();
    }
    public FieldEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
    public FieldEmptyException(String message) {
        super(message);
    }
    public FieldEmptyException(Throwable cause) {
        super(cause);
    }
}
