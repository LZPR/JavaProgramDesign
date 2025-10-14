package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.entity.BookEntity;
import org.example.jpd.service.DatabaseService;

import java.io.IOException;
import java.util.List;

@WebServlet("/database-servlet")
public class DatabaseServlet extends HttpServlet {

    private DatabaseService databaseService;

    @Override
    public void init() throws ServletException {
        databaseService = new DatabaseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookEntity> bookEntities = databaseService.getBooks();
        req.setAttribute("books", bookEntities);
        req.getRequestDispatcher("database.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookEntity bookEntity = BeanUtil.parseParams(BookEntity.class, req);

        if (req.getParameter("add") != null) {
            LogUtil.logInfo("添加图书：" + bookEntity);
            databaseService.addBook(bookEntity);
        } else if (req.getParameter("clear") != null) {
            LogUtil.logInfo("清空图书：" + bookEntity);
            databaseService.clearBooks();
        }

        doGet(req, resp);
    }
}
