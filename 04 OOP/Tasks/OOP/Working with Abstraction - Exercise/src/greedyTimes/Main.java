package greedyTimes;

import greedyTimes.Services.CashService;
import greedyTimes.Services.GemService;
import greedyTimes.Services.Impl.CashServiceImpl;
import greedyTimes.Services.Impl.GemServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long capacity = Long.parseLong(scanner.nextLine());
        String[] treasure = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(capacity);

        for (int i = 0; i < treasure.length; i += 2) {
            GemService gemService = new GemServiceImpl(bag.getGems());
            CashService cashService = new CashServiceImpl(bag.getCash());
            String item = treasure[i];
            long amount = Long.parseLong(treasure[i + 1]);

            String name = "";

            if (item.length() == 3) {
                name = "Cash";
            } else if (item.toLowerCase().endsWith("gem")) {
                name = "Gem";
            } else if (item.toLowerCase().equals("gold")) {
                name = "Gold";
            }

            if (name.equals("")) {
                continue;
            } else if (bag.getCapacity() < amount) {
                continue;
            }

            switch (name) {
                case "Gem":
                    if (!gemService.doesContainsGem(item)) {
                        if (bag.getGold() != null) {
                            if (gemService.getSummaryAmountGem() + amount > bag.getGold().getAmount()) {
                                continue;
                            }
                            bag.getGems().add(new Gem(item, amount));
                            bag.decreaseCapacity(amount);
                        } else {
                            continue;
                        }
                    } else if (gemService.getSummaryAmountGem() + amount > bag.getGold().getAmount()) {
                        continue;
                    } else {
                        Gem gem = gemService.findGemByName(item);
                        gem.setAmount(amount + gem.getAmount());
                        bag.decreaseCapacity(amount);

                    }
                    break;
                case "Cash":
                    if (!cashService.doesContainsCash(item)) {
                        if (bag.getGems().size() > 0) {
                            if (cashService.getSummaryAmountCash() + amount > gemService.getSummaryAmountGem()) {
                                continue;
                            }
                            bag.getCash().add(new Cash(item, amount));
                            bag.decreaseCapacity(amount);
                        } else {
                            continue;
                        }
                    } else if (cashService.getSummaryAmountCash() + amount > gemService.getSummaryAmountGem()) {
                        continue;
                    } else {
                        Cash currentCash = cashService.findCashByName(item);
                        currentCash.setAmount(amount + currentCash.getAmount());
                        bag.decreaseCapacity(amount);
                    }
                    break;
                case "Gold":
                    if (bag.getGold() == null) {
                        bag.setGold(new Gold(amount));
                        bag.decreaseCapacity(amount);

                    } else {
                        Gold gold = bag.getGold();
                        gold.setAmount(amount + gold.getAmount());
                        bag.decreaseCapacity(amount);
                    }
                    break;
            }
        }
        bag.print();
        System.out.println();
    }
}