package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.factory.SimpleBeanFactory;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.common.util.ThreadUtil;
import org.example.jpd.service.ThreadService;

import java.io.IOException;

@WebServlet("/thread-servlet")
public class ThreadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", ThreadUtil.RESULT);
        req.getRequestDispatcher("thread.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ThreadService threadService = SimpleBeanFactory.getInstance(ThreadService.class);

        if(req.getParameter("start") != null) {
            LogUtil.logInfo("启动所有线程");
            threadService.startAllThreads();
        }
        else if(req.getParameter("clear") != null) {
            LogUtil.logInfo("清空线程结果");
            threadService.clearResult();
        }

        // 刷新网页
        // TODO 可以在 jsp 中用计时器实现自动刷新，或者 WebSocket 直接建立持久连接，但都需要写一些 js 代码
        doGet(req, resp);
    }
}
