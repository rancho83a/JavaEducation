package computers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ComputerManagerTest {

    private ComputerManager computerManager;
    private Computer[] compArr;

    @Before
    public void setUp() {
        computerManager = new ComputerManager();
        compArr = createArr(10);
    }

    @Test
    public void createComputer() {
        assertNotNull(computerManager.getComputers());
    }
    @Test
    public void getComputers(){
        List<Computer> actual = computerManager.getComputers();
        List<Computer> expected = List.of(compArr);
        assertEquals(expected,actual);
    }
    @Test
    public void getCount(){
        int actual = computerManager.getCount();
        assertEquals(10, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addComputerWithNullValue(){
        computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addComputerWhichAlreadyExist(){
        computerManager.addComputer(new Computer("manufacturer0","model0", 0.1));
    }
    @Test
    public void  removeComputer(){
        Computer computerToRemove = computerManager.removeComputer("manufacturer0", "model0");
        assertEquals(9,computerManager.getCount());
        Computer expected =new Computer("manufacturer0","model0", 0.1);
        assertEqualsComputers(expected,computerToRemove);
    }

    private void assertEqualsComputers(Computer comp1, Computer comp2) {
        assertEquals(comp1.getManufacturer(),comp2.getManufacturer());
        assertEquals(comp1.getModel(),comp2.getModel());
    }
    @Test
    public void getComputersByManufacturer(){
        List<Computer> actual = computerManager.getComputersByManufacturer("manufacturer0");
        assertEquals(1,actual.size());
    }
    @Test(expected = IllegalArgumentException.class)
    public void getComputersByManufacturerWithNullValue(){
        List<Computer> actual = computerManager.getComputersByManufacturer(null);
    }


    private Computer[] createArr(int count) {
        Computer[] arr = new Computer[count];
        for (int j = 0; j < count; j++) {
            Computer comp = new Computer("manufacturer" + j, "model" + j, j + 0.1);
            arr[j] = comp;
            this.computerManager.addComputer(comp);
        }
        return arr;
    }

}