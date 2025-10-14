package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.entity.BoxEntity;
import org.example.jpd.service.BoxService;

import java.io.IOException;

@WebServlet("/box-servlet")
public class BoxServlet extends HttpServlet {

    private BoxService boxService;

    @Override
    public void init() throws ServletException {
        boxService = new BoxService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoxEntity boxEntity = new BoxEntity();
        boxEntity.setLength(5);
        boxEntity.setWidth(10);
        boxEntity.setHeight(7.5);
        boxEntity.setDensity(1.2);
        req.setAttribute("boxEntity", boxEntity);
        req.getRequestDispatcher("box.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoxEntity boxEntity = BeanUtil.parseParams(BoxEntity.class, req);
        LogUtil.logInfo("计算Box体积和重量：" + boxEntity);
        boxEntity = boxService.calculateBox(boxEntity);
        req.setAttribute("boxEntity", boxEntity);
        req.getRequestDispatcher("box.jsp").forward(req, resp);
    }
}
