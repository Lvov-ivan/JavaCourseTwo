package lvov.course2.matrix.main;

import lvov.course2.matrix.Matrix;
import lvov.course2.vector.Vector;

public class Main {
    public static void main(String[] args) {

        Vector[] vector1 = {
                new Vector(new double[]{-1, 2, -5}),
                new Vector(new double[]{3, 4, 1}),
                new Vector(new double[]{0, 1, 2})
        };
        Matrix matrix1 = new Matrix(vector1);
        Matrix matrix2 = new Matrix(matrix1);

        System.out.printf("Размерность матрицы %s %n %n", matrix1.getSize());

        System.out.printf("Строка матрицы до установки %n %s %n %n", matrix1.getRow(1));
        matrix1.setRow(1, new double[]{31, 6, -15});
        System.out.printf("Матрица после установки строки %n %s %n %n", matrix1);

        System.out.printf("Матрица 2, скопированная до установки строки в матрице 1 %n %s %n %n", matrix2);

        Matrix matrix3 = new Matrix(vector1);
        matrix3.transposition();
        System.out.printf("Транспонированная матрица %n %s %n %n", matrix3);

        Matrix matrix4 = new Matrix(vector1);
        matrix4.multiplyByScalar(11);
        System.out.printf("Матрица умноженная на скаляр %n %s %n %n", matrix4);

        Matrix matrix5 = new Matrix(vector1);
        System.out.printf("Определитель матрицы %s %n %n", matrix5.calculationDeterminants());

        Matrix matrix6 = new Matrix(vector1);
        matrix5.add(matrix6);
        System.out.printf("Сумма матриц %n %s %n %n", matrix5);

        matrix5.subtract(matrix6);
        System.out.printf("Разность матриц %n %s %n %n", matrix5);

        Matrix matrix7 = new Matrix(vector1);
        matrix7.multiplyByVector(new Vector(new double[]{2, 3, 4}));
        System.out.printf("Матрица умноженная на вектор %n %s %n %n", matrix7);

        Matrix matrix8 = new Matrix(vector1);
        Matrix matrix9 = new Matrix(vector1);

        System.out.printf("Сумма матриц %n %s %n %n", Matrix.getSum(matrix8, matrix9));

        System.out.printf("Разность матриц %n %s %n %n", Matrix.getDifference(matrix8, matrix9));

        System.out.printf("Умножение матрицы на матрицу %n %s %n %n", Matrix.getMultiplyMatrix(matrix8, matrix9));
    }
}