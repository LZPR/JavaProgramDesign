package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.PrintUtil;

import java.io.IOException;

@WebServlet("/hello-world-servlet")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintUtil.print(resp, "Hello World", """
                ****************************<br/>
                   Welcome to Java World!<br/>
                ****************************<br/>
                """);
    }
}
