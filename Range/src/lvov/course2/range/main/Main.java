package lvov.course2.range.main;

import lvov.course2.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1.1, 5.8);
        Range range2 = new Range(5.5, 7);

        double number = 2.2;
        System.out.println("Длина диапазона = " + range1.getLength());

        if (range1.isInside(number)) {
            System.out.println("Число принадлежит диапазону " + range1);
        } else {
            System.out.println("Число не принадлежит диапазону " + range1);
        }

        Range intersection = range1.getIntersection(range2);
        if (intersection == null) {
            System.out.println("Диапазоны " + range1 + " и " + range2 + " не пересекаются.");
        } else {
            System.out.println("Пересечение диапазона " + range1 + " и " + range2 + " составляет, диапазон = " + intersection);
        }

        Range[] union = range1.getUnion(range2);
        System.out.println("Объединение диапазонов " + range1 + " и " + range2 + " = " + Arrays.toString(union));

        Range[] difference = range1.getDifference(range2);
        System.out.println("Разность диапазонов " + range1 + " и " + range2 + " = " + Arrays.toString(difference));
    }
}