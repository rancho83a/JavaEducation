import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {

    private static final String PRODUCT_LABEL = "testLabel";

    private ProductStock stock;
    private Product testProduct;
    private Product[] productsArr;

    @Before
    public void setUp() {
        stock = new Instock();
        testProduct = new Product(PRODUCT_LABEL, 4.5, 1);
    }

    @Test
    public void testAddShoudSaveProduct() {
        stock.add(testProduct);
        assertTrue(stock.contains(testProduct));
    }

    @Test
    public void testContains() {
        assertFalse(stock.contains(testProduct));
        stock.add(testProduct);
        assertTrue(stock.contains(testProduct));
    }

    @Test
    public void testCountShouldReturn1ForSingleProduct() {
        stock.add(testProduct);
        assertEquals(1, stock.getCount());
    }

    @Test
    public void testCountShouldReturn10ForTEN_Product() {
        int expectedProductCount = 10;
        addProductsToStock(expectedProductCount);
        assertEquals(expectedProductCount, stock.getCount());
    }

    @Test
    public void testCountShouldReturn0ForEmptyStock() {
        assertEquals(0, stock.getCount());
    }

    @Test
    public void testFindByIndexShouldReturnCorrectProductWhenOnly1() {
        stock.add(testProduct);
        assertProductsAreEquals(testProduct, stock.find(0));
    }

    @Test
    public void testFindByIndexShouldReturnCorrectProductWhenTEN() {
        addProductsToStock(9);
        assertProductsAreEquals(productsArr[productsArr.length - 1], stock.find(productsArr.length - 1));
    }

    @Test
    public void testFindByIndexShouldReturnCorrectProductWhenTEN_AndTakeProductInTheMiddle() {
        addProductsToStock(9);
        stock.add(testProduct);
        assertProductsAreEquals(productsArr[(productsArr.length - 1) / 2], stock.find((productsArr.length - 1) / 2));
    }

    @Test
    public void testFindByIndexShouldReturnCorrectProductsWhenAllIndexesAreFetched() {
        addProductsToStock(10);
        for (int i = 0; i < 10; i++) {
            assertProductsAreEquals(productsArr[i], stock.find(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void FindByIndexWhenEmpty() {
        stock.find(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void FindByIndexWhenNegativeIndex() {
        stock.find(-2);
    }

    @Test
    public void changeQuantityReturnNewSetQuantity() {
        int newQuantity = 10 + testProduct.getQuantity();
        stock.add(testProduct);
        stock.changeQuantity(testProduct.getLabel(), newQuantity);
        assertEquals(newQuantity, testProduct.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeQuantityReturnExceptionWhenNoFindProduct() {
        stock.changeQuantity(testProduct.getLabel(), 1);
    }


    @Test
    public void findByLabelReturnExistProduct() {
        stock.add(testProduct);
        Product actual = stock.findByLabel(testProduct.getLabel());
        assertProductsAreEquals(testProduct, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByLabelReturnExceptionWhenNoFindProduct() {
        stock.add(testProduct);
        stock.findByLabel("Non-Existing Label");
    }

    @Test
    public void findFirstByAlphabeticalOrderReturnCorrectElements() {
        addProductsToStock(10);

        List<Product> expected = Arrays.stream(productsArr).sorted(Comparator.comparing(Product::getLabel))
                .limit(5).collect(Collectors.toList());

        Iterable<Product> iter = stock.findFirstByAlphabeticalOrder(5);
        assertNotNull(iter);

        List<Product> actual = new ArrayList<>();
        iter.forEach(actual::add);
        assertEqualsLists(expected, actual);
    }

    @Test
    public void findFirstByAlphabeticalOrderReturnEmptyList() {
        Iterable<Product> iter = stock.findFirstByAlphabeticalOrder(5);
        assertNotNull(iter);
        List<Product> actual = new ArrayList<>();
        iter.forEach(actual::add);
        assertEquals(0, actual.size());
    }

    @Test
    public void findFirstByAlphabeticalOrderReturnEmptyListWhenCountIsGreaterThanProductsStored() {
        addProductsToStock(10);
        Iterable<Product> iter = stock.findFirstByAlphabeticalOrder(11);
        assertNotNull(iter);
        AtomicInteger counter = new AtomicInteger();
        iter.forEach(p -> counter.incrementAndGet());
        assertEquals(0, counter.get());
    }

    @Test
    public void findAllInPriceRangeReturnCorrectProducts() {
        addProductsToStock(10);

        Iterable<Product> allInRange = stock.findAllInRange(13, 17);
        assertNotNull(allInRange);
        List<Product> actual = new ArrayList<>();
        allInRange.forEach(actual::add);
        assertEquals(4, actual.size());
        assertTrue(actual.stream().noneMatch(p -> p.getPrice() <= 13 || p.getPrice() > 17));
    }

    @Test
    public void findAllInPriceRangeReturnCorrectProductsInDesendingOrder() {
        addProductsToStock(10);
        List<Product> expected = Arrays.stream(productsArr)
                .filter(p -> p.getPrice() > 13 && p.getPrice() <= 17)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());

        Iterable<Product> allInRange = stock.findAllInRange(13, 17);
        assertNotNull(allInRange);

        List<Product> actual = new ArrayList<>();
        allInRange.forEach(actual::add);
        assertEqualsLists(expected, actual);
    }

    @Test
    public void findAllInPriceRangeReturnEmptyListWhenNotFoundProductsInRange() {
        addProductsToStock(10);

        Iterable<Product> allInRange = stock.findAllInRange(19, 179);
        assertNotNull(allInRange);

        List<Product> actual = new ArrayList<>();
        allInRange.forEach(actual::add);
        assertTrue(actual.isEmpty());
    }


    @Test
    public void findAllByPriceReturnOnlyProductsWithSamePrice() {
        addProductsToStock(5);
        for (int i = 0; i < productsArr.length; i++) {
            productsArr[i].setPrice(100);
        }
        Iterable<Product> allByPrice = stock.findAllByPrice(100);
        assertNotNull(allByPrice);

        List<Product> actual = getListFromIterable(allByPrice);
        assertEquals(5, actual.size());
        assertTrue(actual.stream().noneMatch(p -> p.getPrice() != 100));
    }

    @Test
    public void findAllByPriceReturnEmptyListWhenNoProductwithSamePrice() {
        addProductsToStock(5);

        Iterable<Product> allByPrice = stock.findAllByPrice(111);
        assertNotNull(allByPrice);

        List<Product> actual = getListFromIterable(allByPrice);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void findFirstMostExpensiveProductsReturnCorrectProducts() {
        addProductsToStock(20);

        List<Product> expected = Arrays.stream(productsArr).sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(10).collect(Collectors.toList());
        stock.findFirstMostExpensiveProducts(5);

        Iterable<Product> firstMostExpensiveProducts = stock.findFirstMostExpensiveProducts(10);
        assertNotNull(firstMostExpensiveProducts);

        List<Product> actual = getListFromIterable(firstMostExpensiveProducts);
        assertEqualsLists(expected, actual);
    }


    @Test(expected = IllegalArgumentException.class)
    public void findFirstMostExpensiveProductsReturnExceptionWhenCountIsGreaterThenProductsStored() {
        addProductsToStock(20);
        stock.findFirstMostExpensiveProducts(21);
    }

    private void assertEqualsLists(List<Product> expected, List<Product> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertProductsAreEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void findAllByQuantityReturnOnlyProductsWithSameQuantity() {
        addProductsToStock(5);
        List<Product> expected = new ArrayList<>();
        for (int i = 0; i < productsArr.length; i++) {
            productsArr[i].setQuantity(100);
            expected.add(productsArr[i]);
        }
        Iterable<Product> allByQuantity = stock.findAllByQuantity(100);
        assertNotNull(allByQuantity);

        List<Product> actual = getListFromIterable(allByQuantity);
        assertEquals(5, actual.size());
        assertTrue(actual.stream().noneMatch(p -> p.getQuantity() != 100));
        assertEqualsLists(expected, actual);
    }

    @Test
    public void findAllByQuantityReturnEmptyListWhenNoProductWithSameQuantity() {
        addProductsToStock(5);

        Iterable<Product> allByPrice = stock.findAllByQuantity(111);
        assertNotNull(allByPrice);

        List<Product> actual = getListFromIterable(allByPrice);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void getIterableReturnAllProducts() {
        addProductsToStock(10);

        List<Product> expected = Arrays.asList(this.productsArr);

        Iterator<Product> allProducts = stock.iterator();

        List<Product> actual = new ArrayList<>();
        allProducts.forEachRemaining(actual::add);

//        while(allProducts.hasNext()){
//            actual.add(allProducts.next());
//        }
        assertEqualsLists(expected,actual);
    }
    @Test
    public void getIterableReturnEmptyIterator() {
        Iterator<Product> allProducts = stock.iterator();

        assertFalse(allProducts.hasNext());
    }


    private <T> List<T> getListFromIterable(Iterable<T> iterable) {
        List<T> list = new ArrayList<T>();
        iterable.forEach(list::add);
        return list;
    }

    private void assertProductsAreEquals(Product expected, Product actual) {
        assertEquals(expected.getLabel(), actual.getLabel());
        assertEquals(expected.getPrice(), actual.getPrice(), 0.00);
        assertEquals(expected.getQuantity(), actual.getQuantity());
    }

    private void addProductsToStock(int count) {
        productsArr = new Product[count];
        for (int i = 0; i < count; i++) {
            productsArr[i] = new Product("" + i, i + 10, i);
            stock.add(productsArr[i]);
        }
    }


}