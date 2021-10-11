package borderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Identifiable> dataBase = new ArrayList<>();

        String inputLine;
        while (!"End".equals(inputLine = scan.nextLine())) {

            String[] tokens = inputLine.split("\\s+");
            String nameOrModel = tokens[0];
            if (tokens.length == 2) {
                String id = tokens[1];
                dataBase.add(new Robot(nameOrModel, id));
            } else {
                int age = Integer.parseInt(tokens[1]);
                String id = tokens[2];
                dataBase.add(new Citizen(nameOrModel,age,id));
            }
        }
        String fakeId = scan.nextLine();

        for (Identifiable identifiable : dataBase) {
            if(identifiable.getId().endsWith(fakeId)){
                System.out.println(identifiable.getId());
            }

        }
    }
}
