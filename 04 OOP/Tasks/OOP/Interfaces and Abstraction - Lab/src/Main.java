public class Main {
    public static void main(String[] args) {

        Printable printable = new Person("Pesho");

        printable.print();

        Printable second = new Car();
        second.print();
    }
}
