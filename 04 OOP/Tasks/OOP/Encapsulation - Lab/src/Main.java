import farm.Goat;
import farm.Organism;

public class Main {
    public static void main(String[] args) {
      Organism organism = new Organism(111);

      Goat goat = new Goat(33);

        System.out.println(goat.getWeight());

        goat.setWeight(88);
        System.out.println(goat.getWeight());
    }

}

