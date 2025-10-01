package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class HeapSortTest {

    @Test
    public void testEmpty() {
        int[] a = new int[0];
        HeapSort.sort(a, new PerformanceTracker());
        assertArrayEquals(new int[0], a);
    }

    @Test
    public void testSingle() {
        int[] a = new int[]{5};
        HeapSort.sort(a, new PerformanceTracker());
        assertArrayEquals(new int[]{5}, a);
    }

    @Test
    public void testDuplicates() {
        int[] a = new int[]{3,1,2,3,3,0};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        HeapSort.sort(a, new PerformanceTracker());
        assertArrayEquals(expected, a);
    }

    @Test
    public void testRandom() {
        Random r = new Random(42);
        int[] a = new int[1000];
        for (int i = 0; i < a.length; i++) a[i] = r.nextInt(10000) - 5000;
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        HeapSort.sort(a, new PerformanceTracker());
        assertArrayEquals(expected, a);
    }
}
