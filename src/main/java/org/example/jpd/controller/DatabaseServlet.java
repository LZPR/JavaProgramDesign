package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.factory.SimpleBeanFactory;
import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.common.util.PrintUtil;
import org.example.jpd.entity.BookEntity;
import org.example.jpd.service.DatabaseService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/database-servlet")
public class DatabaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseService databaseService = SimpleBeanFactory.getInstance(DatabaseService.class);

        try {
            List<BookEntity> bookEntities = databaseService.getBooks();
            req.setAttribute("books", bookEntities);
        } catch (SQLException | ClassNotFoundException e) {
            PrintUtil.printError(resp, MessageConstant.SQL_EXCEPTION, e);
            return;
        } catch (IllegalArgumentException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        req.getRequestDispatcher("database.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookEntity bookEntity;

        try {
            bookEntity = BeanUtil.parseParams(BookEntity.class, req);
        } catch (NumberFormatException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        DatabaseService databaseService = SimpleBeanFactory.getInstance(DatabaseService.class);

        try {
            if (req.getParameter("add") != null)
                databaseService.addBook(bookEntity);
            else if (req.getParameter("clear") != null) {
                databaseService.clearBooks();
            }
        } catch (SQLException | ClassNotFoundException e) {
            PrintUtil.printError(resp, MessageConstant.SQL_EXCEPTION, e);
            return;
        }

        doGet(req, resp);
    }
}
