import people.Employee;
import people.Person;
import people.Student;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person p = new Person("gg", "Egg");

        p.setName("SSS");
        p.setAddress("Street");

        Employee employee = new Employee("COCA", "str", "Cola");
        Employee employee2 = new Employee("COCA", "str", "Fanta");


        employee.setName("KKK");
        employee.setAddress("rr");
        Student student = new Student("DD", "str", "sch");

        List<Person> list = new ArrayList<>();

        list.add(employee);
        list.add(employee2);
        list.add(student);

        for (Person person : list) {
            if (person instanceof Employee) {
                Employee e = (Employee) person;

                System.out.println(e.getCompany());
            }
        }
    }
}
