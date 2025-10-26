package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.common.util.ValidationUtil;
import org.example.jpd.entity.MaxEntity;
import org.example.jpd.service.MaxService;

import java.io.IOException;

@WebServlet("/max-servlet")
public class MaxServlet extends HttpServlet {

    private MaxService maxService;

    @Override
    public void init() throws ServletException {
        maxService = new MaxService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MaxEntity maxEntity = new MaxEntity();
        maxEntity.setInputA(7.5);
        maxEntity.setInputB(-30);
        req.setAttribute("maxEntity", maxEntity);
        req.getRequestDispatcher("max.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MaxEntity maxEntity = BeanUtil.parseParams(MaxEntity.class, req);
        ValidationUtil.range(-Double.MAX_VALUE, Double.MAX_VALUE,
                maxEntity::getInputA,
                maxEntity::getInputB);
        LogUtil.logInfo("计算最大数：" + maxEntity);
        maxEntity = maxService.solve(maxEntity);
        req.setAttribute("maxEntity", maxEntity);
        req.getRequestDispatcher("max.jsp").forward(req, resp);
    }
}
