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
  public int minMeetingRooms(Interval[] intervals) {
      if(intervals.length == 0) return 0;
      
      List<Integer> ends = new ArrayList<>();
      Arrays.sort(intervals, new Comparator<Interval>(){
         public int compare(Interval i1, Interval i2) {
             return i1.start - i2.start;
         } 
      });
      
      ends.add(intervals[0].end);                
      int min_end = intervals[0].end; /* Record the min end in the array */
      
      for(int i = 1; i < intervals.length; i++) {
          /* need a new room */
          if(intervals[i].start < min_end) {
              ends.add(intervals[i].end);
              min_end = Math.min(intervals[i].end, min_end);
          } else {
              /* Need to choose a room and update its end value if needed */
              int roomIndex = 0;
              int difference = Integer.MAX_VALUE;
              
              for(int j = 0; j < ends.size(); j++) {
                  if(intervals[i].start > ends.get(j) && (intervals[i].start - ends.get(j)) < difference ) {
                      difference = intervals[i].start - ends.get(j);
                      roomIndex = j;
                  }
              }
              /* Update the end */
              ends.set(roomIndex, Math.max(intervals[i].end, ends.get(roomIndex)));
              min_end = Integer.MAX_VALUE;
              for(int k = 0; k < ends.size(); k++ ) {
                  if(min_end > ends.get(k)) min_end = ends.get(k);
              }
  
          }   
      }
      
      for(int i = 0; i < ends.size(); i++) {
          System.out.println(ends.get(i));
      }
      
      return ends.size();
  }
}