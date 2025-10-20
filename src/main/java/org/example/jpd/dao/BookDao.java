package org.example.jpd.dao;

import org.example.jpd.entity.BookEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends BaseDao {

    public List<BookEntity> displayBook() throws SQLException {
        return selectAllBooks();
    }

    public List<BookEntity> selectAllBooks() throws SQLException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("select * from book order by book_price asc")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<BookEntity> result = new ArrayList<>();

                while (resultSet.next()) {
                  BookEntity book = new BookEntity();
                  book.setId(resultSet.getInt("book_id"));
                  book.setName(resultSet.getString("book_name"));
                  book.setPrice(resultSet.getDouble("book_price"));
                  book.setAuthor(resultSet.getString("book_author"));
                  book.setPublish(resultSet.getString("book_publish"));
                  book.setType(resultSet.getString("book_type"));
                  result.add(book);
                }

                return result;
            }
        }
    }

    public void insertBook(BookEntity book) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("insert into book(book_id, book_name, book_price, book_author, book_publish, book_type)" +
                        "values (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, book.getId());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setString(4, book.getAuthor());
            preparedStatement.setString(5, book.getPublish());
            preparedStatement.setString(6, book.getType());

            preparedStatement.execute();
        }
    }
}
