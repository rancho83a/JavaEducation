package DIP.bussiness;

import DIP.ui.Formatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PreetyDataAppendingFormatter implements Formatter {

    private DateTimeFormatter formatter;

     public PreetyDataAppendingFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String format(String input) {
        return String.format("[%s] - %s",
                LocalDateTime.now().format(formatter), input);
    }
}
