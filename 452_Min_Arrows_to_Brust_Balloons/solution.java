/* 
    1. Sorted the points by the end value. 
    ex([1,6] [2,8] [7,12] [10,16])
    2. Chose the first point[1] as the first arrow
    3. Find the next point which has its first element larger than the position of arror
    4. Update the new position of the arrow and update the number it needed

*/


class Solution {
  public int findMinArrowShots(int[][] points) {
      if(points.length == 0) return 0;
      Arrays.sort(points, (a, b) -> a[1] - b[1]);
      int count = 1;
      int arrowPosition = points[0][1];
      for(int i = 1; i < points.length; i++) {
          if(arrowPosition >= points[i][0]) continue;
          arrowPosition = points[i][1];
          count++;
      }
      
      return count;
  }
}