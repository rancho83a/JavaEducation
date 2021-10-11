package p05_CustomLinkedList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {
    public static CustomLinkedList<String> linkedList;

    @Before
    public void setUp() {
        linkedList = new CustomLinkedList<>();
        linkedList.add("A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetElementWithBigIndex() {
        linkedList.get(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetElementWithNegativeIndex() {
        linkedList.get(-1);
    }

    @Test
    public void testGetElementWithCorrectIndex() {
        String actual = linkedList.get(0);
        String expected = "A";
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetElementWithBigIndex() {
        linkedList.set(1, "B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetElementWithNegativeIndex() {
        linkedList.get(-1);
    }

    @Test
    public void testSetElementWithCorrectIndex() {
        linkedList.set(0, "B");
        String actual = linkedList.get(0);
        String expected = "B";
        assertEquals(expected, actual);
    }

    @Test
    public void testAddEmptyList() {
        CustomLinkedList<String> customList = new CustomLinkedList<>();
        customList.add("C");
        assertEquals(0, customList.indexOf("C"));
    }

    @Test
    public void testAddInNonEmptyList() {
        linkedList.add("C");
        assertEquals(1, linkedList.indexOf("C"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtBigIndex() {
        linkedList.removeAt(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtNegativeIndex() {
        linkedList.removeAt(-11);
    }

    @Test
    public void testRemoveAtCorrectIndex() {

        assertTrue(linkedList.contains("A"));
        String actual = linkedList.removeAt(0);
        assertFalse(linkedList.contains("A"));
        assertEquals("A", actual);
    }

    @Test
    public void testRemoveNonExistElement() {
        int actual = linkedList.remove("Z");
        assertEquals(-1, actual);
    }

    @Test
    public void testRemoveExistElement() {
        int actual = linkedList.remove("A");
        assertEquals(0, actual);
    }

}