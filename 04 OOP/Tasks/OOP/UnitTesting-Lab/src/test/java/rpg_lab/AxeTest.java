package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AxeTest {
    private static final int ATTACK = 10;
    private static final int DURABILITY = 10;

    private Axe axe;

    @Before
    public void setUp() {
        this.axe = new Axe(ATTACK, DURABILITY);
    }

    @Test
    public void testIfWeaponLosesDurabilityAfterEachAttack() {
        Dummy target = new Dummy(100, 100);
        this.axe.attack(target);

        int expectedValue = 9;
        int currentValue = axe.getDurabilityPoints();
        assertEquals(expectedValue, currentValue);
    }

    @Test(expected = IllegalStateException.class)
    public void testWithBrokenWeapon() {
        Dummy target = new Dummy(100, 100);
        this.axe.attack(target);
        axe.attack(target);

    }
}
