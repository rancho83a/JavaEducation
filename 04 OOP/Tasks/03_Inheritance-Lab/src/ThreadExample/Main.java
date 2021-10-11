package ThreadExample;

public class Main {
    public static void main(String[] args) {

        Thread thread = new Thread(Main::printSomething);
        thread.start();

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("working main tread");

        }
    }

    public static void printSomething() {
        int count = 0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("async thread second: "+count++);
            if (count == 59) {
                count = 0;
            }
        }
    }
}
