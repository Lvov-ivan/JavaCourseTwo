package lvov.course2.matrix;

import lvov.course2.vector.Vector;

public class Matrix {
    private Vector[] components;

    public Matrix(int verticalSize, int horizontalSize) {
        if (verticalSize == 0 || verticalSize < 0) {
            throw new IllegalArgumentException("Количество строк матрицы не может быть 0 или отрицательным. " +
                    "Переданное количество строк = " + verticalSize);
        }

        if (horizontalSize == 0 || horizontalSize < 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы нне может быть 0 или отрицательным. " +
                    "Переданное количество столбцов = " + horizontalSize);
        }

        components = new Vector[verticalSize];

        for (int i = 0; i < verticalSize; i++) {
            components[i] = new Vector(horizontalSize);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Матрица не может быть null");
        }

        components = new Vector[matrix.components.length];

        for (int i = 0; i < matrix.components.length; i++) {
            components[i] = new Vector(matrix.components[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Переданный массив не может быть null");
        }

        components = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            components[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] arrayVectors) {
        if (arrayVectors == null) {
            throw new NullPointerException("Массив векторов не может быть null");
        }

        if (arrayVectors.length == 0) {
            throw new IllegalArgumentException("Массив векторов пустой");
        }

        int maxVectorSize = 0;

        for (Vector vector : arrayVectors) {
            maxVectorSize = Math.max(vector.getSize(), maxVectorSize);
        }

        components = new Vector[arrayVectors.length];

        for (int i = 0; i < components.length; i++) {
            components[i] = new Vector(maxVectorSize);

            for (int j = 0; j < arrayVectors[i].getSize(); j++) {
                components[i].setElement(j, arrayVectors[i].getElement(j));
            }
        }
    }

    public int getVerticalSize() {
        return components.length;
    }

    public int getHorizontalSize() {
        return components[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        checkRowIndex(rowIndex);

        return new Vector(components[rowIndex]);
    }

    public void setRow(int rowIndex, Vector vector) {
        checkRowIndex(rowIndex);

        if (components[rowIndex].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("Размерность передаваемой строки не равна количеству столбцов матрицы." +
                    " Количество столбцов матрицы " + components[0].getSize() + " Длина передаваемой строки " + vector.getSize());
        }

        if (vector.getSize() == 0) {
            throw new IllegalArgumentException("Присваиваемая строка пустая. Размерность строки = " + vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            components[rowIndex].setElement(i, vector.getElement(i));
        }
    }

    public Vector getColumn(int columnIndex) {
        checkColumnIndex(columnIndex);

        Vector column = new Vector(components.length);

        for (int i = 0; i < components.length; i++) {
            column.setElement(i, components[i].getElement(columnIndex));
        }

        return column;
    }

    public void setColumn(int columnIndex, Vector column) {
        checkColumnIndex(columnIndex);

        if (components[columnIndex].getSize() != column.getSize()) {
            throw new IllegalArgumentException("Размерность передаваемого столбца не равна количеству строк матрицы. " +
                    "Количество строк матрицы " + components.length + " Размерность передаваемого столбца " + column.getSize());
        }

        if (column.getSize() == 0) {
            throw new IllegalArgumentException("Присваиваемый столбец пустой. Размерность столбца = " + column.getSize());
        }

        for (int i = 0; i < column.getSize(); i++) {
            components[i].setElement(columnIndex, column.getElement(i));
        }
    }

    public void transpose() {
        double[][] valuesArray = new double[components.length][components[0].getSize()];

        for (int i = 0; i < valuesArray.length; i++) {
            for (int j = 0; j < valuesArray[0].length; j++) {
                valuesArray[j][i] = components[i].getElement(j);
            }
        }

        Matrix newMatrix = new Matrix(valuesArray);
        components = newMatrix.components;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : components) {
            for (int j = 0; j < components[0].getSize(); j++) {
                vector.setElement(j, vector.getElement(j) * scalar);
            }
        }
    }

    public double getDeterminant() {
        if (components.length != components[0].getSize()) {
            throw new IllegalArgumentException("Нельзя вычислить детерминант для матрицы которая не является квадратной." +
                    " Переданная матрица: высота = " + components.length + ", длина = " + components[0].getSize());
        }

        if (components.length == 1) {
            return components[0].getElement(0);
        }

        if (components.length == 2) {
            return components[0].getElement(0) * components[1].getElement(1) - components[0].getElement(1)
                    * components[1].getElement(0);
        }

        double result = 0;

        for (int i = 0; i < components.length; i++) {
            Matrix minor = new Matrix(components.length - 1, components.length - 1);

            for (int j = 1; j <= minor.components.length; j++) {
                for (int k = 0, insertIndex = 0; k <= minor.components.length; k++) {
                    if (k == i) {
                        continue;
                    }

                    minor.components[j - 1].setElement(insertIndex, components[j].getElement(k));
                    insertIndex++;
                }
            }

            result += Math.pow(-1, i) * components[0].getElement(i) * minor.getDeterminant();
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (Vector component : components) {
            stringBuilder.append('{');

            for (int j = 0; j < components[0].getSize(); j++) {
                stringBuilder
                        .append(component.getElement(j))
                        .append(", ");
            }

            stringBuilder
                    .delete(stringBuilder.length() - 2, stringBuilder.length())
                    .append("}, ");
        }

        stringBuilder
                .delete(stringBuilder.length() - 4, stringBuilder.length())
                .append('}');

        return stringBuilder.toString();
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (Vector component : components) {
            stringBuilder.append('{');

            for (int j = 0; j < components[0].getSize(); j++) {
                stringBuilder
                        .append(component.getElement(j))
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

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() > components.length) {
            throw new IllegalArgumentException("Размерность передаваемого вектора больше высоты матрицы. " +
                    "Размерность передаваемого вектора = " + vector.getSize());
        }

        Vector newVector = new Vector(vector.getSize());

        for (int i = 0; i < components.length; i++) {
            double sumRow = 0;
            for (int j = 0; j < components[0].getSize(); j++) {
                sumRow += components[i].getElement(j) * vector.getElement(j);
            }
            newVector.setElement(i, sumRow);
        }

        return newVector;
    }

    public void add(Matrix matrix) {
        checkMatrixSize(this, matrix);

        for (int i = 0; i < components.length; i++) {
            for (int j = 0; j < components[0].getSize(); j++) {
                components[i].setElement(j, components[i].getElement(j) + matrix.components[i].getElement(j));
            }
        }
    }

    public void subtract(Matrix matrix) {
        checkMatrixSize(this, matrix);

        for (int i = 0; i < components.length; i++) {
            for (int j = 0; j < components[0].getSize(); j++) {
                components[i].setElement(j, components[i].getElement(j) - matrix.components[i].getElement(j));
            }
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatrixSize(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatrixSize(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getMultiplyMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.components.length != matrix2.components[0].getSize()) {
            throw new IllegalArgumentException("Количество строк первой матрицы не совпадает с количеством столбцов второй." +
                    " Количество строк в первой матрице = " + matrix1.components.length +
                    " Количество столбцов второй матрицы = " + matrix2.components[0].getSize());
        }

        Matrix resultMatrix = new Matrix(matrix1.components.length, matrix1.components[0].getSize());

        for (int i = 0; i < matrix1.components.length; i++) {
            resultMatrix.setColumn(i, matrix1.multiplyByVector(matrix2.getColumn(i)));
        }

        return resultMatrix;
    }

    private void checkRowIndex(int rowIndex) {
        if (rowIndex < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным. Передаваемый индекс = " + rowIndex);
        }

        if (rowIndex > components.length) {
            throw new IndexOutOfBoundsException("Индекс строки не может быть больше количества строк матрицы. Допустимые" +
                    " границы " + "от 0 до " + (components.length - 1) + " Передаваемый индекс = " + rowIndex);
        }
    }

    private void checkColumnIndex(int columnIndex) {
        if (columnIndex < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным. Передаваемый индекс = " + columnIndex);
        }

        if (columnIndex > components.length) {
            throw new IndexOutOfBoundsException("Индекс колонки не может быть больше количества столбцов матрицы. Допустимые" +
                    " границы от 0 до " + (components[0].getSize() - 1) + " Передаваемый индекс = " + columnIndex);
        }
    }

    private static void checkMatrixSize(Matrix matrix1, Matrix matrix2) {
        if (matrix1.components.length != matrix2.components.length || matrix1.components[0].getSize() != matrix2.components[0].getSize()) {
            throw new IllegalArgumentException("Размеры матриц не совпадают " + "Исходная матрица: " +
                    "количество строк = " + matrix1.components.length + ", количество столбцов = " + matrix1.components[0].getSize() +
                    " Переданная матрица: количество строк = " + matrix2.components.length + ", количество столбцов = "
                    + matrix2.components[0].getSize());
        }
    }
}