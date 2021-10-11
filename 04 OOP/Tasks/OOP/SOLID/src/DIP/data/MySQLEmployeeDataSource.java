package DIP.data;

import DIP.bussiness.Employee;
import DIP.bussiness.EmployeeDataSource;

public class MySQLEmployeeDataSource implements EmployeeDataSource {
    @Override
    public Employee fetchOnebyId(long id) {

        //connect to DB
        //perform query
        //return result
        return null;
    }
}
