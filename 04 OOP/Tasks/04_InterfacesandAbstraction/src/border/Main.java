package border;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input="";
        List<Identifiable> data =new ArrayList<>();

        while (!"End".equals(input=scan.nextLine())){
            String[] tokens=input.split("\\s+");
            if(tokens.length==3){

                data.add(new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
            } else {
                data.add(new Robot(tokens[0], tokens[1]));
            }
        }
        String fakeId = scan.nextLine();
        for (Identifiable item : data) {
            if(item.getId().endsWith(fakeId)){
                System.out.println(item.getId());
            }
        }
    }
}
