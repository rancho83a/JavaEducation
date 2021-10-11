package shapes;

public class Circle extends Shape {
    private  Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    @Override
    public Double calculatePerimeter() {

        if (this.getPerimeter() != null) {
            return super.getPerimeter();
        }

        Double perim = 2 * Math.PI * this.radius;
        super.setPerimeter(perim);
        return getPerimeter();
    }

    @Override
    public Double calculateArea() {

        if (this.getArea() != null) {
            return super.getArea();
        }
        Double area = Math.PI * this.radius * this.radius;
        super.setArea(area);
        return getArea();
    }
}
