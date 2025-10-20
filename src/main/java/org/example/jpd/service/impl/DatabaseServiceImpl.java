package org.example.jpd.service.impl;

import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.exception.DatabaseException;
import org.example.jpd.dao.BookDao;
import org.example.jpd.entity.BookEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class DatabaseServiceImpl implements org.example.jpd.service.DatabaseService {

    private BookDao bookDao;

    @Override
    public void connect() {
        try {
            this.bookDao = new BookDao();
            this.bookDao.connect();
        } catch (ClassNotFoundException | SQLException e) {
            // 这两个异常必须在编译期处理，所以先将它们转换为运行时异常，方便后续统一处理
            throw new DatabaseException(MessageConstant.DATABASE_ERROR);
        }
    }

    @Override
    public void disconnect() {
        try {
            bookDao.disconnect();
        } catch (SQLException e) {
            throw new DatabaseException(MessageConstant.DATABASE_ERROR);
        }
    }

    @Override
    public void addBook(BookEntity bookEntity) throws IllegalArgumentException, DatabaseException {
        try {
            List<BookEntity> bookEntities = bookDao.displayBook();

            // 虽然数据库中不允许 ID 重复，但先检查一遍可以提前反馈出问题
            for (BookEntity entity : bookEntities) {
                if (Objects.equals(entity.getId(), bookEntity.getId())) {
                    throw new IllegalArgumentException(MessageConstant.DUPLICATED_ID_ERROR);
                }
            }

            bookDao.insertBook(bookEntity);
        } catch (SQLException e) {
            throw new DatabaseException(MessageConstant.DATABASE_ERROR);
        }
    }

    @Override
    public List<BookEntity> getBooks() throws DatabaseException {
        try {
            return bookDao.displayBook();
        } catch (SQLException e) {
            throw new DatabaseException(MessageConstant.DATABASE_ERROR);
        }
    }
}
