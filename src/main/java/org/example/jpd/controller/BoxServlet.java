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
import org.example.jpd.entity.BoxEntity;
import org.example.jpd.service.BoxService;

import java.io.IOException;

@WebServlet("/box-servlet")
public class BoxServlet extends HttpServlet {

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
        BoxEntity boxEntity;

        try {
            boxEntity = BeanUtil.parseParams(BoxEntity.class, req);
        } catch (NumberFormatException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        BoxService boxService = SimpleBeanFactory.getInstance(BoxService.class);
        boxEntity = boxService.calculateBox(boxEntity);

        req.setAttribute("boxEntity", boxEntity);
        req.getRequestDispatcher("box.jsp").forward(req, resp);
    }
}
