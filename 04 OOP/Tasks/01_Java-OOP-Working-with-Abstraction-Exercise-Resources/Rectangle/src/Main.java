import IOUtils.ConsoleReader;
import IOUtils.InputParser;
import geometry.GeometryFactory;
import geometry.Point2D;
import geometry.Rectangle;

public class Main {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();

        int[] rectangleInfo = InputParser.parseArray(reader.readLine(), "\\s+");

        Rectangle rectangle = GeometryFactory.createRectangle(rectangleInfo);
        int n = Integer.parseInt(reader.readLine());

        while (n-- > 0) {
            int[] pointInfo = InputParser.parseArray(reader.readLine(),"\\s+");

            Point2D point2D = GeometryFactory.createPoint(pointInfo);

            System.out.println(rectangle.containsPoint(point2D));

        }

    }
}
