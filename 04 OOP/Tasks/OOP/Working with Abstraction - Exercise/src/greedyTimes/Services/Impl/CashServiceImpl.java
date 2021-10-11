package greedyTimes.Services.Impl;

import greedyTimes.Cash;
import greedyTimes.Services.CashService;

import java.util.List;

public class CashServiceImpl implements CashService {

    private final List<Cash> cash;

    public CashServiceImpl(List<Cash> cash) {
        this.cash = cash;
    }

    @Override
    public boolean doesContainsCash(String name) {
        return this.cash.stream().anyMatch(i -> i.getName().equals(name));
    }

    @Override
    public long getSummaryAmountCash() {
        return this.cash.stream().mapToLong(Cash::getAmount).sum();
    }

    @Override
    public Cash findCashByName(String name) {

        // return getCash().stream().filter(i->i.getName().equals(item)).findFirst().get();
        for (Cash cash : this.cash) {
            if(cash.getName().equals(name)){
                return cash;
            }
        }
        return null;    }
}
