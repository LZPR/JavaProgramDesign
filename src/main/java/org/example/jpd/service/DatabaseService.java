package org.example.jpd.service;

import org.example.jpd.common.exception.DatabaseException;
import org.example.jpd.entity.BookEntity;

import java.util.List;

public interface DatabaseService {
    void connect();

    void disconnect();

    void addBook(BookEntity bookEntity) throws IllegalArgumentException, DatabaseException;

    List<BookEntity> getBooks() throws DatabaseException;
}
