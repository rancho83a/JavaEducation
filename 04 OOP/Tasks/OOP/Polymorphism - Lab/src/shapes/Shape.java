package shapes;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    public Double getArea() {
        return area;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    protected void setArea(Double area) {
        this.area = area;
    }

    protected void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public abstract Double calculatePerimeter();
    public abstract Double calculateArea();
}
