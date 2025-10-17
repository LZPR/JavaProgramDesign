package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.entity.BookEntity;
import org.example.jpd.service.DatabaseService;
import org.example.jpd.service.impl.DatabaseServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/database-servlet")
public class DatabaseServlet extends HttpServlet {

    private DatabaseService databaseService;

    @Override
    public void init() throws ServletException {
        databaseService = new DatabaseServiceImpl();
        databaseService.connect();
    }

    @Override
    public void destroy() {
        databaseService.disconnect();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookEntity> bookEntities = databaseService.getBooks();
        req.setAttribute("books", bookEntities);
        req.getRequestDispatcher("database.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookEntity bookEntity = new BookEntity();

        try {
            bookEntity.setId(Integer.parseInt(req.getParameter("id")));
            bookEntity.setName(req.getParameter("name"));
            bookEntity.setPrice(Double.parseDouble(req.getParameter("price")));
            bookEntity.setAuthor(req.getParameter("author"));
            bookEntity.setPublish(req.getParameter("publish"));
            bookEntity.setType(req.getParameter("type"));
        } catch (NumberFormatException | NullPointerException e) {
            // 字符串到数字的转换可能会出现异常（类型不匹配或为空）
            throw new IllegalArgumentException(MessageConstant.ILLEGAL_INPUT_ERROR);
        }

        // 由于数据库中字段长度的限制为20，所以先提前把问题检查出来
        if(bookEntity.getName().length() > 20 || bookEntity.getAuthor().length() > 20 ||
            bookEntity.getPublish().length() > 20 || bookEntity.getType().length() > 20) {
            throw new IllegalArgumentException(MessageConstant.ILLEGAL_INPUT_ERROR);
        }

        if (req.getParameter("add") != null) {
            // 输出日志，方便调试
            LogUtil.logInfo("添加图书：" + bookEntity);
            databaseService.addBook(bookEntity);
        }

        // 刷新页面（因为逻辑相同，所以直接复用）
        doGet(req, resp);
    }
}
