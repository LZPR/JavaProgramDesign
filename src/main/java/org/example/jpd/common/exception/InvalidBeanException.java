package org.example.jpd.common.exception;

public class InvalidBeanException extends RuntimeException {
    public InvalidBeanException(String message) {
        super(message);
    }

    public InvalidBeanException(String message, Exception e) {
        super(message, e);
    }
}
