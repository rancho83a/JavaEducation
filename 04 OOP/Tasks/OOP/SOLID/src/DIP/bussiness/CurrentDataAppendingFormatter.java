package DIP.bussiness;

import DIP.ui.Formatter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CurrentDataAppendingFormatter implements Formatter {
    @Override
    public String format(String input) {
        return String.format("[%s] - %s", LocalDateTime.now(), input);
    }
}
