public class Main {
    public static void main(String[] args) {
        WeekDay monday = WeekDay.MONDAY;

        System.out.println(monday.ordinal());
        System.out.println(monday.getNumericRepresentation());

        WeekDay[] values = WeekDay.values();

        for (WeekDay value:values
             ) {
            System.out.println(value.getName());
        }

    }
}
