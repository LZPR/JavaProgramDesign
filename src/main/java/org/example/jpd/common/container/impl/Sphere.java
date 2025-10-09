package org.example.jpd.common.container.impl;

import org.example.jpd.common.container.Container;

public class Sphere implements Container {

    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return 4 * PI * radius * radius;
    }

    @Override
    public double calculateVolume() {
        return (double) 4 / 3 * PI * radius * radius * radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
