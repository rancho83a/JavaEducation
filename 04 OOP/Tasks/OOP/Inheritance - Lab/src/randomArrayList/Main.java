package randomArrayList;

public class Main {
    public static void main(String[] args) {


        RandomArrayList<Person> listPerson = new RandomArrayList<>();

        listPerson.add(new Employee("r","r", "y"));
        listPerson.add(new Employee("ff","r", "y"));
        listPerson.add(new Employee("gg","r", "y"));

        System.out.println(listPerson.getRandomElement().getName());


    }
}
