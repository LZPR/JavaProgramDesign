package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.util.PrintUtil;
import org.example.jpd.dao.BookDao;
import org.example.jpd.entity.BookEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/database-servlet")
public class DatabaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BookDao bookDao = new BookDao()) {
            List<BookEntity> bookEntities = bookDao.selectAllBooks();
            req.setAttribute("books", bookEntities);
        } catch (SQLException | ClassNotFoundException e) {
            PrintUtil.printError(resp, MessageConstant.SQL_EXCEPTION, e);
            return;
        }

        req.getRequestDispatcher("database.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookEntity bookEntity = new BookEntity();

        try {
            bookEntity.setId(Integer.parseInt(req.getParameter("id")));
            bookEntity.setName(req.getParameter("name"));
            bookEntity.setPrice(Double.parseDouble(req.getParameter("price")));
            bookEntity.setPublish(req.getParameter("publish"));
            bookEntity.setAuthor(req.getParameter("author"));
            bookEntity.setType(req.getParameter("type"));
        } catch (NumberFormatException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        try (BookDao bookDao = new BookDao()) {
            bookDao.connect();
            bookDao.insertBook(bookEntity);
        } catch (SQLException | ClassNotFoundException e) {
            PrintUtil.printError(resp, MessageConstant.SQL_EXCEPTION, e);
            return;
        }

        doGet(req, resp);
    }
}
