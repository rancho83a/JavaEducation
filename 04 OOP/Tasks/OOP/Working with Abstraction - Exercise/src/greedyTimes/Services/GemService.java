package greedyTimes.Services;

import greedyTimes.Gem;

public interface GemService {
    boolean doesContainsGem(String name);
    long getSummaryAmountGem();
    Gem findGemByName(String name);


}
