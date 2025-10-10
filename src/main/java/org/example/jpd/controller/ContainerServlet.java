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
import org.example.jpd.entity.ContainerEntity;
import org.example.jpd.service.ContainerService;

import java.io.IOException;
import java.util.Collections;

@WebServlet("/container-servlet")
public class ContainerServlet extends HttpServlet {

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
        ContainerEntity containerEntity;

        try {
            containerEntity = BeanUtil.parseParams(ContainerEntity.class, req);
        } catch (NumberFormatException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
            return;
        }

        ContainerService containerService = SimpleBeanFactory.getInstance(ContainerService.class);
        containerEntity = containerService.calculate(containerEntity, Collections.list(req.getParameterNames()));

        req.setAttribute("containerEntity", containerEntity);
        req.getRequestDispatcher("container.jsp").forward(req, resp);
    }
}
