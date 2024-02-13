package lvov.shapes.shapes;

import java.util.Objects;

public class Circle implements Shape {
    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public Double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public Double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public String toString(){
        return "Круг: радиус = " + radius;
    }

    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (obj == null || obj.getClass() != getClass()){
            return false;
        }

        Circle circle = (Circle) obj;
        return radius == circle.radius;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);

        return hash;
    }
}