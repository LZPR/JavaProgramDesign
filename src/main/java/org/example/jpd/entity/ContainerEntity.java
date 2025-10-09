package org.example.jpd.entity;

public class ContainerEntity {

    private double cubeRadius;
    private double sphereRadius;
    private double cylinderRadius;
    private double cylinderHeight;

    private double area;
    private double volume;

    public double getCubeRadius() {
        return cubeRadius;
    }

    public void setCubeRadius(double cubeRadius) {
        this.cubeRadius = cubeRadius;
    }

    public double getSphereRadius() {
        return sphereRadius;
    }

    public void setSphereRadius(double sphereRadius) {
        this.sphereRadius = sphereRadius;
    }

    public double getCylinderRadius() {
        return cylinderRadius;
    }

    public void setCylinderRadius(double cylinderRadius) {
        this.cylinderRadius = cylinderRadius;
    }

    public double getCylinderHeight() {
        return cylinderHeight;
    }

    public void setCylinderHeight(double cylinderHeight) {
        this.cylinderHeight = cylinderHeight;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
