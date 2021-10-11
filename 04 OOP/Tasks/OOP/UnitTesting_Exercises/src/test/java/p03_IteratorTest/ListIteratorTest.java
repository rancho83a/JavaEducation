package p03_IteratorTest;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {
    private ListIterator listIterator;
    private static final String[] ELEMENTS = {"1", "2", "3"};

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.listIterator = new ListIterator(ELEMENTS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testCreateListIteratorWith_NULL_ElementShouldThrowException() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void testMoveShouldReturnCorrectBoolean() {
        assertTrue(listIterator.move());
        assertTrue(listIterator.move());
        assertFalse(listIterator.move());
    }

    @Test
    public void testHasNextShouldReturnCorrectBoolean() {
        assertTrue(listIterator.hasNext());
        listIterator.move();
        assertTrue(listIterator.hasNext());
        listIterator.move();
        assertFalse(listIterator.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintWhenEmptyListIterator() throws OperationNotSupportedException {
        new ListIterator().print();
    }

    @Test
    public void testPrintWhenElementsAreExistInListIterator() throws OperationNotSupportedException {

        for (int i = 0; listIterator.hasNext(); listIterator.move()) {
            String actual = listIterator.print();
            String expected = ELEMENTS[i++];
            assertEquals(expected, actual);
        }
    }
}