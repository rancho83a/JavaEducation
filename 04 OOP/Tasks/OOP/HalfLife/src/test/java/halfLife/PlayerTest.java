package halfLife;

`import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;`

public class PlayerTest {
    private Player player;

    @Before
    public void setup(){
        player = new Player("a",5);
    }
    @Test
    public void createCorrectPlayer() {
        Player player = new Player("A", 5);

        assertEquals("A",player.getUsername());
    }
    @Test(expected = NullPointerException.class)
    public void createPlayerWithNameLengthLessThen1(){
        Player player = new Player("",8);
    }
    @Test(expected = NullPointerException.class)
    public void createPlayerWithNameNULL(){
        Player player = new Player(null,8);
    }

    @Test
    public void testGetHealthShouldReturnCorrectValue(){
        assertEquals(5, player.getHealth());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetHealthWithValueBelow0(){
        Player p = new Player("s",-8);
    }

    @Test
    public void addGunsShouldWork(){
        Gun gun = new Gun("p", 10);
        player.addGun(gun);
        List<Gun> actual = player.getGuns();
        List<Gun> expected = List.of(gun);

        assertEquals(expected,actual);
    }
    @Test(expected =  NullPointerException.class)
    public void addGunsNULLShouldReturnException(){
        player.addGun(null);
    }

    @Test (expected = IllegalStateException.class)
    public void testTakeDamageForDiedPlayer(){
        Player player = new Player("a",1);
        player.takeDamage(12);
        player.takeDamage(1);
    }

    @Test
    public void testTakeDamageShouldWork(){
        Player player = new Player("a",6);
        player.takeDamage(5);
        assertEquals(1, player.getHealth());
    }

    @Test
    public void testTakeDamageMoreThenHealthShouldReturn0(){
        Player player = new Player("a",6);
        player.takeDamage(111);
        assertEquals(0, player.getHealth());
    }

    @Test
    public void removeGunShouldWork(){
        Gun g1 =new Gun("g1",1);
        Gun g2 =new Gun("g2",2);
        player.addGun(g1);
        player.addGun(g2);
        assertEquals(2,player.getGuns().size());

        player.removeGun(g1);

        assertEquals(1,player.getGuns().size());
    }


    @Test
    public void getCorrectGunShouldWork(){
        Gun g1 =new Gun("g1",1);
        Gun g2 =new Gun("g2",2);
        player.addGun(g1);
        player.addGun(g2);

        Gun actual = player.getGun("g1");
        equalsGuns(g1,actual);


    }

    private void equalsGuns(Gun g1, Gun g2){
        assertEquals(g1.getName(),g2.getName());
        assertEquals(g1.getBullets(),g2.getBullets());
    }


}