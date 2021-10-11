import IOUtils.ConsoleReader;
import IOUtils.InputParser;

public class Main {
    public static void main(String[] args) {
        runHoliday();
    }

    private static void runHoliday() {
        ConsoleReader reader = new ConsoleReader();
        String[] input = InputParser.parseArray(reader.readLine());

        PriceCalculator calculator =
                new PriceCalculator(Double.parseDouble(input[0]), Integer.parseInt(input[1]),
                        Season.valueOf(input[2].toUpperCase()),
                        DiscountType.valueOf(input[3].toUpperCase()));
        System.out.printf("%.2f",calculator.calculate());
    }

}
