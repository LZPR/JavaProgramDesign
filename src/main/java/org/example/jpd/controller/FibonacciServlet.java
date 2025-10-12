package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.factory.SimpleBeanFactory;
import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.entity.FibonacciEntity;
import org.example.jpd.service.FibonacciService;

import java.io.IOException;

@WebServlet("/fibonacci-servlet")
public class FibonacciServlet extends HttpServlet {

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
        FibonacciService fibonacciService = SimpleBeanFactory.getInstance(FibonacciService.class);
        fibonacciEntity = fibonacciService.getFibonacci(fibonacciEntity);
        req.setAttribute("fibonacciEntity", fibonacciEntity);
        req.getRequestDispatcher("/fibonacci.jsp").forward(req, resp);
    }
}
