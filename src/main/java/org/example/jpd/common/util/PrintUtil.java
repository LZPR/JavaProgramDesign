package org.example.jpd.common.util;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 打印工具类，用于快速将信息显示到网页上。
 * <p>{@link #printError}可输出异常信息。
 */
public class PrintUtil {

    public static void print(HttpServletResponse resp,String title, String message) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(message);
        out.println("</body>");
        out.println("</html>");
    }

    public static void printError(HttpServletResponse resp, String message, Exception e) throws IOException {
        print(resp, "Error", message + "<br>" + e.getMessage() + "<br>");
        e.printStackTrace(resp.getWriter());
        System.err.println(e.getMessage());
    }
}
