package greedyTimesMy;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long capacity = Long.parseLong(scan.nextLine());

        Map<String, List<Item>> bag = new LinkedHashMap<>();

        String[] tokens = scan.nextLine().split("\\s+");

        for (int i = 0; i < tokens.length; i = i + 2) {
            long amount = Long.parseLong(tokens[i + 1]);

            if (amount > capacity - getCurrentCapacity(bag)) {
                continue;
            }

            if ("gold".equals(tokens[i].toLowerCase())) {
                if (bag.containsKey("Gold")) {
                    bag.get("Gold").get(0).setAmount(amount + bag.get("Gold").get(0).getAmount());
                } else {
                    bag.put("Gold", new ArrayList<>());
                    bag.get("Gold").add(new Item("Gold", amount));
                }
            } else if (tokens[i].toLowerCase().endsWith("gem")) {//&& tokens[i].length() >= 4
                if (bag.containsKey("Gold")) {
                    long goldAmount = getItemAmount("Gold", bag);
                    long gemAmount = 0L;
                    if (bag.containsKey("Gem")) {
                        gemAmount = getItemAmount("Gem", bag);
                    }
                    if (amount <= goldAmount - gemAmount) {
                        bag.putIfAbsent("Gem", new ArrayList<>());
                        boolean isExistItemGem = false;

                        List<Item> gems = bag.get("Gem");
                        for (int j = 0; j < gems.size(); j++) {
                            if (gems.get(j).getName().equals(tokens[i])) {
                                gems.get(j).setAmount(amount + gems.get(j).getAmount());
                                isExistItemGem = true;
                                break;
                            }
                        }
                        if (!isExistItemGem) {
                            Item newGem = new Item(tokens[i], amount);
                            bag.get("Gem").add(newGem);
                        }
                    }
                }
            } else if (tokens[i].length() == 3) {

                if (bag.containsKey("Gem")) {
                    long gemAmount = getItemAmount("Gem", bag);
                    long cashAmount = 0L;
                    if (bag.containsKey("Cash")) {
                        cashAmount = getItemAmount("Cash", bag);
                    }

                    if (amount <= gemAmount - cashAmount) {
                        bag.putIfAbsent("Cash", new ArrayList<>());
                        boolean isExistItemCash = false;
                        List<Item> cashes = bag.get("Cash");
                        for (int j = 0; j < cashes.size(); j++) {
                            if (cashes.get(j).getName().equals(tokens[i])) {
                                cashes.get(j).setAmount(amount + cashes.get(j).getAmount());
                                isExistItemCash = true;
                                break;
                            }
                        }
                        if (!isExistItemCash) {

                            Item newGem = new Item(tokens[i], amount);
                            bag.get("Cash").add(newGem);
                        }
                    }
                }
            }
        }
        bag.entrySet().stream()
                .sorted((f, s) -> (int) (s.getValue().stream().mapToLong(Item::getAmount).
                        sum() - f.getValue().stream().mapToLong(Item::getAmount).sum()))
                .forEach(kv -> {
                    System.out.printf("<%s> $%d%n", kv.getKey(), kv.getValue().stream().mapToLong(Item::getAmount).sum());
                    kv.getValue().stream()
                            .sorted((f, s) -> {
                                int res = s.getName().compareTo(f.getName());
                                if (res == 0) {
                                    return (int) (f.getAmount() - s.getAmount());
                                }
                                return res;
                            })
                            .forEach(i -> System.out.printf("##%s - %d%n", i.getName(), i.getAmount()));
                });
    }

    private static long getCurrentCapacity(Map<String, List<Item>> bag) {
        long currentCapacity = 0L;
        for (List<Item> amounts : bag.values()) {
            currentCapacity += amounts.stream().mapToLong(Item::getAmount).sum();
        }
        return currentCapacity;
    }

    private static long getItemAmount(String key, Map<String, List<Item>> bag) {
        return bag.get(key).stream().mapToLong(Item::getAmount).sum();

    }
}