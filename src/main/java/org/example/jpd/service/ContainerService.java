package org.example.jpd.service;

import org.example.jpd.common.container.Container;
import org.example.jpd.common.container.impl.Cube;
import org.example.jpd.common.container.impl.Cylinder;
import org.example.jpd.common.container.impl.Sphere;
import org.example.jpd.entity.ContainerEntity;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ContainerService {

    /*
     * 用 Map 更迎合 interface 的设计，方便扩展
     * 使用函数式接口，延迟创建对象，这样便可声明成静态字段，避免反复创建
     */
    public static final Map<String, Function<ContainerEntity, Container>> containerMap = Map.of(
            "solveCube", containerEntity -> new Cube(containerEntity.getCubeRadius()),
            "solveSphere", containerEntity -> new Sphere(containerEntity.getSphereRadius()),
            "solveCylinder", containerEntity -> new Cylinder(containerEntity.getCylinderRadius(), containerEntity.getCylinderHeight())
    );

    public ContainerEntity calculate(ContainerEntity containerEntity, List<String> parameterNames) {
        boolean handled = false;

        // 只计算匹配按钮的 Container
        for (var entry : containerMap.entrySet()) {
            if (parameterNames.contains(entry.getKey())) {
                Container container = entry.getValue().apply(containerEntity);
                containerEntity.setArea(container.calculateArea());
                containerEntity.setVolume(container.calculateVolume());
                handled = true;
                break;
            }
        }

        // 如果没有一个匹配，则默认求总量
        if (!handled) {
            double totalArea = 0, totalVolume = 0;
            for (var func : containerMap.values()) {
                Container container = func.apply(containerEntity);
                totalArea += container.calculateArea();
                totalVolume += container.calculateVolume();
            }
            containerEntity.setArea(totalArea);
            containerEntity.setVolume(totalVolume);
        }

        return containerEntity;
    }
}
