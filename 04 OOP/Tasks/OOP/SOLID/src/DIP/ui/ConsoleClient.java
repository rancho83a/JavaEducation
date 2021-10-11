package DIP.ui;

public class  ConsoleClient {

    private EmployeeInfoService employeeInfoService;
    private Formatter formatter;

    public ConsoleClient(EmployeeInfoService employeeInfoService, Formatter formatter){

        this.employeeInfoService = employeeInfoService;
        this.formatter = formatter;
    }

    public void printInfoFor(long id){
       String employeeInfo= employeeInfoService.fetchEmployeeNameById(id);
        String formattedInfo = formatter.format(employeeInfo);
        System.out.println(formattedInfo);
    }
}
