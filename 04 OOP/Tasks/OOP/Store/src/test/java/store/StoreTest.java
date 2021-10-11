package store;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class StoreTest {

    private Store store;
    private Product[] productsArr;

    @Before
    public void setup() {
        store = new Store();
        productsArr = createProductArr(10);
    }

    @Test
    public void createStore() {
        Assert.assertNotNull(store.getProducts());
    }
    @Test
    public void getProduct() {
        List<Product> actual = store.getProducts();
        List<Product> expected = List.of(productsArr);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void getCount(){
        Assert.assertEquals(10,store.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullProductShouldReturnEx(){
        store.addProduct(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProductWithQuantityBelowZeroShouldReturnEx(){
        store.addProduct(new Product("ss",-5,7.7));
    }

    @Test (expected = IllegalArgumentException.class)
    public void buyProductWithNonExistName(){
        store.buyProduct("e",5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void buyProductWithQuantityMoreThenExist(){
        store.buyProduct("1",3);
    }

    @Test
    public void buyProductWithCorrectParametrs(){
        double actual = store.buyProduct("0", 1);
        Assert.assertEquals(0.1, actual,0.00);
    }

    @Test
    public void getMoreExpensiveProduct(){
        Product actual = store.getTheMostExpensiveProduct();
        Product expected=productsArr[9];
        assertEqualsProduct(expected, actual);
    }

    private void assertEqualsProduct(Product expected, Product actual) {
        Assert.assertEquals(expected.getName(),actual.getName());
        Assert.assertEquals(expected.getPrice(),actual.getPrice(),0.00);
        Assert.assertEquals(expected.getQuantity(),actual.getQuantity());
    }


    private Product[] createProductArr(int count) {
        Product[] arr = new Product[count];
        for (int i = 0; i < count; i++) {
            Product product= new Product("" + i, i + 1, i + 0.1);
            arr[i] =product;
            this.store.addProduct(product);
        }
        return arr;
    }
}