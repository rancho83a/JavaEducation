package rpg_lab;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroTest {

    private static final int TARGET_XP = 50;
    private static final String NAME = "KILLER";


    @Test
    public void testIfHeroGainsXPWhenTargetDies() {

        Target fakeTarget = new Dummy(10, TARGET_XP);


        Weapon fakeWeapon = new Weapon() {
            @Override
            public int getAttackPoints() {
                return 10;
            }

            @Override
            public int getDurabilityPoints() {
                return 0;
            }

            @Override
            public void attack(Target target) {
                target.takeAttack(target.getHealth() + 1);

            }
        };

        Hero hero = new Hero(NAME, fakeWeapon);

        hero.attack(fakeTarget);

        assertEquals(50, hero.getExperience());
    }

    @Test
    public void testHeroKillingTargetGettingLootInHisInventory(){

        Axe mockAxe =  mock(Axe.class);
        Hero hero = new Hero("first", mockAxe);
        Target mockTarget = mock(Target.class);
        when(mockTarget.getLoot()).thenReturn(mockAxe);
        when(mockTarget.isDead()).thenReturn(true);
        hero.attack(mockTarget);

        assertEquals(hero.getInventory().size(),1);
        assertTrue(hero.getInventory().contains(mockAxe));

    }
}