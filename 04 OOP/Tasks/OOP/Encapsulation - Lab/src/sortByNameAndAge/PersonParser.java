package sortByNameAndAge;

public class PersonParser {
    public static Person createPerson( String line){
        String[] tokens = line.split("\\s+");
        return new Person(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
    }
}
