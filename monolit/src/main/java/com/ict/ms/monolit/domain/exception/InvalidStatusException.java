package com.ict.ms.monolit.domain.exception;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String value) {
        super("Invalid status: '" + value );
    }
}
