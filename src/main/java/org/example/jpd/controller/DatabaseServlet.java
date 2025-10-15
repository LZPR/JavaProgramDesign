package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.common.util.ValidationUtil;
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
        /* 等价于：
        * bookEntity.setId(Integer.parseInt(req.getParameter("id")));
        * bookEntity.setName(req.getParameter("name"));
        * bookEntity.setPrice(Double.parseDouble(req.getParameter("price")));
        * bookEntity.setAuthor(req.getParameter("author"));
        * bookEntity.setPublish(req.getParameter("publish"));
        * bookEntity.setType(req.getParameter("type"));
        * */
        //非业务的常规检查（与前端形成二次校验）
        ValidationUtil.notNull(bookEntity::getId, bookEntity::getName);
        /* 等价于：
        * if(bookEntity.getId() == null || bookEntity.getName() == null) {
        *     throw ...
        * }
        * */
        ValidationUtil.maxLength(20, bookEntity::getName, bookEntity::getAuthor,
                bookEntity::getPublish, bookEntity::getType);
        /* 等价于：
        * if(bookEntity.getName().length() > 20 || bookEntity.getAuthor().length() > 20 ||
        *     bookEntity.getPublish().length() > 20 || bookEntity.getType().length() > 20) {
        *     throw ...
        * }
        * */
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
