package lvov.course2.matrix;

import lvov.course2.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private double[][] matrix;
    private final int row;
    private final int column;

    public Matrix(int n, int m) {
        if (n == 0) {
            throw new IllegalArgumentException("Высота матрицы не может быть 0. Переданная высота = " + n);
        }

        if (m == 0) {
            throw new IllegalArgumentException("Ширина матрицы не может быть 0. Переданная ширина = " + m);
        }

        matrix = new double[n][m];
        row = n;
        column = m;
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрица не может быть null" + matrix);
        }

        if (matrix.row == 0) {
            throw new IllegalArgumentException("Высота матрицы не может быть 0. Высота переданной матрицы " + matrix.row);
        }

        if (matrix.column == 0) {
            throw new IllegalArgumentException("Ширина матрицы не может быть 0. Ширина переданной матрицы " + matrix.column);
        }

        row = matrix.matrix.length;
        column = matrix.matrix[0].length;
        this.matrix = new double[row][column];

        for (int i = 0; i < row; i++) {
            this.matrix[i] = Arrays.copyOf(matrix.matrix[i], this.matrix[0].length);
        }
    }

    public Matrix(Vector[] vector) {
        if (vector == null) {
            throw new NullPointerException("Массив векторов не может быть null");
        }

        if (vector.length == 0) {
            throw new IllegalArgumentException("Массив векторов пустой");
        }

        int maxVectorLength = 0;
        for (Vector e : vector) {
            maxVectorLength = Math.max(e.getSize(), maxVectorLength);
        }

        matrix = new double[vector.length][maxVectorLength];

        row = matrix.length;
        column = matrix[0].length;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector[i].getSize(); j++) {
                matrix[i][j] = vector[i].getElement(j);
            }
        }
    }

    public int getSize() {
        return row * column;
    }

    public Vector getRow(int rowIndex) {
        if (rowIndex >= matrix.length) {
            throw new IllegalArgumentException("Передаваемый индекс больше высоты матрицы. Переданный индекс " + rowIndex);
        }

        return new Vector(matrix[rowIndex]);
    }

    public void setRow(int rowIndex, double[] row) {
        if (matrix[rowIndex].length < row.length) {
            throw new IllegalArgumentException("Длина передаваемой строки больше строки матрицы. Ширина матрицы "
                    + matrix[0].length + " Длина передаваемого массива " + row.length);
        }

        if (row.length == 0) {
            throw new IllegalArgumentException("Присваеваемая строка пустая. Длина строки = " + row.length);
        }

        matrix[rowIndex] = Arrays.copyOf(row, matrix[rowIndex].length);
    }

    public Vector getColumn(int columnIndex) {
        if (columnIndex >= row) {
            throw new IllegalArgumentException("Передаваемый индекс больше размера матрицы. Переданный индекс " + columnIndex);
        }
        Vector column = new Vector(row);

        for (int i = 0; i < this.column; i++) {
            column.setElement(i, matrix[i][columnIndex]);
        }

        return column;
    }

    public void setColumn(int index, Vector column) {
        for (int i = 0; i < column.getSize(); i++) {
            matrix[i][index] = column.getElement(i);
        }
    }

    public void transposition() {
        double[][] newMatrix = new double[column][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }

        matrix = newMatrix;
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] *= scalar;
            }
        }
    }

    public double calculationDeterminants() {
        if (row != column) {
            throw new IllegalArgumentException("Нельзя вычислить детерминант для матрицы которая не является квадратной." +
                    " Переданная матрица: высота = " + row + ", длина = " + column);
        }

        if (row == 1) {
            return matrix[0][0];
        } else if (row == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            double result = 0;
            Matrix tempMatrix = new Matrix(row - 1, row - 1);

            for (int i = 0; i < row; i++) {
                for (int j = 1; j < row; j++) {
                    System.arraycopy(matrix[j], 0, tempMatrix.matrix[j - 1], 0, i);
                    System.arraycopy(matrix[j], i + 1, tempMatrix.matrix[j - 1], i, row - (i + 1));
                }
                result += Math.pow(-1, i) * matrix[0][i] * tempMatrix.calculationDeterminants();
            }

            return result;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (int i = 0; i < row; i++) {
            stringBuilder.append('{');
            for (int j = 0; j < column; j++) {
                stringBuilder
                        .append(matrix[i][j])
                        .append(", ");
            }
            stringBuilder
                    .delete(stringBuilder.length() - 2, stringBuilder.length())
                    .append("}, ")
                    .append("\n ");
        }

        stringBuilder
                .delete(stringBuilder.length() - 4, stringBuilder.length())
                .append('}');

        return stringBuilder.toString();
    }

    public void multiplyByVector(Vector vector) {
        if (vector.getSize() > matrix[0].length) {
            throw new ArrayIndexOutOfBoundsException("Длина передаваемого вектора больше длины матрицы. Длина " +
                    "передаваемого вектора = " + vector.getSize());
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] *= vector.getElement(j);
            }
        }
    }

    public void add(Matrix matrix) {
        if (row != matrix.matrix.length || column != matrix.matrix[0].length) {
            throw new IllegalArgumentException("Размеры матриц не совпадают " +
                    "Матрица к которой прибавляем высота = " + row + ", длина = " + column +
                    " Переданная матрица: высота = " + matrix.row + ", длина = " + matrix.column);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.matrix[i][j] += matrix.matrix[i][j];
            }
        }
    }

    public void subtract(Matrix matrix) {
        if (row != matrix.matrix.length || column != matrix.matrix[0].length) {
            throw new IllegalArgumentException("Размеры матриц не совпадают " +
                    "Матрица к которой прибавляем высота = " + row + ", длина = " + column +
                    " Переданная матрица: высота = " + matrix.row + ", длина = " + matrix.column);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.matrix[i][j] -= matrix.matrix[i][j];
            }
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.row != matrix2.row || matrix1.column != matrix2.column) {
            throw new IllegalArgumentException("Размеры матриц не совпадают " +
                    "Матрица к которой прибавляем высота = " + matrix1.row + ", длина = " + matrix1.column +
                    " Переданная матрица: высота = " + matrix2.row + ", длина = " + matrix2.column);
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.row != matrix2.row || matrix1.column != matrix2.column) {
            throw new IllegalArgumentException("Размеры матриц не совпадают " +
                    "Матрица к которой прибавляем высота = " + matrix1.row + ", длина = " + matrix1.column +
                    " Переданная матрица: высота = " + matrix2.row + ", длина = " + matrix2.column);
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    private static Vector sumRow(Matrix matrix) {
        if (matrix.row == 0) {
            throw new IllegalArgumentException("Высота передаваемой матрицы не может быть 0. Высота переданной матрицы "
                    + matrix.row);
        }

        if (matrix.column == 0) {
            throw new IllegalArgumentException("Ширина передаваемой матрицы не может быть 0. Высота переданной матрицы "
                    + matrix.row);
        }

        Vector newVector = new Vector(matrix.row);

        for (int i = 0; i < matrix.row; i++) {
            double sumRow = 0;
            for (int j = 0; j < matrix.column; j++) {
                sumRow += matrix.matrix[i][j];
            }
            newVector.setElement(i, sumRow);
        }

        return newVector;
    }

    public static Matrix getMultiplyMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.row != matrix2.row || matrix1.column != matrix2.column) {
            throw new IllegalArgumentException("Размеры матриц не совпадают " +
                    "Матрица к которой прибавляем высота = " + matrix1.row + ", длина = " + matrix1.column +
                    " Переданная матрица: высота = " + matrix2.row + ", длина = " + matrix2.column);
        }

        Matrix resultMatrix = new Matrix(matrix1.row, matrix1.column);

        for (int i = 0; i < matrix1.row; i++) {
            Matrix temp = new Matrix(matrix1);

            temp.multiplyByVector(matrix2.getColumn(i));
            resultMatrix.setColumn(i, sumRow(temp));
        }

        return resultMatrix;
    }
}