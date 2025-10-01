package benchmarks;

import algorithms.HeapSort;
import metrics.PerformanceTracker;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class HeapSortBenchmark {

    @Param({"100", "1000", "10000"})
    public int size;

    private int[] array;
    private PerformanceTracker tracker;

    @Setup(Level.Iteration)
    public void setup() {
        array = new int[size];
        Random r = new Random(12345 + size);
        for (int i = 0; i < size; i++) array[i] = r.nextInt();
        tracker = new PerformanceTracker();
    }

    @Benchmark
    public int[] heapSort() {
        int[] copy = array.clone(); // чтобы каждый раз сортировать одинаковый массив
        HeapSort.sort(copy, tracker);
        return copy;
    }
}
