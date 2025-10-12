package org.example.jpd.service;

import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.exception.DatabaseException;
import org.example.jpd.entity.ErrorEntity;

import java.util.Map;
import java.util.Objects;

public class ErrorService {

    private static final Map<Class<? extends Throwable>, String> defaultErrorMessages = Map.of(
            NumberFormatException.class, MessageConstant.NUMBER_FORMAT_ERROR,
            IllegalArgumentException.class, MessageConstant.ILLEGAL_ARGUMENT,
            ArithmeticException.class, MessageConstant.ARITHMETIC_EXCEPTION,
            DatabaseException.class, MessageConstant.DATABASE_ERROR
    );

    public ErrorEntity handleError(ErrorEntity errorEntity) {
        Throwable exception = errorEntity.getException();
        Objects.requireNonNull(exception);
        String message =  defaultErrorMessages.getOrDefault(exception.getClass(), "错误");
        errorEntity.setMessage(message);
        return errorEntity;
    }
}
