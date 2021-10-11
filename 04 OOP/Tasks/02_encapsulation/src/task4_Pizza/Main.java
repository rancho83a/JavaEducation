package task4_Pizza;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] tokens = scan.nextLine().split("\\s+");
        try {
            Pizza pizza = new Pizza(tokens[1], Integer.parseInt(tokens[2]));

            tokens = scan.nextLine().split("\\s+");
            Dough dough = new Dough(tokens[1], tokens[2], Double.parseDouble(tokens[3]));
            pizza.setDough(dough);

            String input="";
            while(!(input=scan.nextLine()).equals("END")){
                tokens = input.split("\\s+");
                pizza.addTopping(new Topping(tokens[1], Double.parseDouble(tokens[2])));
            }
            System.out.println(pizza.toString());


        } catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }

    }
}
