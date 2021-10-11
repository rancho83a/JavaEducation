package task04;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArrayList extends ArrayList {
    public Object getRandomElement(){
        int i = ThreadLocalRandom.current().nextInt(super.size());
        System.out.println("index= "+ i);
        return remove(i);
    }
}
