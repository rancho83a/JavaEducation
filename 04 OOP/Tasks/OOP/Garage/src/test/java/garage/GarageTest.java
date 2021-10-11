package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class GarageTest {

    private Garage garage;
    private Car[] carsArr;

    @Before
    public void setup() {
        garage = new Garage();
        carsArr = createCarArr(10);
    }

    @Test public void createGarage(){
        Assert.assertNotNull(this.garage.getCars());
    }

    @Test public void getCars(){
        List<Car> actual = this.garage.getCars();
        List<Car> expected  = List.of(carsArr);
        Assert.assertEquals(expected,actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addCar_Null_value(){
        this.garage.addCar(null);
    }

    @Test
    public void getCount(){
        int count = this.garage.getCount();
        Assert.assertEquals(10,count);
    }

    @Test
    public void findAllCarsWithMaxSpeedAbove(){
        List<Car> actual = this.garage.findAllCarsWithMaxSpeedAbove(100);
        Assert.assertEquals(9,actual.size());
    }
    @Test
    public void getTheMostExpensiveCar(){
        Car actual = this.garage.getTheMostExpensiveCar();
        Car expected = carsArr[9];
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void findAllCarsByBrand(){
        List<Car> actual = this.garage.findAllCarsByBrand("brand1");
        Assert.assertEquals(1,actual.size());
        Assert.assertEquals(carsArr[1],actual.get(0));

    }


    private Car[] createCarArr(int count) {
        Car[] carsArr = new Car[count];
        for (int j = 0; j < count; j++) {
            Car car = new Car("brand" + j, 100+j, j + 0.1);
            carsArr[j] = car;
            this.garage.addCar(car);
        }
        return carsArr;
    }
}
