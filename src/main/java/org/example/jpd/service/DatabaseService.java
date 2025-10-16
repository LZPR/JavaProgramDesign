package org.example.jpd.service;

import org.example.jpd.common.exception.DatabaseException;
import org.example.jpd.dao.BookDao;
import org.example.jpd.entity.BookEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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

            //虽然数据库中不允许 ID 重复，但在业务中提前检查可以更好地提示用户
            if (bookEntities.stream().anyMatch(entity -> Objects.equals(entity.getId(), bookEntity.getId()))) {
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

    public void deleteBook(int id) throws DatabaseException {
        try {
            bookDao.deleteBook(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void updateBook(BookEntity bookEntity) throws DatabaseException {
        try {
            bookDao.updateBook(bookEntity);
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
