package task01_Box;

public class Box {
    private double length;
    private double width;
    private double height;


    public Box(double length, double width, double height) {
        setLength(length);
        setWidth(width);
        setHeight(height);
    }

    public double getLength() {
        return length;
    }

    private void setLength(double length) {
        validation(length, "Length");
        this.length = length;
    }

    private void validation(double size, String param) {
        if (size <= 0) {
            throw new IllegalArgumentException(String.format("%s cannot be zero or negative.", param));
        }
    }

    public double getWidth() {
        return width;
    }

    private void setWidth(double width) {
        validation(width, "Width");

        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    private void setHeight(double height) {
        validation(height, "Height");
        this.height = height;
    }


    public double calculateSurfaceArea() {
        return 2 * (this.length * this.width + this.length * this.height + this.height * this.width);
    }

    public double calculateLateralSurfaceArea() {
        return 2 * this.height * (this.width + this.length);
    }

    public double calculateVolume() {
        return this.height * this.width * this.length;
    }
}
