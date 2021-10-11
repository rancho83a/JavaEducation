package foodShortage;


public class Citizen implements Person, Identifiable, Buyer, Birthable{

    private String name;
    private int age;
    private String id;
    private String birthDate;
    private int food;

    public Citizen(String name, int age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.id = id;
        this.food = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void buyFood() {
        this.food += 10;
    }

    @Override
    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public int getFood() {
        return this.food;
    }
}
