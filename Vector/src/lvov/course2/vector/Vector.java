package lvov.course2.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть меньше или равна 0. Размер = " + size);
        }

        elements = new double[size];
    }

    public Vector(Vector vector) {
        elements = Arrays.copyOf(vector.elements, vector.elements.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть равна 0, длина передаваемого массива "
                    + array.length);
        }

        elements = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size == 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть равна 0, длина передаваемого массива "
                    + size);
        }

        elements = Arrays.copyOf(array, Math.max(size, array.length));
    }

    public int getSize() {
        return elements.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');

        for (double element : elements) {
            stringBuilder
                    .append(element)
                    .append(", ");
        }

        stringBuilder
                .delete(stringBuilder.length() - 2, stringBuilder.length())
                .append('}');

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        double[] newElements = Arrays.copyOf(elements, Math.max(elements.length, vector.elements.length));

        for (int i = 0; i < vector.elements.length; i++) {
            newElements[i] += vector.elements[i];
        }

        elements = newElements;
    }

    public void subtract(Vector vector) {
        double[] newElements = Arrays.copyOf(elements, Math.max(elements.length, vector.elements.length));

        for (int i = 0; i < vector.elements.length; i++) {
            newElements[i] -= vector.elements[i];
        }

        elements = newElements;
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= scalar;
        }
    }

    public void revert() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double squaresSum = 0;

        for (double e : elements) {
            squaresSum += e * e;
        }

        return Math.sqrt(squaresSum);
    }

    public double getElement(int index) {
        if (index >= elements.length) {
            throw new ArrayIndexOutOfBoundsException("Передаваемый индекс больше длины вектора. Переданный индекс "
                    + index + "Допустимые границы {0, " + (elements.length - 1) + "}");
        }

        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс не может быть меньше 0");
        }

        return elements[index];
    }

    public void setElement(int index, double element) {
        if (index >= elements.length) {
            throw new ArrayIndexOutOfBoundsException("Передаваемый индекс больше длины вектора. Переданный индекс "
                    + index + ". Допустимые границы {0, " + (elements.length - 1) + "}");
        }

        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Индекс не может быть меньше 0");
        }

        elements[index] = element;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) obj;

        if (elements.length != vector.elements.length) {
            return false;
        }

        return !Arrays.equals(elements, vector.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getScalarMultiply(Vector vector1, Vector vector2) {
        double result = 0;

        for (int i = 0; i < Math.min(vector1.elements.length, vector2.elements.length); i++) {
            result += vector1.elements[i] * vector2.elements[i];
        }

        return result;
    }
}