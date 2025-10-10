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
import org.example.jpd.entity.ArrayEntity;
import org.example.jpd.service.ArrayService;

import java.io.IOException;

@WebServlet("/array-servlet")
public class ArrayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setInput("10, 80, -20, 35, -85, -100, 0");
        req.setAttribute("arrayEntity", arrayEntity);
        req.getRequestDispatcher("array.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayEntity arrayEntity = null;

        try {
            arrayEntity = BeanUtil.parseParams(ArrayEntity.class, req);
        } catch (NumberFormatException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        // TODO 可以在网页上做一个可视化的列表，代替纯文本输入
        // 全角转半角，正则可以过滤多余的空格
        String[] elements = arrayEntity.getInput().replace('，', ',').split("(,|\\s)+");
        int[] arr = new int[elements.length];

        try {
            for (int i = 0; i < elements.length; i++) {
                arr[i] = Integer.parseInt(elements[i]);
            }
        } catch (NumberFormatException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        ArrayService arrayService = SimpleBeanFactory.getInstance(ArrayService.class);
        arrayEntity = arrayService.getMaxNumber(arrayEntity, arr);
        req.setAttribute("arrayEntity", arrayEntity);
        req.getRequestDispatcher("array.jsp").forward(req, resp);
    }
}
