package foodShortage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Buyer> data = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        while (n-- > 0) {
            String[] tokens = scan.nextLine().split("\\s+");
            if (tokens.length == 4) {
                data.putIfAbsent(tokens[0], new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3]));
            } else {
                data.putIfAbsent(tokens[0], new Rebel(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
            }
        }
        String input = "";
        while (!"End".equals(input = scan.nextLine())){
            if(data.containsKey(input)){
                data.get(input).buyFood();
            }
        }
        int sum = data.values().stream().mapToInt(Buyer::getFood).sum();
        System.out.println(sum);
    }
}
