package people;

public class Employee extends Person {

    private String company;

    public Employee(String name, String address, String company) {
        super(name, address);
        this.company = company;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void sleep(){
        System.out.println("bye-bye");
    }
}
