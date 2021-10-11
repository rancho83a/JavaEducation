package animals;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setAge(age);
        this.setName(name);
        this.setGender(gender);
    }
    public abstract String produceSound();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        ValidatorData.validateName(name);
        this.name = name;
    }

    public void setAge(int age) {
        ValidatorData.validateAge(age);
        this.age = age;
    }

    public void setGender(String gender) {
        ValidatorData.validateGender(gender);
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + System.lineSeparator()+ this.name +" "+ this.age+" " +this.gender;
    }
}
