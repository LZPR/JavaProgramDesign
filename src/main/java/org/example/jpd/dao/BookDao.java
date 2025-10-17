package org.example.jpd.dao;

import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.common.util.SqlUtil;
import org.example.jpd.entity.BookEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDao extends BaseDao {

    public BookDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public List<BookEntity> selectAllBooks() throws SQLException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("select * from book order by book_price asc")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return BeanUtil.parseResult(BookEntity.class, resultSet, "book_");
                /*
                * 等价于：
                * while (resultSet.next()) {
                *   BookEntity book = new BookEntity();
                *   book.setId(resultSet.getInt("book_id"));
                *   book.setName(resultSet.getString("book_name"));
                *   book.setPrice(resultSet.getDouble("book_price"));
                *   book.setAuthor(resultSet.getString("book_author"));
                *   book.setPublish(resultSet.getString("book_publish"));
                *   book.setType(resultSet.getString("book_type"));
                *   result.add(book);
                * }
                * */
            }
        }
    }

    public void insertBook(BookEntity book) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("insert into book(book_id, book_name, book_price, book_author, book_publish, book_type)" +
                        "values (?, ?, ?, ?, ?, ?)")) {
            SqlUtil.fillStatement(preparedStatement, book::getId, book::getName, book::getPrice,
                    book::getAuthor, book::getPublish, book::getType);
            /*
            * 等价于：
            * preparedStatement.setInt(1, book.getId());
            * preparedStatement.setString(2, book.getName());
            * preparedStatement.setDouble(3, book.getPrice());
            * preparedStatement.setString(4, book.getAuthor());
            * preparedStatement.setString(5, book.getPublish());
            * preparedStatement.setString(6, book.getType());
            * */
            preparedStatement.execute();
        }
    }

    public void deleteBooks() throws SQLException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("delete from book")) {
            preparedStatement.execute();
        }
    }

    public void updateBook(BookEntity bookEntity) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("update book set book_name=?, book_price=?, book_author=?, book_publish=?, book_type=? " +
                        "where book_id=?")) {
            SqlUtil.fillStatement(preparedStatement, bookEntity::getName, bookEntity::getPrice,
                    bookEntity::getAuthor, bookEntity::getPublish, bookEntity::getType, bookEntity::getId);
            preparedStatement.execute();
        }
    }

    public void deleteBook(int id) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("delete from book where book_id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }
}
