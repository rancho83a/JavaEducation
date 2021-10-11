package DIP.data;

import DIP.bussiness.Employee;
import DIP.bussiness.EmployeeDataSource;

import java.util.Map;

public class InMemoryEmployeeDataSource implements EmployeeDataSource {
    private static final Map<Long, Employee> STUB_DATA_SOURCE = Map.of(
            1L, new Employee("Alice", "Sofia"),
            2L, new Employee("Bob", "Plovdiv"),
            3L, new Employee("Charlie", "Varna")
    );

    @Override
    public Employee fetchOnebyId(long id) {
        return STUB_DATA_SOURCE.get(id);
    }
}
