package shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public Double calculatePerimeter() {
        if(super.getPerimeter()!=null){
            return super.getPerimeter();
        }
        Double perim = 2*(this.height+this.width);
        super.setPerimeter(perim);
       return super.getPerimeter();
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    @Override
   public Double calculateArea() {
        if (this.getArea() != null) {
            return super.getArea();
        }
        Double area = this.height*this.width;
        super.setArea(area);
        return getArea();

    }
}