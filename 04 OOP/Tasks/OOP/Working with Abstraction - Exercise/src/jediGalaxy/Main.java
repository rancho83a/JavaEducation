package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] size = readPositions(scanner.nextLine());
        int rows = size[0];
        int cols = size[1];

        Galaxy galaxy = new Galaxy(new Field(size[0], size[1]));

        String command = scanner.nextLine();
        long starPower = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] jediPositions = readPositions(command);

            int[] sithPositions = readPositions(scanner.nextLine());
            int rowSith = sithPositions[0];
            int colSith = sithPositions[1];
            galaxy.moveSith(rowSith, colSith);

            int rowJedi = jediPositions[0];
            int colJedi = jediPositions[1];
            starPower += galaxy.moveJedi(rowJedi, colJedi);

            command = scanner.nextLine();
        }
        System.out.println(starPower);
    }

    private static int[] readPositions(String command) {
        return Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
