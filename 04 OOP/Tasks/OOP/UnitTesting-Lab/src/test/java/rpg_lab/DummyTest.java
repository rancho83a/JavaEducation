package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {
    private final static int HEALTH =10;
    private final static int XP =10;

    private Dummy dummy;

    @Before
            public void setUp() {
        this.dummy = new Dummy(HEALTH, XP);
    }

    @Test
    public void DummyLosesHealthIfAttacked() {


        dummy.takeAttack(4);

        int expectedHealth = 6;
        assertEquals(expectedHealth, dummy.getHealth());

    }

    @Test(expected = IllegalStateException.class)
    public void DeadDummyThrowsExceptionIfAttacked() {
        dummy.takeAttack(HEALTH);
     dummy.takeAttack(111);
    }


    @Test
    public void DeadDummyCanGiveXP(){
        dummy.takeAttack(HEALTH);
        int currentXP = dummy.giveExperience();

        assertEquals(currentXP,XP);
    }

    @Test(expected = IllegalStateException.class)
    public void DeadDummyCanNotGiveXP(){
        dummy.giveExperience();
    }

    @Test
    public void isDeadShouldReturnFalse(){

        assertFalse(dummy.isDead());
    }

}