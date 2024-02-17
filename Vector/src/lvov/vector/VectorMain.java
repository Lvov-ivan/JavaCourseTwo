package lvov.vector;

public class VectorMain {
    public static void main(String[] args) {
        double[] array1 = {1, 3};
        double[] array2 = {1, 2, 1};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);

        vector1.sum(vector2);
        System.out.println("Сумма векторов = " + vector1);

        vector1.subtract(vector2);
        System.out.println("Разность векторов = " + vector1);

        vector1.multiplyByScalar(2);
        System.out.println("Умножение на скаляр = " + vector1);

        vector1.revert();
        System.out.println("Разворот вектора = " + vector1);

        System.out.println("Длина вектора = " + vector1.getLength());

        System.out.println("Установка значения, до " + vector2);
        vector2.setElement(0, 2);
        System.out.println("Установка значения, после " + vector2);

        double[] array3 = {2, 6, 9};
        double[] array4 = {3, 5, 1, 11};


        Vector vector3 = new Vector(array3);
        Vector vector4 = new Vector(array4);

        System.out.println("Сумма двух векторов = " + Vector.getSum(vector3, vector4));

        System.out.println("Разность двух векторов = " + Vector.getSubtract(vector3, vector4));

        System.out.println("Скалярное произведение векторов = " + Vector.getScalar(vector3, vector4));

        if (vector3.equals(vector4)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }

        System.out.println("Hash Code = " + vector4.hashCode());
    }
}