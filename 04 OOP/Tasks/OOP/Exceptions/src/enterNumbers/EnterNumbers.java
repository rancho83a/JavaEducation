package enterNumbers;
;

import java.util.Scanner;

public class EnterNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean succeed = false;

        while (!succeed) {
            try {
                succeed = printRangeOfTwoNumbers(scan);
            } catch (IlligalInputNumbers iin) {
                System.out.println(iin.getMessage());
            }
        }
    }
    private static boolean printRangeOfTwoNumbers(Scanner scan) {
        try {
            int[] numbers = readTwoNumbers(scan);
            printNumbers(numbers[0], numbers[1]);
            return true;
        } catch (NumberFormatException nfe) {
            throw new IlligalInputNumbers("non-number text is entered " + nfe.getMessage() + "; Enter new number:");
            //System.out.println("non-number text is entered " + nfe.getMessage() + "; Enter new number:");
        }
    }

    private static int[] readTwoNumbers(Scanner scan) {

        int[] numbers = new int[2];
        numbers[0] = readSingleNumber(scan);
        numbers[1] = readSingleNumber(scan);
        return numbers;
    }

    private static int readSingleNumber(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printNumbers(int start, int end) {
        if (start<=1 || end>=100 || end<=start){
            throw new IlligalInputNumbers ("invalid number");
       }
        for (int i = start; i <= end; i++) {
            System.out.println(i);
        }
    }
}
