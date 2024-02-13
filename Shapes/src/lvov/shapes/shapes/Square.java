package lvov.shapes.shapes;

public class Square implements Shape {
    private double sideLength;

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public Square (double sideLength){
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public Double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public Double getPerimeter() {
        return sideLength * 4;
    }

    public String toString(){
        return "Квадрат: сторона = " + sideLength;
    }

    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (obj == null || obj.getClass() != getClass()){
            return false;
        }

        Square square = (Square) obj;
        return sideLength == square.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }
}
