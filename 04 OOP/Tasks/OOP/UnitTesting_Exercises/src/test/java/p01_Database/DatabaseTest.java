package p01_Database;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    private Database database;
    private static final Integer[]  EXPECTED   = {1, 2, 3, 4};

    @Before
    public void setup() throws OperationNotSupportedException {
        database = new Database(1, 2, 3, 4);
    }


    @Test
    public void testConstructorShouldCreateArrayWithValidState() throws OperationNotSupportedException {


        assertEquals(4, database.getElements().length);

        for (int i = 0; i < EXPECTED.length; i++) {
            assertEquals(EXPECTED[i], database.getElements()[i]);
        }
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testCreateDatabaseWithMoreThen16elementsShouldThrowException() throws OperationNotSupportedException {
        Integer[] elements = new Integer[17];
        new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testCreateDatabaseWithLessThan1elementsShouldThrowException() throws OperationNotSupportedException {
        Integer[] elements = new Integer[0];
        new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAdd_NULL_ElementShouldThrowException() throws OperationNotSupportedException {
        Integer el = null;
        database.add(el);
    }

    @Test
    public void testAddValidElementShouldThrowException() throws OperationNotSupportedException {
        database.add(6);
        Integer[] el = database.getElements();
        assertEquals(5, el.length);
        assertEquals(Integer.valueOf(6), el[4]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveElementFromEmptyDatabase() throws OperationNotSupportedException {
        for (int i = 0; i < 4; i++) {
            database.remove();
        }
        database.remove();
    }


    @Test
    public void testRemoveLastElementFromNonEmptyDatabase() throws OperationNotSupportedException {
        database.remove();
        Integer[] actual = database.getElements();
        Integer[] expected = {1, 2, 3};
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}