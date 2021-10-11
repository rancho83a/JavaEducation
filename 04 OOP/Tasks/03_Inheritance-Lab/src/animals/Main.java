package animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = "";

        while (!"Beast!".equals(input = scan.nextLine())) {
            String info[] = scan.nextLine().split("\\s+");
            String name = info[0];
            String gender = info[2];
            int age = Integer.parseInt(info[1]);

            Animal animal = null;
            try {

                switch (input) {
                    case "Dog":
                        animal = new Dog(name, age, gender);
                        break;
                    case "Cat":
                        animal = new Cat(name, age, gender);
                        break;
                    case "Kitten":
                        animal = new Kitten(name, age);
                        break;
                    case "Tomcat":
                        animal = new Tomcat(name, age);
                        break;
                    case "Frog":
                        animal = new Frog(name, age, gender);
                        break;
                }
                System.out.println(animal.toString());
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
