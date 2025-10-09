package org.example.jpd.common.container.impl;

import org.example.jpd.common.container.Container;

public class Cube implements Container {

    private double radius;

    public Cube(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return radius * radius;
    }

    @Override
    public double calculateVolume() {
        return radius * radius * radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
