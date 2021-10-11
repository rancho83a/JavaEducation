package Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] tokens = scan.nextLine().split("\\s+");
        Car car = new Car(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));

        tokens = scan.nextLine().split("\\s+");
        Truck truck = new Truck(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));

        int n = Integer.parseInt(scan.nextLine());

        while (n-- > 0) {
            tokens = scan.nextLine().split("\\s+");
            if (tokens[0].equals("Drive")) {
                if (tokens[1].equals("Car")) {
                    System.out.println(car.drive(Double.parseDouble(tokens[2])));
                } else {
                    System.out.println(truck.drive(Double.parseDouble(tokens[2])));
                }

            } else {
                if (tokens[1].equals("Car")) {
                    car.refuel(Double.parseDouble(tokens[2]));
                } else {
                    truck.refuel(Double.parseDouble(tokens[2]));
                }

            }
        }
        System.out.println(car);
        System.out.println(truck);
    }
}
