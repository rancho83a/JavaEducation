package task01Vehicles;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Vehicle> data = new HashMap<>();

        String[] tokens = scan.nextLine().split("\\s+");
        data.put("Car", new Car(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));
         tokens = scan.nextLine().split("\\s+");
        data.put("Truck", new Truck(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));

        int n = Integer.parseInt(scan.nextLine());

        while(n-->0){
            tokens = scan.nextLine().split("\\s+");
            double param = Double.parseDouble(tokens[2]);
            switch(tokens[0]){
                case "Drive":
                        System.out.println(data.get(tokens[1]).drive(param));
                    break;
                case "Refuel":
                    data.get(tokens[1]).refuel(param);
                    break;
            }
        }

        data.values().forEach(v-> System.out.println(v.toString()));
    }
}
