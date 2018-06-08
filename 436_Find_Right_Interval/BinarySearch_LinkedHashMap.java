/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
  public int[] findRightInterval(Interval[] intervals) {
      
      // 1. 將 Start Point 和 對應的Index 存入 Map<Interval, Integer> 當中。
      // 2. 重新排序 Intervals，依據他的Start Point，Java中需要借助 Comparator<Interval>()，由小排至大
      // 3. Traversal 過一次 Map 中所有的 End Point，
      // 讓End Point用Binary Search去找我們排序過的Interval內，有沒有適合的Start Point，可以對應。
      // 4. Binary Search
      
      Map<Interval, Integer> indexMap = new LinkedHashMap<>();
      int[] results = new int[intervals.length];
      
      // Put all Interval with its index into the IndexMap 
      for(int i = 0; i < intervals.length; i++) {
          indexMap.put(intervals[i], i);
      }
      
      // Sort the intervals Array by the start point
      Arrays.sort(intervals, new Comparator<Interval>() {
          @Override
          public int compare(Interval a, Interval b) {
              return a.start - b.start;
          }
      });
      
      // Traversal Whole End points in the Map and Find their pair in the sorted intervals array
      int count = 0;
      for(Interval each : indexMap.keySet()) {
          int targetIndex = Solution.binarySearch(intervals, each);
          if(targetIndex < 0) results[count++] = -1;
          else results[count++] = indexMap.get(intervals[targetIndex]);
      }
      
      return results;
  }
  
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
}