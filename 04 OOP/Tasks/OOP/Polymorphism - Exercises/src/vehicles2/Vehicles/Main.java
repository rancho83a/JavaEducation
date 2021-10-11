package vehicles2.Vehicles;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, Vehicles> vehiclesByType = new HashMap<>();
        String[] tokens = scan.nextLine().split("\\s+");
        vehiclesByType.put("Car", new Car(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));

        tokens = scan.nextLine().split("\\s+");
        vehiclesByType.put("Truck", new Truck(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));

        int n = Integer.parseInt(scan.nextLine());

        while (n-- > 0) {
            tokens = scan.nextLine().split("\\s+");

            String type = tokens[1];
            double param = Double.parseDouble(tokens[2]);
            if (tokens[0].equals("Drive")) {
                System.out.println(vehiclesByType.get(type).drive(param));
            } else {
                vehiclesByType.get(type).refuel(param);
            }
        }
        for (Vehicles vehicle : vehiclesByType.values()) {
            System.out.println(vehicle);
        }
    }
}
