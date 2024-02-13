package lvov.shapes.shapes;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    public void setWidth(double width){
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public void setHeight(double height){
        this.height = height;
    }

    @Override
    public Double getArea() {
        return width * height;
    }

    @Override
    public Double getPerimeter() {
        return (width + height) * 2;
    }

    public String toString(){
        return "Прямоугольник: высота = " + height + ", ширина = " + width;
    }

    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (obj == null || obj.getClass() != getClass()){
            return false;
        }

        Rectangle rectangle = (Rectangle) obj;

        return width == rectangle.getWidth() && height == rectangle.getHeight();
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);

        return hash;
    }
}