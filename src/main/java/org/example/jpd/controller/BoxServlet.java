package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.util.AlgorithmUtil;
import org.example.jpd.common.util.PrintUtil;
import org.example.jpd.entity.BoxEntity;

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
        BoxEntity boxEntity = new BoxEntity();

        try {
            boxEntity.setLength(Double.parseDouble(req.getParameter("length")));
            boxEntity.setWidth(Double.parseDouble(req.getParameter("width")));
            boxEntity.setHeight(Double.parseDouble(req.getParameter("height")));
            boxEntity.setDensity(Double.parseDouble(req.getParameter("density")));
        } catch (NumberFormatException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        AlgorithmUtil.calculateBox(boxEntity);
        req.setAttribute("boxEntity", boxEntity);
        req.getRequestDispatcher("box.jsp").forward(req, resp);
    }
}
