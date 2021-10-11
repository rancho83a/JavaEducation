package hotelReservation;

import java.io.PrintStream;

public class Printer {
    private PrintStream out;

    public Printer(PrintStream out) {
        this.out = out;
    }

    public void printDoubleRounded(double dbl) {
        String rounderDbl=String.format("%.2f", dbl);
        out.println(rounderDbl);
    }
    public void printLine(String line){
        out.println(line);
    }
}
