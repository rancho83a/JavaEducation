import java.util.Scanner;

public class RhombusofStars {
    public static void main(String[] args) {
        int n= readN(new Scanner((System.in)));

        for (int i = 1; i <=n ; i++) {
            printLine(n, i);
        }
        for (int i = n-1; i >=1; i--) {
            printLine(n, i);
        }
    }

    private static void printLine(int numberOfStars, int starsToPrint) {
        String line = buildStr(" ", numberOfStars - starsToPrint) + buildStr("* ", starsToPrint);
        System.out.println(line);
    }

    private static int readN(Scanner scanner) {
        return scanner.nextInt();
    }

    private static String buildStr(String delim, int size) {
        StringBuilder str= new StringBuilder();
        for (int j = 1; j <=size ; j++) {
            str.append(delim);
        }
        return str.toString();
    }
}
