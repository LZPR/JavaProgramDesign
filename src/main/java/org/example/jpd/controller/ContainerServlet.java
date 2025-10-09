package org.example.jpd.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jpd.common.constant.MessageConstant;
import org.example.jpd.common.container.Container;
import org.example.jpd.common.container.impl.Cube;
import org.example.jpd.common.container.impl.Cylinder;
import org.example.jpd.common.container.impl.Sphere;
import org.example.jpd.common.util.PrintUtil;
import org.example.jpd.entity.ContainerEntity;

import java.io.IOException;
import java.util.Map;

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
        ContainerEntity containerEntity = new ContainerEntity();

        try {
            containerEntity.setCubeRadius(Double.parseDouble(req.getParameter("cube-radius")));
            containerEntity.setSphereRadius(Double.parseDouble(req.getParameter("sphere-radius")));
            containerEntity.setCylinderRadius(Double.parseDouble(req.getParameter("cylinder-radius")));
            containerEntity.setCylinderHeight(Double.parseDouble(req.getParameter("cylinder-height")));
        } catch (NumberFormatException e) {
            PrintUtil.printError(resp, MessageConstant.ILLEGAL_ARGUMENT, e);
        }

        // 用 Map 更迎合 interface 的设计，方便扩展
        Map<String, Container> containerMap = Map.of(
                "solve-cube", new Cube(containerEntity.getCubeRadius()),
                "solve-sphere", new Sphere(containerEntity.getSphereRadius()),
                "solve-cylinder", new Cylinder(containerEntity.getCylinderRadius(), containerEntity.getCylinderHeight())
        );

        boolean handled = false;

        // 只计算匹配按钮的 Container
        for (var entry : containerMap.entrySet()) {
            if (req.getParameter(entry.getKey()) != null) {
                Container container = entry.getValue();
                containerEntity.setArea(container.calculateArea());
                containerEntity.setVolume(container.calculateVolume());
                handled = true;
                break;
            }
        }

        // 如果没有一个匹配，则默认求总量
        if (!handled) {
            double totalArea = 0, totalVolume = 0;
            for(Container container: containerMap.values()){
                totalArea += container.calculateArea();
                totalVolume += container.calculateVolume();
            }
            containerEntity.setArea(totalArea);
            containerEntity.setVolume(totalVolume);
        }

        req.setAttribute("containerEntity", containerEntity);
        req.getRequestDispatcher("container.jsp").forward(req, resp);
    }
}
