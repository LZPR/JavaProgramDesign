package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.util.AlgorithmUtil;
import org.example.jpd.common.util.PrintUtil;
import org.example.jpd.entity.PrimeEntity;

import java.io.IOException;

@WebServlet("/prime-servlet")
public class PrimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrimeEntity primeEntity = new PrimeEntity();
        primeEntity.setInput(0);
        req.setAttribute("primeEntity", primeEntity);
        req.getRequestDispatcher("prime.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrimeEntity primeEntity = new PrimeEntity();

        try {
            int input = Integer.parseInt(req.getParameter("input"));
            primeEntity.setInput(input);
            primeEntity.setResult(AlgorithmUtil.isPrimeNumber(input) ? "是" : "否");
        } catch (IllegalArgumentException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        req.setAttribute("primeEntity", primeEntity);
        req.getRequestDispatcher("/prime.jsp").forward(req, resp);
    }
}
