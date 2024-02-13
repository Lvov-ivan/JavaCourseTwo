package lvov.shapes;

import lvov.shapes.comparers.ShapeAreaComparator;
import lvov.shapes.comparers.ShapePerimeterComparator;
import lvov.shapes.shapes.*;

import java.util.Arrays;

public class Main {
    public static void print(Shape shape) {
        System.out.println(shape);
        System.out.printf("Площадь фигуры = %.2f", shape.getArea());
        System.out.println();
        System.out.printf("Периметр фигуры %.2f", shape.getPerimeter());
        System.out.println();
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        if (shapes.length == 0) {
            throw new IllegalArgumentException("Массив фигур пуст");
        }

        Arrays.sort(shapes, new ShapeAreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondPerimeter(Shape[] shapes) {
        if (shapes.length == 0) {
            throw new IllegalArgumentException("Массив фигур пуст");
        }

        if (shapes.length == 1) {
            throw  new IllegalArgumentException("Массив состоит из 1 фигуры");
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());

        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(23.4),
                new Circle(55),
                new Triangle(2, 2, 3, 0, -2, -4),
                new Triangle(-3.15, 2.22, 3.76, 4.0, 6.0, 4.18),
                new Rectangle(4.9, 2),
                new Rectangle(10, 15),
                new Square(6),
                new Square(76)
        };

        System.out.println("Фигура с максимальной площадью:");
        print(getShapeWithMaxArea(shapes));

        System.out.println("Фигура со вторым по размеру периметром:");
        print(getShapeWithSecondPerimeter(shapes));
    }
}