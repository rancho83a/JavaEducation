package greedyTimes;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private long currentTotalQuantity;
    private long gold;
    private Map<String, Long> gem;
    private Map<String, Long> cash;
    private boolean isGoldAdded;
    private boolean isGemAdded;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.gem = new HashMap<>();
        this.cash = new HashMap<>();
        this.isGoldAdded=false;
        this.isGemAdded=false;
    }


    public void addCash(String item, long quantity) {
        if (hasFreeCapacity(quantity) && getTotalCash() + quantity <= getTotalGems() && isGemAdded) {
            this.cash.putIfAbsent(item, 0L);
            this.cash.put(item, this.cash.get(item) + quantity);
            this.currentTotalQuantity += quantity;
        }
    }

    private long getTotalCash() {
        return this.cash.values().stream().mapToLong(e -> e).sum();
    }

    public void addGems(String item, long quantity) {
        if (hasFreeCapacity(quantity) && getTotalGems() + quantity <= this.gold && isGoldAdded) {
            this.gem.putIfAbsent(item, 0L);
            this.gem.put(item, this.gem.get(item) + quantity);
            this.currentTotalQuantity += quantity;
            this.isGemAdded=true;
        }
    }

    private long getTotalGems() {
        return this.gem.values().stream().mapToLong(e -> e).sum();
    }

    public void addGold(long quantity) {
        if (hasFreeCapacity(quantity)) {
            this.gold += quantity;
            this.currentTotalQuantity += quantity;
            this.isGoldAdded=true;
        }
    }

    private boolean hasFreeCapacity(long quantity) {
        return quantity + this.currentTotalQuantity <= this.capacity;
    }

    private Comparator<Map.Entry<String, Long>> getComparator() {
        return new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {

                int res = o2.getKey().compareTo(o1.getKey());
                if (res == 0) {
                    res = o1.getValue().compareTo(o2.getValue());
                }
                return res;
            }
        };
    }

    public void printContent() {
        StringBuilder sb = new StringBuilder();

        if(!isGoldAdded){
            return;
        }

        sb.append(String.format("<Gold> $%d", this.gold)).append(System.lineSeparator());
        sb.append(String.format("##Gold - %d", this.gold)).append(System.lineSeparator());

        if (!this.gem.isEmpty()) {
            sb.append(String.format("<Gem> $%d", this.getTotalGems())).append(System.lineSeparator());
            this.gem.entrySet().stream().sorted(getComparator())
                    .forEach(entry -> sb.append(String.format("##%s - %d", entry.getKey(), entry.getValue()))
                            .append(System.lineSeparator()));
        }

        if (!this.cash.isEmpty()) {
            sb.append(String.format("<Cash> $%d", this.getTotalCash())).append(System.lineSeparator());
            this.cash.entrySet().stream().sorted(getComparator())
                    .forEach(entry -> sb.append(String.format("##%s - %d", entry.getKey(), entry.getValue()))
                            .append(System.lineSeparator()));
        }
        System.out.println(sb.toString().trim());
    }
}
