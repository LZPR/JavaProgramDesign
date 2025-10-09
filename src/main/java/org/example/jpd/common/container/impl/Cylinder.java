package org.example.jpd.common.container.impl;

import org.example.jpd.common.container.Container;

public class Cylinder implements Container {

    private double radius;
    private double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 2 * PI * radius * (height + radius);
    }

    @Override
    public double calculateVolume() {
        return PI * radius * radius * height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
