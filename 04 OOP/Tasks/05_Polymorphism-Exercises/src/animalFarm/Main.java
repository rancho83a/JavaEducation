package animalFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = "";
        List<Animal> data = new ArrayList<>();
        while (!"End".equals(input = scan.nextLine())) {
            Animal animal = createAnimal(input);
            animal.makeSound();

            String[] foodInfo=scan.nextLine().split("\\s+");
            Food food = foodInfo[0].equals("Meat") ?
                    new Meat(Integer.parseInt(foodInfo[1])) : new Vegetable(Integer.parseInt(foodInfo[1]));
            animal.eat(food);
            data.add(animal);

        }
        data.forEach(System.out::println);
    }

    private static Animal createAnimal(String input) {
        Animal animal = null;
        String tokens[] = input.split("\\s+");
        String type = tokens[0];
        String name = tokens[1];
        Double weight = Double.parseDouble(tokens[2]);
        String region = tokens[3];

        switch (type) {
            case "Cat":
                animal = new Cat(name, type, weight, region, tokens[4]);
                break;
            case "Tiger":
                animal = new Tiger(name, type, weight, region);
                break;

            case "Zebra":
                animal = new Zebra(name, type, weight, region);
                break;

            case "Mouse":
                animal = new Mouse(name, type, weight, region);
                break;
        }
        return animal;
    }
}
