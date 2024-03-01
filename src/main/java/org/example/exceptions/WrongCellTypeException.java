package org.example.exceptions;

public class WrongCellTypeException extends RuntimeException {
    public WrongCellTypeException(String message) {
        super(message);
    }
}
