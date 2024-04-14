package lvov.course2.matrix;

import lvov.course2.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк матрицы не может быть 0 или отрицательным. " +
                    "Переданное количество строк = " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы нне может быть 0 или отрицательным. " +
                    "Переданное количество столбцов = " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
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

        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы не может быть 0");
        }

        int maxColumnLength = 0;

        for (double[] row : array) {
            maxColumnLength = Math.max(row.length, maxColumnLength);
        }

        rows = new Vector[maxColumnLength];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxColumnLength, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new NullPointerException("Массив векторов не может быть null");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы не может быть 0");
        }

        int maxColumnLength = 0;

        for (Vector row : vectors) {
            maxColumnLength = Math.max(row.getSize(), maxColumnLength);
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxColumnLength);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        checkRowIndex(rowIndex);

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector row) {
        checkRowIndex(rowIndex);

        if (getColumnsCount() != row.getSize()) {
            throw new IllegalArgumentException("Размерность передаваемой строки не равна количеству столбцов матрицы." +
                    " Количество столбцов матрицы " + getColumnsCount() + " Размерность передаваемой строки " + row.getSize());
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

        if (getRowsCount() != column.getSize()) {
            throw new IllegalArgumentException("Размерность передаваемого столбца не равна количеству строк матрицы. " +
                    "Количество строк матрицы " + rows.length + " Размерность передаваемого столбца " + column.getSize());
        }

        for (int i = 0; i < column.getSize(); i++) {
            rows[i].setElement(columnIndex, column.getElement(i));
        }
    }

    public void transpose() {
        Vector[] columns = new Vector[rows[0].getSize()];

        for (int i = 0; i < rows[0].getSize(); i++) {
            columns[i] = getColumn(i);
        }

        rows = columns;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new IllegalStateException("Нельзя вычислить определитель для матрицы которая не является квадратной." +
                    " Переданная матрица: количество строк = " + getRowsCount() + ", количество столбцов = "
                    + getColumnsCount());
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
            stringBuilder
                    .append(row)
                    .append(',');
        }

        stringBuilder
                .delete(stringBuilder.length() - 1, stringBuilder.length())
                .append('}');

        return stringBuilder.toString();
    }

    public void print() {
        System.out.print('{');
        int rowsCount = rows.length - 1;

        for (int i = 0; i < rowsCount; i++) {
            System.out.println(rows[i]);
        }

        System.out.print(rows[rows.length - 1]);
        System.out.print('}');
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размерность передаваемого вектора не равна количеству строк матрицы. " +
                    "Количество строк = " + getRowsCount() + ". Размерность передаваемого вектора = " + vector.getSize());
        }

        Vector resultVector = new Vector(getColumnsCount());

        for (int i = 0; i < rows.length; i++) {
            resultVector.setElement(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        checkMatricesSizesEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesSizesEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizesEquality(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizesEquality(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Количество строк первой матрицы не совпадает с количеством строк второй." +
                    " Количество строк первой матрицы = " + matrix1.getRowsCount() +
                    " Количество строк второй матрицы = " + matrix2.getRowsCount());
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы не совпадает с количеством столбцов второй." +
                    " Количество столбцов первой матрицы = " + matrix1.getRowsCount() +
                    " Количество столбцов второй матрицы = " + matrix2.getRowsCount());
        }

        Matrix resultMatrix = new Matrix(Math.max(matrix1.getRowsCount(), matrix2.getRowsCount()),
                Math.max(matrix1.getColumnsCount(), matrix2.getColumnsCount()));

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector resultColumn = new Vector(resultMatrix.getColumnsCount());

            for (int j = 0; j < matrix1.getColumnsCount(); j++) {
                resultColumn.setElement(j, Vector.getScalarProduct(matrix1.getColumn(i), matrix2.getRow(j)));
            }

            resultMatrix.setColumn(i, resultColumn);
        }

        return resultMatrix;
    }

    private void checkRowIndex(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException("Переданный индекс строки не может быть отрицательным, больше или равным " +
                    "количества строк матрицы. Переданный индекс = " + rowIndex + ". Допустимые границы от 0 до "
                    + (rows.length - 1));
        }
    }

    private void checkColumnIndex(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Переданный индекс столбца не может быть отрицательным, больше или равным " +
                    "количества столбцов матрицы. Переданный индекс = " + columnIndex + ". Допустимые границы от 0 до "
                    + (getColumnsCount() - 1));
        }
    }

    private static void checkMatricesSizesEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Размеры матриц не совпадают. Исходная матрица: количество строк = " +
                    matrix1.getRowsCount() + ", количество столбцов = " + matrix1.getColumnsCount() +
                    " Переданная матрица: количество строк = " + matrix2.getRowsCount() + ", количество столбцов = "
                    + matrix2.getColumnsCount());
        }
    }
}