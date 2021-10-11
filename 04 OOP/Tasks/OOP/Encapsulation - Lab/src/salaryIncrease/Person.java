package salaryIncrease;

import java.text.DecimalFormat;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.setAge(age);
        this.lastName = lastName;
        this.firstName = firstName;
        this.salary = salary;

    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return this.age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void increaseSalary(double bonus) {
        if (this.getAge() < 30) {
            bonus /= 2;
        }
        this.setSalary(this.getSalary()*(1+ bonus/100));
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {

        return String.format("%s %s gets %s leva.",this.getFirstName(), this.lastName,this.salary);
    }


}
