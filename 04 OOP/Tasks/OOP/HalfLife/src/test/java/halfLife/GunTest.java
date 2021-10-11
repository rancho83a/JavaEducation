package halfLife;

import org.junit.Test;

import static org.junit.Assert.*;

public class GunTest {
    private Gun gun;

    @Test
    public void CreateCorrectGunGetName(){
        gun = new Gun("p",10);
        assertEquals("p", gun.getName());
    }

    @Test
    public void CreateCorrectGunGetBullet(){
        gun = new Gun("p",10);
        assertEquals(10, gun.getBullets());
    }


}