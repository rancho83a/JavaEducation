package checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemPriceTest {

    @Test
    public  void testCalculate_price50_count5_calculate250(){
        ItemPrice itemPrice = new ItemPrice(50);

        long totalPrice = itemPrice.calculate(5);

        assertEquals(250, totalPrice);
    }

    @Test
    public  void testCalculate_price15_count3_calculate45(){
        ItemPrice itemPrice = new ItemPrice(15);

        long totalPrice = itemPrice.calculate(3);

        assertEquals(45, totalPrice);
    }

    @Test
    public  void testCalculate_price20_count3_calculate60(){
        ItemPrice itemPrice = new ItemPrice(20);

        long totalPrice = itemPrice.calculate(3);

        assertEquals(60, totalPrice);
    }

    @Test
    public  void testCalculate_price0_count0_calculate0(){
        ItemPrice itemPrice = new ItemPrice(0);

        long totalPrice = itemPrice.calculate(0);

        assertEquals(0, totalPrice);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegative_price_Throw_IAE(){
        new ItemPrice(-42);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegative_count_Throw_IAE(){
        ItemPrice itemPrice = new ItemPrice(10);
        long totalPrice = itemPrice.calculate(-7);
    }

    @Test
    public void item_price_with_name_from_getter(){
        ItemPrice itemPrice = new ItemPrice("A",42);
       String name = itemPrice.getName();
    assertEquals("A",name);
    }
    @Test
    public void another_item_price_with_name_from_getter(){
        ItemPrice itemPrice = new ItemPrice("B",42);
        String name = itemPrice.getName();
        assertEquals("B",name);
    }
    @Test
    public void item_with_noName_returns_Empty_String(){
        ItemPrice itemPrice = new ItemPrice(42);
        String name = itemPrice.getName();
        assertEquals("",name);
    }

    @Test
    public void can_have_discount(){
        ItemPrice itemPrice = new ItemPrice("A", 50, new NForMDiscount(3,130));
        long discountedPrice = itemPrice.calculate(3);
        assertEquals(130,discountedPrice);
    }

    @Test
    public void can_have_discount_4_items(){
        ItemPrice itemPrice = new ItemPrice("A", 50, new NForMDiscount(3,130));
        long discountedPrice = itemPrice.calculate(4);
        assertEquals(180,discountedPrice);
    }

}