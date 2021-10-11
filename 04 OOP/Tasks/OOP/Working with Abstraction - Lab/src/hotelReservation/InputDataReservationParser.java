package hotelReservation;

public class InputDataReservationParser {

    public Reservation parseFromLine(String line){

        String tokens[] = line.split("\\s+");
        double pricePErDay = Double.parseDouble(tokens[0]);
        int numberOfDays = Integer.parseInt(tokens[1]);
        Season season = Season.valueOf(tokens[2].toUpperCase());
        DiscountType discountType = DiscountType.valueOf(tokens[3].toUpperCase());

        Reservation reservation=new Reservation(pricePErDay,numberOfDays,season,discountType);

        return reservation;
    }

//    public static void main(String[] args) {
//     InputDataReservationParser parser = new InputDataReservationParser();
//     String input = "40 10 Autumn VIP";
//        Reservation reservation = parser.parseFromLine(input);
//        System.out.println(reservation.toString());
//    }
}
