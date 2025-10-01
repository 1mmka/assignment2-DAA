package algorithms;

import metrics.PerformanceTracker;

public class HeapSort {

    public static void sort(int[] a, PerformanceTracker tracker) {
        if (a == null || a.length <= 1) {
            return;
        }

        int n = a.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(a, n, i, tracker);
        }

        for (int i = n - 1; i > 0; i--) {
            int tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            tracker.incSwaps();
            tracker.incAccesses(3);

            siftDown(a, i, 0, tracker);
        }
    }


    private static void siftDown(int[] a, int n, int i, PerformanceTracker tracker) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n) {
                tracker.incComparisons();
                tracker.incAccesses(2);
                if (a[left] > a[largest]) {
                    largest = left;
                }
            }

            if (right < n) {
                tracker.incComparisons();
                tracker.incAccesses(2);
                if (a[right] > a[largest]) {
                    largest = right;
                }
            }

            if (largest == i) {
                return;
            }

            int tmp = a[i];
            a[i] = a[largest];
            a[largest] = tmp;
            tracker.incSwaps();
            tracker.incAccesses(3);

            i = largest;
        }
    }
}
