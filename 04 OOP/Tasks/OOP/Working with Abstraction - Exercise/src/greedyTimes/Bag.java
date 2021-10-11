package greedyTimes;

import greedyTimes.Services.CashService;
import greedyTimes.Services.GemService;
import greedyTimes.Services.Impl.CashServiceImpl;
import greedyTimes.Services.Impl.GemServiceImpl;

import java.util.*;

public class Bag {
    private Gold gold;
    private List<Gem> gems;
    private List<Cash> cash;
    private long capacity;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.cash = new ArrayList<>();
        this.gems = new ArrayList<>();
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public List<Gem> getGems() {
        return gems;
    }

    public List<Cash> getCash() {
        return cash;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public Gold getGold() {
        return gold;
    }

    public long getCapacity() {
        return capacity;
    }

    public void decreaseCapacity(long amount) {
        this.setCapacity(this.getCapacity() - amount);
    }

    public void print() {
        Map<String, Long> bag = new LinkedHashMap<>();
        GemService gemService = new GemServiceImpl(this.getGems());
        CashService cashService = new CashServiceImpl(this.getCash());
        if (this.gold != null) {
            bag.put("Gold", getGold().getAmount());
        }
        if (this.getGems().size() > 0) {
            bag.put("Gem", gemService.getSummaryAmountGem());
        }
        if (this.getCash().size() > 0) {
            bag.put("Cash", cashService.getSummaryAmountCash());
        }

        bag.entrySet().stream()
                .sorted((f, s) -> (int) (s.getValue() - f.getValue()))
                .forEach(kv -> {
                    System.out.printf("<%s> $%d%n", kv.getKey(), kv.getValue());
                    if (kv.getKey().equals("Gold")) {
                        System.out.println("##Gold - " + kv.getValue());
                    } else if (kv.getKey().equals("Gem")) {
                        this.getGems()
                                .stream()
                                .sorted((f, s) -> {
                                    int res = s.getName().compareTo(f.getName());
                                    if (res == 0) {
                                        return (int) (f.getAmount() - s.getAmount());
                                    }
                                    return res;
                                })
                                .forEach(i -> System.out.printf("##%s - %d%n", i.getName(), i.getAmount()));
                    } else {
                        this.getCash()
                                .stream()
                                .sorted((f, s) -> {
                                    int res = s.getName().compareTo(f.getName());
                                    if (res == 0) {
                                        return (int) (f.getAmount() - s.getAmount());
                                    }
                                    return res;
                                })
                                .forEach(i -> System.out.printf("##%s - %d%n", i.getName(), i.getAmount()));
                    }
                });
    }
}