package checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class PricingRulesTest {

    @Test
    public void initiate_PricingRulesWithOneItemPrice(){
        PricingRules pricingRules = new PricingRules(new ItemPrice(44));
    }

    @Test(expected = ItemNotFoundException.class)
    public void empty_rules_throws_item_not_found_exception_when_asked_for_price(){
        PricingRules pricingRules = new PricingRules();
        pricingRules.ruleFor("A");
    }

    @Test(expected = ItemNotFoundException.class)
    public void another_empty_rules_throws_item_not_found_exception_when_asked_for_price(){
        PricingRules pricingRules = new PricingRules();
        pricingRules.ruleFor("B");
    }

    @Test
    public  void pricing_rules_with_rule_A_returns_the_rule_from_rulesFor(){
        PricingRules pricingRules = new PricingRules(new ItemPrice("A",111));

        ItemPrice itemPrice=pricingRules.ruleFor("A");

        assertEquals(new ItemPrice("A",111), itemPrice);
    }

    @Test
    public  void pricing_rules_with_rule_A_and_B_returns_the_rule_from_rulesFor_A_and_B(){
        PricingRules pricingRules = new PricingRules(
                new ItemPrice("A",111),
                new ItemPrice("B",111)
        );

        ItemPrice itemPriceA=pricingRules.ruleFor("A");
        ItemPrice itemPriceB=pricingRules.ruleFor("B");

        assertEquals(new ItemPrice("A",111), itemPriceA);
        assertEquals(new ItemPrice("B",111), itemPriceB);
    }

}