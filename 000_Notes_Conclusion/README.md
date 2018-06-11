# 刷題總結


***

## Interval 類

### 56 Merge Intervals
Override the Comparator

- [Example code](../056_Merge_Intervals/Iteration_Comparator.java)
- [Details Description](../056_Merge_Intervals/)

#### Override
```java
class Intervals {
  int start;
  int end;
  Intervals(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

Collections.sort(intervals, new Comparator<Interval>(){
  public int compare(Intervals a1, Inteverals a2) {
    return Integer.compare(a1.start, a2.start);
    // return a1.start - a2.start (遞增)
  }
});
```

***

## Tree 類


***

## DP 類



***

## Example
[example](./example.java)



***
## 必刷題

#### 130 Surrounded Regions
練習 BFS(Queue) 與 DFS (Recursion || Stack) 解法

