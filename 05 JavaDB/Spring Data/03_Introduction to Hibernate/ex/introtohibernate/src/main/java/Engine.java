import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Engine implements Runnable {

    final private BufferedReader reader;
    final private EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {

        while (true) {
            System.out.println("\nEnter task Number: from 2 to 13 are allowed. (For escape APP press number 1): ");
            try {
                String input = reader.readLine();
                if (input.equals("1")) {
                    System.out.println("GOOD LUCK!");
                    break;
                }
                switch (Integer.parseInt(input)) {
                    case 2 -> ex2();
                    case 3 -> ex3();
                    case 4 -> ex4();
                    case 5 -> ex5();
                    case 6 -> ex6();
                    case 7 -> ex7();
                    case 8 -> ex8();
                    case 9 -> ex9();
                    case 10 -> ex10();
                    case 11 -> ex11();
                    case 12 -> ex12();
                    case 13 -> ex13();


                    default -> System.out.println("*****WARNING*****\nPlease enter correct task number.");
                }
            } catch (Exception e) {
                System.out.println("*****WARNING*****\nPlease enter correct data.");
                e.printStackTrace();
            }
        }
    }

    private void ex11() throws IOException {
        System.out.println("Enter the pattern for first name");
        String pattern = reader.readLine().toLowerCase();
        List<Employee> rs = entityManager
                .createQuery("SELECT e FROM Employee e ", Employee.class)

                .getResultList();

        rs.stream()
                .filter(e -> e.getFirstName().toLowerCase().startsWith(pattern))
                .forEach(e -> System.out.printf("%s %s - %s - ($%s)%n",
                        e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));

        System.out.println();


    }

    private void ex13() throws IOException {
        System.out.println("Enter town name:");
        String townName = reader.readLine();
        Town town = entityManager.createQuery("SELECT t FROM Town  t WHERE t.name=:t_name", Town.class)
                .setParameter("t_name", townName)
                .getSingleResult();

        int affectedItems = removeAddressesByTownId(town.getId());
        entityManager.getTransaction().begin();


        entityManager.remove(town);

        entityManager.getTransaction().commit();
        System.out.printf("%d address in %s deleted", affectedItems, townName);

    }

    private int removeAddressesByTownId(Integer id) {
        List<Address> rs = entityManager.createQuery("SELECT a FROM Address a WHERE a.town.id=:t_id", Address.class)
                .setParameter("t_id", id)
                .getResultList();

        entityManager.getTransaction().begin();

        rs.forEach(entityManager::remove);

        entityManager.getTransaction().commit();
        return rs.size();

    }

    private void ex12() {
        List<Object[]> rs = entityManager.createNativeQuery("SELECT d.name, max(e.salary) as m_salary  from employees as e\n" +
                "JOIN departments d on d.department_id = e.department_id\n" +
                "GROUP by d.name\n" +
                "HAVING m_salary NOT BETWEEN 30000 AND 70000;")
                .getResultList();
        for (Object[] row : rs) {
            for (int i = 0; i < row.length; i = i + 2) {
                System.out.println(row[i] + " " + row[i + 1]);
            }
        }


    }

    private void ex10() {

        entityManager.getTransaction().begin();
        entityManager
                .createQuery("update  Employee e SET e.salary = e.salary*1.12 WHERE e.department.id IN :ids")
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .executeUpdate();

        entityManager.getTransaction().commit();



        entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.department.id IN :ids", Employee.class)
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .getResultList()
                .forEach(e -> System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary()));
    }

    private void ex9() {
        entityManager.createQuery("SELECT p FROM Project p " +
                "ORDER BY  p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream().sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("Project name: %s%n" +
                                "       Project Description: %s%n" +
                                "       Project Start Date:%s%n" +
                                "       Project End Date: %s%n",
                        p.getName(), p.getDescription(),
                        p.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        p.getEndDate() == null ? null : p.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                ));
    }

    private void ex8() throws IOException {
        System.out.println("Enter employee id:");
        int id = Integer.parseInt(reader.readLine());
        Employee e = entityManager.find(Employee.class, id);
        System.out.printf("%s %s - %s%n", e.getFirstName(), e.getLastName(), e.getJobTitle());
        e.getProjects()
                .stream().sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("      %s%n", p.getName()));
    }

    private void ex7() {
        List<Address> rs = entityManager
                .createQuery("SELECT a FROM Address a ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        rs.stream()
                .forEach(e -> System.out.printf("%s, %s - %d employees%n",
                        e.getText(), e.getTown() == null ? "Unknown" : e.getTown().getName(), e.getEmployees().size()));

    }

    private void ex6() throws IOException {
        System.out.println("Enter employee last name:");
        String lastName = reader.readLine();

        Employee e = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.lastName=:l_name", Employee.class)
                .setParameter("l_name", lastName)
                .getSingleResult();

        Address address = createAddress("Vitoshka 15");
        entityManager.getTransaction().begin();
        e.setAddress(address);
        entityManager.getTransaction().commit();
    }

    private Address createAddress(String addressText) {

        Address address = new Address();
        address.setText(addressText);
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        return address;
    }

    private void ex5() {

        entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.department.name=:dep_name ORDER BY e.salary, e.id ", Employee.class)
                .setParameter("dep_name", "Research and Development")
                .getResultStream()
                .forEach(e -> System.out.printf(String.format("%s %s from Research and Development - $%.2f%n",
                        e.getFirstName(), e.getLastName(), e.getSalary())));
    }

    private void ex4() {
        entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.salary>:min_salary", Employee.class)
                .setParameter("min_salary", BigDecimal.valueOf(50000L))
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

    private void ex3() throws IOException {
        System.out.println("Enter employee full name:");
        String[] tokens = reader.readLine().split("\\s+");
        String firstName = tokens[0];
        String lastName = tokens[1];
        Long sg = entityManager.createQuery("SELECT count(e) FROM Employee e WHERE e.firstName=:f_name AND e.lastName=:l_name", Long.class)
                .setParameter("f_name", firstName)
                .setParameter("l_name", lastName)
                .getSingleResult();

        System.out.println("------------------");

        System.out.println(sg == 0 ? "No" : "Yes");
        System.out.println("------------------");
    }

    private void ex2() {

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("update Town t SET t.name=upper(t.name) WHERE length(t.name)<=5 ");

        System.out.println(query.executeUpdate() + " items are effected");
        entityManager.getTransaction().commit();
    }
}
