package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.util.AlgorithmUtil;
import org.example.jpd.common.util.PrintUtil;
import org.example.jpd.entity.FibonacciEntity;

import java.io.IOException;

@WebServlet("/fibonacci-servlet")
public class FibonacciServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FibonacciEntity fibonacciEntity = new FibonacciEntity();
        fibonacciEntity.setInput(1);
        req.setAttribute("fibonacciEntity",fibonacciEntity);
        req.getRequestDispatcher("/fibonacci.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FibonacciEntity fibonacciEntity = new FibonacciEntity();

        try {
            int input = Integer.parseInt(req.getParameter("input"));
            fibonacciEntity.setInput(input);
            fibonacciEntity.setResult(AlgorithmUtil.getFibonacciString(input));
        } catch (IllegalArgumentException e){
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        req.setAttribute("fibonacciEntity",fibonacciEntity);
        req.getRequestDispatcher("/fibonacci.jsp").forward(req,resp);
    }
}
