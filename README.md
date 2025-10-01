# HeapSort Benchmark Project

## Overview
This project implements and benchmarks the **HeapSort algorithm** in Java with detailed performance tracking.

The benchmark measures:
- Execution time (ms)
- Number of comparisons
- Number of swaps
- Number of array accesses 


Results are exported into a CSV file:
`docs/performance.csv`


and visualized with plots (see [`docs/performance-plots/`](docs/performance-plots/)).


---

## Setup & Run

### Clone and build:
```bash
git clone https://github.com/your-repo/assignment2.git
cd assignment2
mvn clean package
```


## Results location:
* CSV file: docs/performance.csv
* Example contents:
```yaml
n,elapsedMs,comparisons,swaps,accesses
100,1,1020,580,3953
1000,1,16846,9064,62533
10000,4,235512,124274,860372
```

## Algorithm: HeapSort
HeapSort is a comparison-based sorting algorithm built on the binary heap structure.

* Heap construction: O(n)
* Repeated extraction: O(n log n)
* Total complexity:
    1. Best Case: Θ(n log n)
    2. Average Case: O(n log n)
    3. Worst Case: O(n log n)
       Additional properties:

Space Complexity: O(1) (in-place)
Stability: Not stable


## Optimizations
Implemented optimizations in this project:

* Bottom-up heap construction (faster than repeated insertions).
* Early stopping in siftDown when no swaps are needed.
* Inlined swap operations (avoid function call overhead).
* Reduced array accesses by caching indices in local variables.


## Empirical Results
| n      | Time (ms) | Comparisons | Swaps   | Accesses |
| ------ | --------- | ----------- | ------- | -------- |
| 100    | 1         | 1,020       | 580     | 3,953    |
| 1,000  | 1         | 16,846      | 9,064   | 62,533   |
| 10,000 | 4         | 235,512     | 124,274 | 860,372  |


## Plots
The following plots are generated from docs/performance.csv and stored in [`docs/performance-plots/`](docs/performance-plots/)
Execution Time vs n
Comparisons vs n
Swaps vs n
Accesses vs n


## Analysis
### Theoretical vs Empirical
* Runtime grows as expected with O(n log n).
* For n = 10,000, runtime ≈ 4ms, confirming logarithmic scaling vs quadratic algorithms.

### Memory Accesses
* Accesses dominate cost (~860k for n = 10,000).
* Highlights importance of cache locality.

### Optimizations Impact
* Early stopping in siftDown reduces redundant comparisons.
* Inlined swaps reduce function call overhead.
* Cached indices reduce array access count.

### Comparison with ShellSort (partner’s algorithm)
* Small n: ShellSort may be faster (lower constant factors).
* Large n: HeapSort is more consistent and predictable (always O(n log n)).
* ShellSort’s performance depends on gap sequence, while HeapSort is deterministic.


---


## Conclusion
#### HeapSort proves to be:
* Efficient and predictable with O(n log n) scaling.
* Memory-efficient (in-place, O(1)).
* Practical for large datasets.
* Slower than QuickSort in practice due to higher memory access cost.

This project demonstrates both theoretical complexity analysis and empirical benchmarking, confirming the expected performance of HeapSort.