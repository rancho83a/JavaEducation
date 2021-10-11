package classBox01;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length cannot be zero or negative.");
        }
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height cannot be zero or negative.");
        }
        this.height = height;
    }

    public double calculateSurfaceArea (){
        return 2*this.getLength()*this.getWidth()+this.calculateLateralSurfaceArea();
    }
    public  double calculateLateralSurfaceArea (){
        return 2*this.getHeight()*(this.getLength()+this.getWidth());
    }

    public double calculateVolume (){
        return this.getWidth()*this.getLength()*this.getHeight();
    }

    @Override
    public String toString() {
        return String.format(
                "Surface Area - %.2f%n"+
                        "Lateral Surface Area - %.2f%n"+
                        "Volume - %.2f%n",
                this.calculateSurfaceArea(), this.calculateLateralSurfaceArea(),this.calculateVolume());
    }
}
