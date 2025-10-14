package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.entity.FibonacciEntity;
import org.example.jpd.service.FibonacciService;

import java.io.IOException;

@WebServlet("/fibonacci-servlet")
public class FibonacciServlet extends HttpServlet {

    private FibonacciService fibonacciService;

    @Override
    public void init() throws ServletException {
        fibonacciService = new FibonacciService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FibonacciEntity fibonacciEntity = new FibonacciEntity();
        fibonacciEntity.setInput(1);
        req.setAttribute("fibonacciEntity", fibonacciEntity);
        req.getRequestDispatcher("/fibonacci.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FibonacciEntity fibonacciEntity;
        fibonacciEntity = BeanUtil.parseParams(FibonacciEntity.class, req);
        fibonacciEntity = fibonacciService.getFibonacci(fibonacciEntity);
        req.setAttribute("fibonacciEntity", fibonacciEntity);
        req.getRequestDispatcher("/fibonacci.jsp").forward(req, resp);
    }
}
