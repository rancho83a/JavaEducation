package checkout;

import java.util.HashMap;
import java.util.Map;

public class Checkout {

    private  final PricingRules pricingRules;
    private final Map<String, Integer> scanItems;

    public Checkout(PricingRules pricingRules) {
        this.pricingRules = pricingRules;
        this.scanItems = new HashMap<>();
    }

    public long total() {

        return scanItems.entrySet().stream()
                .mapToLong(entry-> pricingRules.ruleFor(entry.getKey()).calculate(entry.getValue()))
                .sum();
    }

    public void scan(String item) {
        scanItems.putIfAbsent(item, 0);
        scanItems.put(item, scanItems.get(item) + 1);
    }
}
