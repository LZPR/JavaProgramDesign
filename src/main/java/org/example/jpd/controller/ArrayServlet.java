package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.common.util.StringUtil;
import org.example.jpd.entity.ArrayEntity;
import org.example.jpd.service.ArrayService;

import java.io.IOException;

@WebServlet("/array-servlet")
public class ArrayServlet extends HttpServlet {

    private ArrayService arrayService;

    @Override
    public void init() throws ServletException {
        arrayService = new ArrayService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setInput("10, 80, -20, 35, -85, -100, 0");
        req.setAttribute("arrayEntity", arrayEntity);
        req.getRequestDispatcher("array.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayEntity arrayEntity = BeanUtil.parseParams(ArrayEntity.class, req);
        LogUtil.logInfo("计算数组最大值：" + arrayEntity);
        // TODO 可以在网页上做一个可视化的列表，代替纯文本输入
        arrayEntity = arrayService.getMaxNumber(arrayEntity, StringUtil.toArray(arrayEntity.getInput()));
        req.setAttribute("arrayEntity", arrayEntity);
        req.getRequestDispatcher("array.jsp").forward(req, resp);
    }
}
