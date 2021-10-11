package wildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Animal> animals = new ArrayList<>();
        String inputLine;
        while (!"End".equals(inputLine = scan.nextLine())) {

            Animal animal = createAnimal(inputLine);
            animals.add(animal);

            inputLine = scan.nextLine();

            String foodType = inputLine.split(" ")[0];
            Integer quantity = Integer.parseInt(inputLine.split(" ")[1]);
            Food food = foodType.equals("Vegetable") ? new Vegetable(quantity) : new Meat(quantity);

            animal.makeSound();

            animal.eatFood(food);
        }
        for (Animal animal : animals) {
            System.out.println(animal.toString().trim());
        }
    }

    private static Animal createAnimal(String inputLine) {
        String[] tokens = inputLine.split("\\s+");
        String animalType = tokens[0];
        String animalName = tokens[1];
        Double animalWeight = Double.parseDouble(tokens[2]);
        String animalLivingRegion = tokens[3];
        Animal animal;
        switch (animalType) {
            case "Cat":
                String breed = tokens[4];
                animal = new Cat(animalName, animalType, animalWeight, animalLivingRegion, breed);
                break;
            case "Tiger":
                animal = new Tiger(animalName, animalType, animalWeight, animalLivingRegion);
                break;
            case "Zebra":
                animal = new Zebra(animalName, animalType, animalWeight, animalLivingRegion);
                break;
            default:
                animal = new Mouse(animalName, animalType, animalWeight, animalLivingRegion);
                break;
        }
        return animal;
    }
}

