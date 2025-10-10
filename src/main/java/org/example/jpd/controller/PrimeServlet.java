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
import org.example.jpd.entity.PrimeEntity;
import org.example.jpd.service.PrimeService;

import java.io.IOException;

@WebServlet("/prime-servlet")
public class PrimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrimeEntity primeEntity = new PrimeEntity();
        primeEntity.setInput(2);
        req.setAttribute("primeEntity", primeEntity);
        req.getRequestDispatcher("prime.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrimeEntity primeEntity = BeanUtil.parseParams(PrimeEntity.class, req);
        PrimeService primeService = SimpleBeanFactory.getInstance(PrimeService.class);

        try {
            primeEntity = primeService.testPrimeNumber(primeEntity);
        } catch (IllegalArgumentException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        req.setAttribute("primeEntity", primeEntity);
        req.getRequestDispatcher("/prime.jsp").forward(req, resp);
    }
}
