package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.entity.StreamEntity;
import org.example.jpd.service.StreamService;

import java.io.IOException;

@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10, // 10 MB
        fileSizeThreshold = 1024 * 1024 // 1 MB
)
@WebServlet("/stream-servlet")
public class StreamServlet extends HttpServlet {

    private StreamService streamService;

    @Override
    public void init() throws ServletException {
        streamService = new StreamService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StreamEntity streamEntity = new StreamEntity();
        req.setAttribute("streamEntity", streamEntity);
        req.getRequestDispatcher("stream.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StreamEntity streamEntity;
        Part filePart = req.getPart("file");
        LogUtil.logInfo("处理文件：" + filePart.getSubmittedFileName());
        streamEntity = streamService.parseFile(filePart);
        req.setAttribute("streamEntity", streamEntity);
        req.getRequestDispatcher("stream.jsp").forward(req, resp);
    }
}
