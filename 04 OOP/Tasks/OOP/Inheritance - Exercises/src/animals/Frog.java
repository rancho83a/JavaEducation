package animals;

public class Frog extends Animal {
    public Frog(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
       return "Ribbit";
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator()+this.produceSound();
    }
}
