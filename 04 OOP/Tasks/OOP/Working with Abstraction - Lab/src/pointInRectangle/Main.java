package pointInRectangle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Point bottomLeft = readCoordinateAndCreatePoint(scan);
        Point topRight = readCoordinateAndCreatePoint(scan);
        
        Rectangle rectangle = new Rectangle(bottomLeft, topRight);

        int n = scan.nextInt();

        while (n-- > 0) {
            Point pointToCheck = readCoordinateAndCreatePoint(scan);
            System.out.println((rectangle.contains(pointToCheck)));
        }
    }

    private static Point readCoordinateAndCreatePoint(Scanner scan) {
        int x = scan.nextInt();
        int y = scan.nextInt();
        return new Point(x, y);
    }
}
