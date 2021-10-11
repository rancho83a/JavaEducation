package animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();
        String inputData;
        while (!"Beast!".equals(inputData = scan.nextLine())) {
            String animalType = inputData;
            String[] tokens = scan.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            String gender = tokens[2];
            try {
                switch (animalType) {
                    case "Dog":
                        animals.add(new Dog(name, age, gender));
                        break;
                    case "Cat":
                        animals.add(new Cat(name, age, gender));
                        break;
                    case "Frog":
                        animals.add(new Frog(name, age, gender));
                        break;
                    case "Kitten":
                        animals.add(new Kitten(name, age));
                        break;
                    case "Tomcat":
                        animals.add(new Tomcat(name, age));
                        break;
                    default:
                        throw new IllegalStateException("Invalid animal type");
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
        for (Animal animal : animals) {
            System.out.println(animal);
        }

    }
}
