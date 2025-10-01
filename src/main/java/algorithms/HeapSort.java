package algorithms;

import metrics.PerformanceTracker;
import java.util.Objects;

public class HeapSort {

    public static void sort(int[] a, PerformanceTracker tracker) {
        Objects.requireNonNull(a);
        int n = a.length;
        if (tracker != null) tracker.incAccesses(n);

        for (int i = parent(n - 1); i >= 0; i--) {
            siftDown(a, i, n - 1, tracker);
        }

        for (int end = n - 1; end > 0; end--) {
            if (tracker != null) {
                tracker.incSwaps();
                tracker.incAccesses(4);
            }
            swap(a, 0, end);
            siftDown(a, 0, end - 1, tracker);
        }
    }

    private static void siftDown(int[] a, int start, int end, PerformanceTracker tracker) {
        int root = start;
        while (leftChild(root) <= end) {
            int child = leftChild(root);
            int swapIdx = root;
            if (tracker != null) tracker.incAccesses(2);
            tracker.incComparisons();
            if (a[swapIdx] < a[child]) {
                swapIdx = child;
            }
            if (child + 1 <= end) {
                if (tracker != null) tracker.incAccesses(1);
                tracker.incComparisons();
                if (a[swapIdx] < a[child + 1]) {
                    swapIdx = child + 1;
                }
            }
            if (swapIdx == root) {
                return;
            } else {
                if (tracker != null) {
                    tracker.incSwaps();
                    tracker.incAccesses(4);
                }
                swap(a, root, swapIdx);
                root = swapIdx;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int parent(int i) { return (i - 1) / 2; }
    private static int leftChild(int i) { return 2 * i + 1; }
}
