package lvov.shapes.comparers;

import lvov.shapes.shapes.Shape;

import java.util.Comparator;

public class ShapePerimeterComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape shape1, Shape shape2) {
        return  shape1.getPerimeter().compareTo(shape2.getPerimeter());
    }
}