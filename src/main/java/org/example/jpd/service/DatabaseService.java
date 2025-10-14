package org.example.jpd.service;

import org.example.jpd.common.exception.DatabaseException;
import org.example.jpd.dao.BookDao;
import org.example.jpd.entity.BookEntity;

import java.sql.SQLException;
import java.util.List;

public class DatabaseService {

    private final BookDao bookDao;

    public DatabaseService() {
        try {
            this.bookDao = new BookDao();
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void addBook(BookEntity bookEntity) throws IllegalArgumentException, DatabaseException {
        try {

            List<BookEntity> bookEntities = bookDao.selectAllBooks();

            if (bookEntities.stream().anyMatch(entity -> entity.getId() == bookEntity.getId())) {
                throw new IllegalArgumentException("图书 ID 不能重复");
            }

            bookDao.insertBook(bookEntity);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void clearBooks() throws DatabaseException {
        try {
            bookDao.deleteBooks();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public List<BookEntity> getBooks() throws DatabaseException {
        try {
            return bookDao.selectAllBooks();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
