package task03_Shop;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, Person> persons;
        Map<String, Product> products;

        try {
            persons = getPersonMap(scan);
            products = getProductMap(scan);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        String input = "";
        while (!(input = scan.nextLine()).equals("END")) {

            String[] tokens = input.split("\\s+");
            String personName = tokens[0];
            String productName = tokens[1];

            byuProduct(persons, products, personName, productName);
        }

        for (Person person : persons.values()) {
            System.out.println(person.toString());
        }
    }

    private static void byuProduct(Map<String, Person> persons, Map<String, Product> products, String personName, String productName) {
        try {
            persons.get(personName).buyProduct(products.get(productName));

            System.out.println(personName + " bought " + productName);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static Map<String, Product> getProductMap(Scanner scan) {
        Map<String, Product> products = new LinkedHashMap<>();
        String[] tokens = scan.nextLine().split(";");
        for (String token : tokens) {
            String[] productInfo = token.split("=");
            Product product = new Product(productInfo[0], Double.parseDouble(productInfo[1]));
            products.put(product.getName(), product);
        }
        return products;
    }

    private static Map<String, Person> getPersonMap(Scanner scan) {
        Map<String, Person> persons = new LinkedHashMap<>();
        String[] tokens = scan.nextLine().split(";");
        for (String token : tokens) {
            String[] personInfo = token.split("=");
            Person person = new Person(personInfo[0], Double.parseDouble(personInfo[1]));
            persons.put(person.getName(), person);
        }
        return persons;
    }
}
