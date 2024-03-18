package lvov.course2.matrix;

import lvov.course2.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int verticalSize, int horizontalSize) {
        if (verticalSize <= 0) {
            throw new IllegalArgumentException("Количество строк матрицы не может быть 0 или отрицательным. " +
                    "Переданное количество строк = " + verticalSize);
        }

        if (horizontalSize <= 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы нне может быть 0 или отрицательным. " +
                    "Переданное количество столбцов = " + horizontalSize);
        }

        rows = new Vector[verticalSize];

        for (int i = 0; i < verticalSize; i++) {
            rows[i] = new Vector(horizontalSize);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрица не может быть null");
        }

        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Переданный массив не может быть null");
        }

        if (array[0].length == 0) {
            throw new IllegalArgumentException("Количество колонок матрицы не может быть 0");
        }

        int maxRowLength = 0;

        for (double[] row : array) {
            maxRowLength = Math.max(row.length, maxRowLength);
        }

        rows = new Vector[maxRowLength];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new NullPointerException("Массив векторов не может быть null");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Массив векторов пустой");
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(1);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowCount() {
        return rows.length;
    }

    public int getColumnCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        checkRowIndex(rowIndex);

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector row) {
        checkRowIndex(rowIndex);

        if (getColumnCount() != row.getSize()) {
            throw new IllegalArgumentException("Размерность передаваемой строки не равна количеству столбцов матрицы." +
                    " Количество столбцов матрицы " + getColumnCount() + " Длина передаваемой строки " + row.getSize());
        }

        for (int i = 0; i < row.getSize(); i++) {
            rows[rowIndex].setElement(i, row.getElement(i));
        }
    }

    public Vector getColumn(int columnIndex) {
        checkColumnIndex(columnIndex);

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setElement(i, rows[i].getElement(columnIndex));
        }

        return column;
    }

    public void setColumn(int columnIndex, Vector column) {
        checkColumnIndex(columnIndex);

        if (getRowCount() != column.getSize()) {
            throw new IllegalArgumentException("Размерность передаваемого столбца не равна количеству строк матрицы. " +
                    "Количество строк матрицы " + rows.length + " Размерность передаваемого столбца " + column.getSize());
        }

        for (int i = 0; i < column.getSize(); i++) {
            rows[i].setElement(columnIndex, column.getElement(i));
        }
    }

    public void transpose() {
        Vector[] transposeMatrix = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            if (rows[i].getSize() != getColumnCount()) {
                throw new IllegalStateException("Нельзя транспонировать матрицу которая не является квадратной");
            }

            transposeMatrix[i] = new Vector(getColumn(i));
        }

        rows = transposeMatrix;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnCount()) {
            throw new IllegalStateException("Нельзя вычислить определитель для матрицы которая не является квадратной." +
                    " Переданная матрица: количество строк = " + getRowCount() + ", количество столбцов = "
                    + getColumnCount());
        }

        if (rows.length == 1) {
            return rows[0].getElement(0);
        }

        if (rows.length == 2) {
            return rows[0].getElement(0) * rows[1].getElement(1)
                    - rows[0].getElement(1) * rows[1].getElement(0);
        }

        double determinant = 0;

        for (int i = 0; i < rows.length; i++) {
            Matrix minor = new Matrix(rows.length - 1, rows.length - 1);

            for (int j = 1; j <= minor.rows.length; j++) {
                for (int k = 0, insertIndex = 0; k <= minor.rows.length; k++) {
                    if (k == i) {
                        continue;
                    }

                    minor.rows[j - 1].setElement(insertIndex, rows[j].getElement(k));
                    insertIndex++;
                }
            }

            determinant += Math.pow(-1, i) * rows[0].getElement(i) * minor.getDeterminant();
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (Vector row : rows) {
            stringBuilder.append(row.toString());
        }

        stringBuilder
                .delete(stringBuilder.length() - 4, stringBuilder.length())
                .append('}');

        return stringBuilder.toString();
    }

    public void print() {
        System.out.print('{');

        for (int i = 0; i < rows.length; i++) {
            if (i == rows.length - 1) {
                System.out.println(rows[i].toString() + '}');
            } else {
                System.out.println(rows[i].toString());

            }
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != rows.length) {
            throw new IllegalArgumentException("Размерность передаваемого вектора не равна количеству строк матрицы. " +
                    "Количество строк = " + getRowCount() + ". Размерность передаваемого вектора = " + vector.getSize());
        }

        Vector resultVector = new Vector(vector.getSize());

        for (int i = 0; i < rows.length; i++) {
            resultVector.setElement(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        checkEqualityMatricesSizes(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkEqualityMatricesSizes(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkEqualityMatricesSizes(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkEqualityMatricesSizes(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Количество строк первой матрицы не совпадает с количеством столбцов второй." +
                    " Количество строк в первой матрице = " + matrix1.getRowCount() +
                    " Количество столбцов второй матрицы = " + matrix2.getColumnCount());
        }

        Matrix resultMatrix = new Matrix(matrix1.getRowCount(), matrix1.getColumnCount());

        for (int i = 0; i < matrix1.rows.length; i++) {
            resultMatrix.setColumn(i, matrix1.multiplyByVector(matrix2.getColumn(i)));
        }

        return resultMatrix;
    }

    private void checkRowIndex(int rowIndex) {
        if (rowIndex < 0 || rowIndex > getRowCount() - 1) {
            throw new IndexOutOfBoundsException("Переданный индекс не может быть отрицательным или больше количества" +
                    " строк матрицы. Переданный индекс = " + rowIndex + ". Допустимые границы от 0 до "
                    + (getRowCount() - 1));
        }
    }

    private void checkColumnIndex(int columnIndex) {
        if (columnIndex < 0 || columnIndex > getColumnCount() - 1) {
            throw new IndexOutOfBoundsException("Переданный индекс не может быть отрицательным или больше количества" +
                    " столбцов матрицы. Переданный индекс = " + columnIndex + ". Допустимые границы от 0 до "
                    + (getColumnCount() - 1));
        }
    }

    private static void checkEqualityMatricesSizes(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() || matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Размеры матриц не совпадают. Исходная матрица: количество строк = " +
                    matrix1.getRowCount() + ", количество столбцов = " + matrix1.getColumnCount() +
                    " Переданная матрица: количество строк = " + matrix2.getRowCount() + ", количество столбцов = "
                    + matrix2.getColumnCount());
        }
    }
}