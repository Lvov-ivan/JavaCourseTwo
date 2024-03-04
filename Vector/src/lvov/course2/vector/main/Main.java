package lvov.course2.vector.main;

import lvov.course2.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = {1, 3};
        double[] array2 = {1, 2, 1};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);

        System.out.printf("Сумма векторов " + vector1 + " и " + vector2 + " = ");
        vector1.add(vector2);
        System.out.println(vector1);

        System.out.printf("Разность векторов " + vector1 + " и " + vector2 + " = ");
        vector1.subtract(vector2);
        System.out.println(vector1);

        System.out.printf("Вектор " + vector1 + " умноженный на скаляр 2 = ");
        vector1.multiplyByScalar(2);
        System.out.println(vector1);

        System.out.printf("Вектор " + vector1 + " до разворота. После разворота = ");
        vector1.revert();
        System.out.println(vector1);

        System.out.println("Длина вектора = " + vector1.getLength());

        System.out.printf("Установка значения 2 по индексу 0, для вектора " + vector2);
        vector2.setElement(0, 2);
        System.out.println(" После установки вектор = " + vector2);

        double[] array3 = {2, 6, 9};
        double[] array4 = {3, 5, 1, 11};

        Vector vector3 = new Vector(array3);
        Vector vector4 = new Vector(array4);

        System.out.println("Сумма векторов " + vector3 + " и " + vector4 + " = " + Vector.getSum(vector3, vector4));

        System.out.println("Разность векторов " + vector3 + " и " + vector4 + " = " + Vector.getDifference(vector3, vector4));

        System.out.println("Скалярное произведение векторов = " + vector3 + " и " + vector4 + " = " +
                Vector.getScalarProduct(vector3, vector4));

        if (vector3.equals(vector4)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }

        System.out.println("Hash Code = " + vector4.hashCode());
    }
}