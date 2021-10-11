import DIP.bussiness.EmployeeDataSource;
import DIP.bussiness.EmployeeInfoProvider;
import DIP.bussiness.PreetyDataAppendingFormatter;
import DIP.data.InMemoryEmployeeDataSource;
import DIP.ui.ConsoleClient;
import DIP.ui.EmployeeInfoService;
import DIP.ui.Formatter;

import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        EmployeeDataSource dataSource = new InMemoryEmployeeDataSource();
        EmployeeInfoService employeeInfoService = new EmployeeInfoProvider(dataSource);
        Formatter formatter = new PreetyDataAppendingFormatter(DateTimeFormatter.ISO_TIME);
        ConsoleClient consoleClient = new ConsoleClient(employeeInfoService, formatter);

        consoleClient.printInfoFor(1);

    }
}
