package customORMDemo.entity;

import OrmFramework.annotation.Column;
import OrmFramework.annotation.Entity;
import OrmFramework.annotation.Id;

@Entity(tableName = "Employees")
public class Employee {
    @Id
    private int employeeId;

    @Column(name="employee_salary", columnDefinition = "DECIMAL(19,4)")
    private double salary;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
