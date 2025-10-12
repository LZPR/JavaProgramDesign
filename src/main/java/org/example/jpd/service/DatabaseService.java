package org.example.jpd.service;

import org.example.jpd.common.exception.DatabaseException;
import org.example.jpd.dao.BookDao;
import org.example.jpd.entity.BookEntity;

import java.sql.SQLException;
import java.util.List;

public class DatabaseService {
    public void addBook(BookEntity bookEntity) throws IllegalArgumentException, DatabaseException {
        try (BookDao bookDao = new BookDao()) {

            List<BookEntity> bookEntities = bookDao.selectAllBooks();

            if(bookEntities.stream().anyMatch(entity -> entity.getId() == bookEntity.getId())) {
                throw new IllegalArgumentException("图书 ID 不能重复");
            }

            bookDao.insertBook(bookEntity);
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseException(e);
        }
    }

    public void clearBooks() throws DatabaseException {
        try (BookDao bookDao = new BookDao()) {
            bookDao.deleteBooks();
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseException(e);
        }
    }

    public List<BookEntity> getBooks() throws DatabaseException {
        try (BookDao bookDao = new BookDao()) {
            return bookDao.selectAllBooks();
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseException(e);
        }
    }
}
