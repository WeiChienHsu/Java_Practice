# Mergoe Intervals
- Sorted Intervals by it's start point first, if start point is the same, use the end point by comparator
- Record the current start and end

- Iterate each interval:
- if interval.start <= curEnd, comparing the end and interval.end, and record the larger one
- if interval.start > curEnd : Mean there is no overlap!
- Record the curEnd and curStart
- Reset the curEnd and curStart
- Dont forget to record the last start and end  


```java
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
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals == null || intervals.isEmpty()){
            return intervals;
        }
        
        
        // Sorted Interval by its start (if start if the same, sorted by the end)
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2) {
                if(i1.start == i2.start) {
                    return i1.end - i2.end;
                } else {
                    return i1.start - i2.start;
                }
            }
        });
        
                
        // Keep tracking the start and end
        // If curent start <= end : Choose larger end between recorded end and current end
        // If curent start > end : Add in the result and Reset the start and end
        
        List<Interval> results = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        
        for(Interval interval : intervals) {
            if(interval.start <= end) {
                end = Math.max(interval.end, end);
            } else {
                results.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        results.add(new Interval(start, end));
        return results;
        

    }
}
```