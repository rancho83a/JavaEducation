package foodShortage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        Map<String, Buyer> repo = new HashMap<>();

        while (n-- > 0) {
            String[] tokens = scan.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            String idOrGroup = tokens[2];

            if (tokens.length == 4) {
                repo.put(name, new Citizen(name, age, idOrGroup, tokens[3]));
            } else {
                repo.put(name, new Rebel(name, age, idOrGroup));
            }
        }
        String nameToBuyFood;
        while (!"End".equals(nameToBuyFood = scan.nextLine())) {
            if (repo.containsKey(nameToBuyFood)) {
                repo.get(nameToBuyFood).buyFood();
            }
        }
        System.out.println(repo.values().stream().mapToInt(Buyer::getFood).sum());
    }
}
