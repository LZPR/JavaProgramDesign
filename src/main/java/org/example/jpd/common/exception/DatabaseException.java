package org.example.jpd.common.exception;

import org.example.jpd.common.constant.MessageConstant;

public class DatabaseException extends RuntimeException {


    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Exception e) {
        super(message, e);
    }

    public DatabaseException(Exception e) {
        super(MessageConstant.DATABASE_ERROR, e);
    }
}
