package greedyTimes.Services;

import greedyTimes.Cash;
import greedyTimes.Gem;

public interface CashService {
    boolean doesContainsCash(String name);
    long getSummaryAmountCash();
    Cash findCashByName(String name);

}
