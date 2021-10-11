package task04;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        RandomArrayList randomArrayList= new RandomArrayList();

        ThreadLocalRandom.current().ints(50).forEach(randomArrayList::add);

        System.out.println(randomArrayList.getRandomElement());
    }
}
