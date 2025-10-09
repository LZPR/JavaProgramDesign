package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.util.AlgorithmUtil;
import org.example.jpd.common.util.PrintUtil;
import org.example.jpd.entity.StreamEntity;

import java.io.*;

@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10, // 10 MB
        fileSizeThreshold = 1024 * 1024 // 1 MB
)
@WebServlet("/stream-servlet")
public class StreamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StreamEntity streamEntity = new StreamEntity();
        req.setAttribute("streamEntity", streamEntity);
        req.getRequestDispatcher("stream.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StreamEntity streamEntity = new StreamEntity();

        try {
            Part filePartB = req.getPart("file");
            StringBuilder sb = new StringBuilder();

            try (InputStream fileContentB = filePartB.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(fileContentB))) {
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }

            // 我的理解是把字符串转换成数字，而不是直接读取二进制数据
            int number = Integer.parseInt(sb.toString());
            streamEntity.setPrimeNumber(AlgorithmUtil.isPrimeNumber(number) ? "是" : "否");
            streamEntity.setFileContent(sb.toString());
        } catch (IllegalArgumentException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        req.setAttribute("streamEntity", streamEntity);
        req.getRequestDispatcher("stream.jsp").forward(req, resp);
    }
}
