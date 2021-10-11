package checkout;

// Item   Unit      Special
//         Price     Price
//  --------------------------
//    A     50       3 for 130
//    B     30       2 for 45
//    C     20
//    D     15


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckoutTest {
    PricingRules testRules;

    @Before
    public void setup() {
        testRules = new PricingRules(
                new ItemPrice("A", 50, new NForMDiscount(3,130)),
                new ItemPrice("B", 30, new NForMDiscount(2,45)),
                new ItemPrice("C", 20),
                new ItemPrice("D", 15)
        );

    }

    @Test
    public void testNonScannedItemReturnZeroInTotal() {
        //GIVEN
        Checkout checkout = new Checkout(testRules);

        //WHEN
        long total = checkout.total();

        //THEN
        assertEquals(0, total);
    }

    @Test
    public void testScan_A_Item() {
        //GIVEN
        Checkout checkout = new Checkout(testRules);
        checkout.scan("A");

        //WHEN
        long total = checkout.total();

        //THEN
        assertEquals(50, total);
    }

    @Test
    public void testScan_B_Item() {
        //GIVEN
        Checkout checkout = new Checkout(testRules);
        checkout.scan("B");

        //WHEN
        long total = checkout.total();

        //THEN
        assertEquals(30, total);
    }

    @Test
    public void testScan_AB_Item_returns_80() {
        //GIVEN
        Checkout checkout = new Checkout(testRules);
        checkout.scan("A");
        checkout.scan("B");

        //WHEN
        long total = checkout.total();

        //THEN
        assertEquals(80, total);
    }

    @Test
    public void testScan_ABCD_Item_returns_115() {
        //GIVEN
        Checkout checkout = new Checkout(testRules);
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("C");
        checkout.scan("D");
        //WHEN
        long total = checkout.total();
        //THEN
        assertEquals(115, total);
    }
    @Test
    public void testScan_AA_Item_returns_100() {
        //GIVEN
        Checkout checkout = new Checkout(testRules);
        checkout.scan("A");
        checkout.scan("A");

        //WHEN
        long total = checkout.total();
        //THEN
        assertEquals(100, total);
    }

    @Test
    public void testScan_AAA_Item_returns_130() {
        //GIVEN
        Checkout checkout = new Checkout(testRules);
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("A");

        //WHEN
        long total = checkout.total();
        //THEN
        assertEquals(130, total);
    }

    @Test
    public void testScan_AAABBD_Item_returns_130() {
        //GIVEN
        Checkout checkout = new Checkout(testRules);
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("B");
        checkout.scan("A");
        checkout.scan("D");

        //WHEN
        long total = checkout.total();
        //THEN
        assertEquals(190, total);
    }

    @Test
    public void testScan_DABABA_Item_returns_130() {
        //GIVEN
        Checkout checkout = new Checkout(testRules);
        checkout.scan("D");
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("A");

        //WHEN
        long total = checkout.total();
        //THEN
        assertEquals(190, total);
    }

    @Test
    public void testIncremental() {
        //GIVEN
        Checkout checkout = new Checkout(testRules);
        assertEquals(0, checkout.total());
        checkout.scan("A");
        assertEquals(50, checkout.total());

        checkout.scan("B");
        assertEquals(80, checkout.total());

        checkout.scan("A");
        assertEquals(130, checkout.total());

        checkout.scan("A");
        assertEquals(160, checkout.total());

        checkout.scan("B");
        assertEquals(175, checkout.total());
    }

}
