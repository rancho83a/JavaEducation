package rpg_lab;

import java.util.concurrent.ThreadLocalRandom;

public class Time {
    public boolean isMorning() {
        return ThreadLocalRandom.current().nextInt(100) / 2 <= 10;
    }

}
