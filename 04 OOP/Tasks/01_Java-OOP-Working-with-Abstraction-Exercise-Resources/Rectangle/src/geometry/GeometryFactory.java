package geometry;

public class GeometryFactory {

    public static Rectangle createRectangle(int[] pointInfo) {
        return new Rectangle(createPoint(pointInfo[0], pointInfo[1]), createPoint(pointInfo[2], pointInfo[3]));
    }

    public static Point2D createPoint(int x, int y) {
        return new Point2D(x, y);
    }

    public static Point2D createPoint(int[] xy){
        if(xy.length!=2){
            throw new IllegalStateException("Points2D can be created only with X and Y");
        }
        return createPoint(xy[0], xy[1]);
    }
}
