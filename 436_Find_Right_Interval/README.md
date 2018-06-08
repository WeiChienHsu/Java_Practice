# Find Right Interval
Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

```
Example 1:
Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.
Example 3:
Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.

```

## Solution
題目要替每個End Point找到等於或是小於他的連接 Start Point， 並且設定將不會有“重複的"Start Point，所以，我們可以使用Map的結構來存取所有的起始點以及該位置，並且將所有Start Point重新排列(Sort)，讓End Point可以使用Binary Search找尋對應的Start Point。

```java
public class Interval {
  int start;
  int end;
  Interval() { start = 0; end = 0; }
  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}
```

1. 將 Start Point 和 對應的Index 存入 Map<Interval, Integer> 當中。
- 要使用 LinkedHashMap 來實現，才可以保有插入的順序！
```java
Map<Interval, Integer> indexMap = new LinkedHashMap<>();
int[] results = new int[intervals.length];

// Put all Interval with its index into the IndexMap 
for(int i = 0; i < intervals.length; i++) {
    indexMap.put(intervals[i], i);
}
```


2. 重新排序 Intervals，依據他的Start Point，Java中需要借助 Comparator<Interval>()，由小排至大

```java
// Sort the intervals Array by the start point
Arrays.sort(intervals, new Comparator<Interval>() {
    @Override
    public int compare(Interval a, Interval b) {
        return a.start - b.start;
    }
});
```

3. Traversal 過一次 Map 中所有的 End Point，讓End Point用Binary Search去找我們排序過的Interval內，有沒有適合的Start Point，可以對應。

```java
int count = 0;
for(Interval each : indexMap.keySet()) {
    int targetIndex = Solution.binarySearch(intervals, each);
    if(targetIndex < 0) results[count++] = -1;
    else results[count++] = indexMap.get(intervals[targetIndex]);
}

return results;
```

4. Binary Search

```java
public static int binarySearch(Interval[] intervals, Interval each) {
  int start = 0, end = intervals.length - 1;
  while(start + 1 < end) {
    int mid = start + (end - start) / 2;
      
    if(intervals[mid].start < each.end) {
      start = mid;
    } else {
      end = mid;
    }
  }

  if(intervals[start].start < each.end && intervals[end].start < each.end) return -1;

  return intervals[start].start > each.end ? start : end;
}
```


## Map Differences
HashMap is implemented as a hash table, and there is no ordering on keys or values. 
TreeMap is implemented based on red-black tree structure, and it is ordered by the key. 
LinkedHashMap preserves the insertion order. 
Hashtable is synchronized, in contrast to HashMap.

