import java.util.*;

public class JediGalaxyMy {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] size = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int rows = size[0];
        int cols = size[1];

        int[][] matrix = new int[rows][cols];

        int counter = 0;
        for (int k = 0; k < rows; k++) {
            for (int j = 0; j < cols; j++) {
                matrix[k][j] = counter++;
            }
        }

        String input;
        while (!"Let the Force be with you".equals(input = scan.nextLine())) {
            int[] ivo = Arrays.stream(input.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int ivoX = ivo[0];
            int ivoY = ivo[1];
            int[] evil = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            int evilX = evil[0];
            int evilY = evil[1];


            int y = evilY;
            int x = evilX;
            Set<Integer> reverseDiagonal = new HashSet<>();
            while (x >= 0 && y >= 0) {
                if (x < matrix.length && y < cols)
                    matrix[x][y] = 0;
                x--;
                y--;
            }


            x = ivoX;
            y = ivoY;
            int sum = 0;
            while (x >= 0 && y < cols) {

                if (x < matrix.length && y >= 0)
                    sum += matrix[x][y];
                x--;
                y++;
            }

            System.out.println(sum);
        }
    }

}