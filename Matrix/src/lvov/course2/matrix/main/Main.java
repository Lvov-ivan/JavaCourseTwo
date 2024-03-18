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

        System.out.printf("%nКоличество столбцов %s%n%n", matrix1.getColumnCount());
        System.out.printf("Количество строк %s%n%n", matrix1.getRowCount());

        System.out.printf("Строка матрицы до установки%n%s%n", matrix1.getRow(1));
        matrix1.setRow(1, new Vector(new double[]{31, 6, -15}));
        System.out.printf("%nМатрица после установки строки%n");
        matrix1.print();

        System.out.printf("%nМатрица 2, скопированная до установки строки в матрице%n");
        matrix2.print();

        Matrix matrix3 = new Matrix(vector1);
        matrix3.transpose();
        System.out.printf("%nТранспонированная матрица%n");
        matrix3.print();

        Matrix matrix4 = new Matrix(vector1);
        matrix4.multiplyByScalar(11);
        System.out.printf("%nМатрица умноженная на скаляр%n");
        matrix4.print();

        Matrix matrix5 = new Matrix(vector1);
        System.out.printf("%nОпределитель матрицы %s%n", matrix5.getDeterminant());

        Matrix matrix6 = new Matrix(vector1);
        matrix5.add(matrix6);
        System.out.printf("%nСумма матриц%n");
        matrix5.print();

        matrix5.subtract(matrix6);
        System.out.printf("%nРазность матриц%n");
        matrix5.print();

        Matrix matrix7 = new Matrix(vector1);
        System.out.printf("%nМатрица умноженная на вектор%n%s%n", matrix7.multiplyByVector(new Vector(new double[]{2, 3, 4})));

        Matrix matrix8 = new Matrix(vector1);
        Matrix matrix9 = new Matrix(vector1);

        System.out.printf("%nСумма матриц%n");
        Matrix.getSum(matrix8, matrix9).print();

        System.out.printf("%nРазность матриц%n");
        Matrix.getDifference(matrix8, matrix9).print();

        System.out.printf("%nУмножение матрицы на матрицу%n");
        Matrix.getProduct(matrix8, matrix9).print();
    }
}