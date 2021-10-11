package sortByNameAndAge;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.setAge(age);
        this.lastName = lastName;
        this.firstName=firstName;

    }

    public void setAge(int age) {
        this.validAge(age);
        this.age = age;
    }

    private void validAge(int age) {
        if (age < 0) {
            throw new IllegalStateException(" Error Age");
        }
    }

    public int getAge() {
        return this.age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return this.getFirstName() +" "+ this.getLastName() +" is "+this.getAge()+ " years old.";
    }
}
