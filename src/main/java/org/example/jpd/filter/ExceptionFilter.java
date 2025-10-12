package org.example.jpd.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.controller.ErrorServlet;

import java.io.IOException;

/**
 * 异常过滤器。由于 {@link ErrorServlet} 无法正确的获取到异常信息，所以用过滤器作为中间层，手动设置数据。
 */
@WebFilter("/*")
public class ExceptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            LogUtil.logException(e);
            servletRequest.setAttribute("javax.servlet.error.exception", e);
            servletRequest.setAttribute("javax.servlet.error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            servletRequest.setAttribute("javax.servlet.error.servlet_name", "ExceptionFilter");
            servletRequest.setAttribute("javax.servlet.error.request_uri", ((HttpServletRequest) servletRequest).getRequestURI());
            servletRequest.getRequestDispatcher("/error-servlet").forward(servletRequest, servletResponse);
        }
    }
}
