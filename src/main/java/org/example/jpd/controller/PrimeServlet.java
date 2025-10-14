package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.entity.PrimeEntity;
import org.example.jpd.service.PrimeService;

import java.io.IOException;

@WebServlet("/prime-servlet")
public class PrimeServlet extends HttpServlet {

    private PrimeService primeService;

    @Override
    public void init() throws ServletException {
        primeService = new PrimeService();
    }

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
        LogUtil.logInfo("判断质数：" + primeEntity);
        primeEntity = primeService.testPrimeNumber(primeEntity);
        req.setAttribute("primeEntity", primeEntity);
        req.getRequestDispatcher("/prime.jsp").forward(req, resp);
    }
}
