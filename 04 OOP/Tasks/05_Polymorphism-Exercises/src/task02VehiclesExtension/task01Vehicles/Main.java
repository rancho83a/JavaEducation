package task02VehiclesExtension.task01Vehicles;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Vehicle> data = new LinkedHashMap<>();

        String[] tokens = scan.nextLine().split("\\s+");
        data.put("Car", new Car(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3])));
        tokens = scan.nextLine().split("\\s+");
        data.put("Truck", new Truck(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3])));
        tokens = scan.nextLine().split("\\s+");

        data.put("Bus", new Bus(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3])));

        int n = Integer.parseInt(scan.nextLine());

        while (n-- > 0) {
            tokens = scan.nextLine().split("\\s+");
            double param = Double.parseDouble(tokens[2]);
            switch (tokens[0]) {
                case "Drive":
                    System.out.println(data.get(tokens[1]).drive(param));
                    break;
                case "Refuel":
                    data.get(tokens[1]).refuel(param);
                    break;
                case "DriveEmpty":
                    System.out.println( ((Bus)data.get("Bus")).driveEmpty(param));

                    break;

            }
        }

        data.values().forEach(v -> System.out.println(v.toString()));
    }
}
