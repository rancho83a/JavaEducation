package birthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String inputLine;

        List<Birthable> birthableList = new ArrayList<>();
        while (!"End".equals(inputLine = scan.nextLine())) {

            String[] tokens = inputLine.split("\\s+");
            String name = tokens[1];


            if (tokens[0].equals("Citizen")) {
                int age = Integer.parseInt(tokens[2]);
                String id = tokens[3];
                String birthDate = tokens[4];
                birthableList.add(new Citizen(name,age,id,birthDate));
            } else if(tokens[0].equals("Pet")){
                String birthDate = tokens[2];
                birthableList.add(new Pet(name,birthDate));
            }
        }
        String year =scan.nextLine();

        for (Birthable birthable : birthableList) {
            if(birthable.getBirthDate().endsWith(year)){
                System.out.println(birthable.getBirthDate());
            }
        }

    }
}
