package cli;

import algorithms.HeapSort;
import metrics.PerformanceTracker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        int[] sizes = {100, 1000, 10000};
        String out = "docs/performance.csv";
        if (args.length >= 1) {
            String[] parts = args[0].split(",");
            sizes = new int[parts.length];
            for (int i = 0; i < parts.length; i++) sizes[i] = Integer.parseInt(parts[i]);
        }
        if (args.length >= 2) out = args[1];

        try (FileWriter fw = new FileWriter(out);
             CSVPrinter printer = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader("n","elapsedMs","comparisons","swaps","accesses"))) {

            for (int n : sizes) {
                int[] arr = generateRandomArray(n, 12345);
                PerformanceTracker tracker = new PerformanceTracker();
                long start = System.currentTimeMillis();
                HeapSort.sort(arr, tracker);
                long elapsed = System.currentTimeMillis() - start;
                printer.printRecord(n, elapsed, tracker.getComparisons(), tracker.getSwaps(), tracker.getAccesses());
                System.out.printf("n=%d done: %d ms, cmp=%d, swaps=%d, acc=%d\n",
                        n, elapsed, tracker.getComparisons(), tracker.getSwaps(), tracker.getAccesses());
            }
        }
        System.out.println("CSV written to " + out);
    }

    private static int[] generateRandomArray(int n, long seed) {
        Random r = new Random(seed + n);
        int[] a = new int[n];
        for (int i=0;i<n;i++) a[i] = r.nextInt();
        return a;
    }
}
