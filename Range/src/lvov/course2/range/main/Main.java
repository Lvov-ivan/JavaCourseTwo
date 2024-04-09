package lvov.course2.range.main;

import lvov.course2.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1.1, 5.8);
        Range range2 = new Range(5.9, 7);

        double number = 2.2;

        System.out.println("Длина диапазона = " + range1.getLength());
        if (range1.isInside(number)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }

        Range rangeIntersection = range1.getSum(range2);
        if (rangeIntersection == null) {
            System.out.println("Нет пересечений данных интервалов");
        } else {
            System.out.println("Пересечения интервала " + range1 + "и интервала " + range2 + " составляет, интервал = " + rangeIntersection);
        }

        Range[] rangeUnion = range1.getUnion(range2);
        if (rangeUnion.length == 1) {
            System.out.println("Объединённый интервал = " + rangeUnion[0]);
        } else {
            System.out.println("Объединённый интервал состоит из 2х интервалов = " + Range.printRanges(rangeUnion));
        }

        Range[] difference = range1.getDifference(range2);
        if (difference.length == 0) {
            System.out.println("Разность интервалов = 0");
        } else
            System.out.println("Разность интервалов = " + Range.printRanges(difference));
    }
}