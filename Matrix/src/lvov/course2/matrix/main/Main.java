package lvov.course2.matrix.main;

import lvov.course2.matrix.Matrix;
import lvov.course2.vector.Vector;

public class Main {
    public static void print(Matrix matrix) {
        System.out.print('{');
        int maxRowIndex = matrix.getRowsCount() - 1;

        for (int i = 0; i < maxRowIndex; i++) {
            System.out.println(matrix.getRow(i));
        }

        System.out.print(matrix.getRow(maxRowIndex));
        System.out.print('}');
    }

    public static void main(String[] args) {
        Vector[] vectorsArray = {
                new Vector(new double[]{-1, 2, -5}),
                new Vector(new double[]{3, 4, 1}),
                new Vector(new double[]{0, 1, 2})
        };

        Matrix matrix1 = new Matrix(vectorsArray);
        Matrix matrix2 = new Matrix(matrix1);

        System.out.printf("%nКоличество столбцов %s%n%n", matrix1.getColumnsCount());
        System.out.printf("Количество строк %s%n%n", matrix1.getRowsCount());

        System.out.printf("Строка матрицы до установки%n%s%n", matrix1.getRow(1));
        matrix1.setRow(1, new Vector(new double[]{31, 6, -15}));
        System.out.printf("%nМатрица после установки строки%n");
        print(matrix1);

        System.out.printf("%nМатрица 2, скопированная до установки строки в матрице%n");
        print(matrix2);

        Matrix matrix3 = new Matrix(vectorsArray);
        matrix3.transpose();
        System.out.printf("%nТранспонированная матрица%n");
        print(matrix3);

        Matrix matrix4 = new Matrix(vectorsArray);
        matrix4.multiplyByScalar(11);
        System.out.printf("%nМатрица умноженная на скаляр%n");
        print(matrix4);

        Matrix matrix5 = new Matrix(vectorsArray);
        System.out.printf("%nОпределитель матрицы %s%n", matrix5.getDeterminant());

        Matrix matrix6 = new Matrix(vectorsArray);
        matrix5.add(matrix6);
        System.out.printf("%nСумма матриц%n");
        print(matrix5);

        matrix5.subtract(matrix6);
        System.out.printf("%nРазность матриц%n");
        print(matrix5);

        Matrix matrix7 = new Matrix(vectorsArray);
        System.out.printf("%nМатрица умноженная на вектор%n%s%n", matrix7.multiplyByVector(new Vector(new double[]{2, 3, 4})));

        Matrix matrix8 = new Matrix(vectorsArray);
        Matrix matrix9 = new Matrix(vectorsArray);

        System.out.printf("%nСумма матриц%n");
        print(Matrix.getSum(matrix8, matrix9));

        System.out.printf("%nРазность матриц%n");
        print(Matrix.getDifference(matrix8, matrix9));

        System.out.printf("%nУмножение матрицы на матрицу%n");
        print(Matrix.getProduct(matrix8, matrix9));
    }
}