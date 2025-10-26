package org.example.jpd.service;

import org.example.jpd.common.exception.DatabaseException;
import org.example.jpd.entity.Book;

import java.util.List;

public interface DatabaseService {
    void connect();

    void disconnect();

    void addBook(Book bookEntity) throws IllegalArgumentException, DatabaseException;

    List<Book> getBooks() throws DatabaseException;
}
