package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class PerformanceTracker {
    private final AtomicLong comparisons = new AtomicLong(0);
    private final AtomicLong swaps = new AtomicLong(0);
    private final AtomicLong accesses = new AtomicLong(0);

    public void incComparisons() { comparisons.incrementAndGet(); }
    public void incSwaps() { swaps.incrementAndGet(); }
    public void incAccesses(long n) { accesses.addAndGet(n); }

    public long getComparisons() { return comparisons.get(); }
    public long getSwaps() { return swaps.get(); }
    public long getAccesses() { return accesses.get(); }

    public void reset() {
        comparisons.set(0);
        swaps.set(0);
        accesses.set(0);
    }

    public String toCsvLine(int n, long elapsedMs) {
        return String.format("%d,%d,%d,%d,%d", n, elapsedMs, getComparisons(), getSwaps(), getAccesses());
    }

    public static void writeHeaderCsv(FileWriter fw) throws IOException {
        fw.write("n,elapsedMs,comparisons,swaps,accesses\n");
    }

    public static void writeLineCsv(FileWriter fw, String line) throws IOException {
        fw.write(line + "\n");
        fw.flush();
    }
}
