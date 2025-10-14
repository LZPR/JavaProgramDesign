package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.util.BeanUtil;
import org.example.jpd.common.util.LogUtil;
import org.example.jpd.entity.ContainerEntity;
import org.example.jpd.service.ContainerService;

import java.io.IOException;
import java.util.Collections;

@WebServlet("/container-servlet")
public class ContainerServlet extends HttpServlet {

    private ContainerService containerService;

    @Override
    public void init() throws ServletException {
        containerService = new ContainerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContainerEntity containerEntity = new ContainerEntity();
        containerEntity.setCubeRadius(4);
        containerEntity.setSphereRadius(10);
        containerEntity.setCylinderRadius(2.5);
        containerEntity.setCylinderHeight(8);
        req.setAttribute("containerEntity", containerEntity);
        req.getRequestDispatcher("container.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContainerEntity containerEntity = BeanUtil.parseParams(ContainerEntity.class, req);
        LogUtil.logInfo("计算容器体积和表面积：" + containerEntity);
        containerEntity = containerService.calculate(containerEntity, Collections.list(req.getParameterNames()));
        req.setAttribute("containerEntity", containerEntity);
        req.getRequestDispatcher("container.jsp").forward(req, resp);
    }
}
