package DIP.bussiness;

import DIP.ui.EmployeeInfoService;

public class EmployeeInfoProvider implements EmployeeInfoService {

    private EmployeeDataSource dataSource;

    public EmployeeInfoProvider (EmployeeDataSource dataSource){

        this.dataSource = dataSource;
    }
    @Override
    public String fetchEmployeeNameById(long id) {
        Employee employee=dataSource.fetchOnebyId(id);
        return employee.getName();
    }
}
