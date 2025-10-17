package org.example.jpd.common.exception;

import org.example.jpd.common.constant.MessageConstant;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        super(message);
    }
}
