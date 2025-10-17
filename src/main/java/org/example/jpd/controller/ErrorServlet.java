package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.entity.ErrorEntity;
import org.example.jpd.service.ErrorService;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/error-servlet")
public class ErrorServlet extends HttpServlet {

    private ErrorService errorService;

    @Override
    public void init() throws ServletException {
        errorService = new ErrorService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        Throwable ex = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Integer status = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");

        if (servletName == null){
            servletName = "未知";
        }

        String uri = (String) req.getAttribute("javax.servlet.error.request_uri");

        if (uri == null){
            uri = "未知";
        }

        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setException(ex);
        errorEntity.setServletName(servletName);
        errorEntity.setStatusCode(status);
        errorEntity.setRequestUri(uri);

        LogUtil.logInfo("错误信息：" + errorEntity);

        try {
            Objects.requireNonNull(ex);
            errorEntity = errorService.handleError(errorEntity);
        } catch (Exception e) {
            LogUtil.logInfo("线程名称：" + Thread.currentThread().getName());
            LogUtil.logInfo("请求类型：" + req.getDispatcherType());
            LogUtil.logException(e);
            // 若处理异常的页面也出现异常，则显示当前异常
            errorEntity.setException(e);
        }

        req.setAttribute("errorEntity", errorEntity);
        req.getRequestDispatcher("error.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}