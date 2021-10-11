package hotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputDataReservationParser parser = new InputDataReservationParser();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Reservation reservation = parser.parseFromLine(input);

        double price = reservation.calculatedPrice();

        Printer printer = new Printer(System.out);
        printer.printDoubleRounded(price);
      //  printer.printLine(String.valueOf(price));
    }
}
