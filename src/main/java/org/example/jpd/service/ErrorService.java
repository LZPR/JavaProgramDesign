package org.example.jpd.service;

import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.exception.DatabaseException;
import org.example.jpd.entity.ErrorEntity;

import java.util.Map;
import java.util.Objects;

public class ErrorService {

    public ErrorEntity handleError(ErrorEntity errorEntity) {
        Throwable exception = errorEntity.getException();
        Objects.requireNonNull(exception);
        return errorEntity;
    }
}
