package com.urise.webapp.exception;

public class NoFreeSpaceException extends RuntimeException {
    public NoFreeSpaceException(final String message) {
        super(message);
    }
}
