package shoppingSpree03TempRename;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, Person> persons = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();

        for (String p : scan.nextLine().split(";")) {

            String name = p.substring(0, p.indexOf("="));
            double money = Double.parseDouble(p.substring(p.indexOf("=") + 1));
            persons.put(name, new Person(name, money));
        }

        for (String p : scan.nextLine().split(";")) {

            String name = p.substring(0, p.indexOf("="));
            double money = Double.parseDouble(p.substring(p.indexOf("=") + 1));
            products.put(name, new Product(name, money));
        }
        String command;
        while (!"END".equals(command = scan.nextLine())) {
            String name = command.split("\\s+")[0];
            String product = command.split("\\s+")[1];
            if (persons.containsKey(name) && products.containsKey(product)) {
                try {
                    persons.get(name).buyProduct(products.get(product));
                } catch (IllegalStateException ise) {
                    System.out.println(ise.getMessage());
                }
            }
        }

        persons.forEach((key, value) -> System.out.println(value.toString()));
    }

    public static <V> Map<String, V> createMap(String inputData) {
        Map<String, V> map = new LinkedHashMap<>();

        return map;
    }
}
