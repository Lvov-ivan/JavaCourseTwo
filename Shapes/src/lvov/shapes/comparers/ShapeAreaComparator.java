package lvov.shapes.comparers;

import lvov.shapes.shapes.Shape;

import java.util.Comparator;

public class ShapeAreaComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape shape1, Shape shape2) {
        return shape1.getArea().compareTo(shape2.getArea());
    }
}