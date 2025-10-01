package bench;

import algorithms.HeapSort;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class HeapSortBenchmark {

    @Param({"100", "1000", "10000"})
    public int n;

    private int[] baseArray;

    @Setup(Level.Trial)
    public void setUp() {
        Random r = new Random(12345);
        baseArray = new int[n];
        for (int i = 0; i < n; i++) baseArray[i] = r.nextInt();
    }

    @Benchmark
    public void heapSort_timeOnly() {
        int[] arr = java.util.Arrays.copyOf(baseArray, baseArray.length);
        HeapSort.sort(arr, null);
    }
}
