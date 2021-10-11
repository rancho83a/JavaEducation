package birthdayCelebrations;

public class Pet implements Birthable {


    public Pet(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    private String name;
    private String birthDate;

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    public String getName() {
        return name;
    }
}
