package lvov.course2.vector;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть меньше или равна 0");
        }

        elements = new double[size];
    }

    public Vector(Vector vector) {
        elements = new double[vector.elements.length];
        System.arraycopy(vector.elements, 0, elements, 0, elements.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть равна 0, длина передаваемого массива 0");
        }

        elements = new double[array.length];
        System.arraycopy(array, 0, elements, 0, array.length);
    }

    public Vector(int size, double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть равна 0, длина передаваемого массива 0");
        }

        if (size < array.length) {
            throw new IllegalArgumentException("Размер передаваемого массива больше размера нового массива");
        }

        elements = new double[size];
        System.arraycopy(array, 0, elements, 0, size);
    }

    public int getSize() {
        return elements.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (double element : elements) {
            stringBuilder.append(element);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public void sum(Vector vector) {
        int minLength = elements.length;

        if (elements.length < vector.elements.length) {
            double[] newArray = new double[vector.elements.length];

            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
            minLength = elements.length;
        } else if (elements.length > vector.elements.length) {
            minLength = vector.elements.length;
        }

        for (int i = 0; i < minLength; i++) {
            elements[i] += vector.elements[i];
        }
    }

    public void subtract(Vector vector) {
        int minLength = elements.length;

        if (elements.length < vector.elements.length) {
            double[] newArray = new double[vector.elements.length];

            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
            minLength = elements.length;
        } else if (elements.length > vector.elements.length) {
            minLength = vector.elements.length;
        }

        for (int i = 0; i < minLength; i++) {
            elements[i] -= vector.elements[i];
        }
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
        if (elements.length - 1 < index) {
            throw new IllegalArgumentException("Передаваемый индекс больше длины массива");
        }

        return elements[index];
    }

    public void setElement(double element, int index) {
        if (elements.length - 1 < index) {
            throw new IllegalArgumentException("Передаваемый индекс больше длины массива");
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

        for (int i = 0; i < vector.elements.length; i++) {
            if (elements[i] != vector.elements[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;

        for (double e : elements) {
            hash = prime * hash + Double.hashCode(e);
        }

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.sum(vector2);

        return resultVector;
    }

    public static Vector getSubtract(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getScalar(Vector vector1, Vector vector2) {
        double result = 0;

        Vector vectorWithMaxLength = vector1.elements.length >= vector2.elements.length ? vector1 : vector2;
        Vector vectorWithMinLength = vector1.elements.length >= vector2.elements.length ? vector2 : vector1;

        for (int i = 0; i < vectorWithMaxLength.elements.length; i++) {
            if (vectorWithMinLength.elements.length > i) {
                result += vectorWithMaxLength.elements[i] * vectorWithMinLength.elements[i];
            } else {
                return result;
            }
        }

        return result;
    }
}