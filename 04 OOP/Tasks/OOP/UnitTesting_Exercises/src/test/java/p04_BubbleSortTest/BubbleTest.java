package p04_BubbleSortTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {

    @Test
    public void sort() {
        int[] expected = {-1, 0, 1,2,3,4};
        int[] arr = {0,4,3,2,1,-1};

        Bubble.sort(arr);

        assertArrayEquals(expected,arr);
    }
}