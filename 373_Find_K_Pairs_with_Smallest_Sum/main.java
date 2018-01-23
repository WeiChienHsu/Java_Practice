import java.util.Comparator;
import java.util.PriorityQueue;

class Solution{
  public List<int[]> List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // nums1 nums2 變成matrix
        //    2   3   6
        // 1  3   4   7
        // 2  4   5   7
        // 7  9   10  13
        // 位置存入int[nums1位置, nums2位置] 
        //  放入 minHeap內 ( We Save Poisition into minHeap)
        // Put all first column into minHeap
        // pop out and add into list
        // go right if there's still a number and add into minHeap
        List<int[]> list = new ArrayList<>();
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k <= 0) return list;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, Comparator<int[]>(){
          @Override
          public int compare(int[] o1, int[] o2) {
            int sum1 = nums1[o1[0]] + nums2[o1[1]];
            int sum2 = nums1[o2[0]] + nums2[o2[1]];
            if(sum1 == sum2) {
              return 0;
            } else {
              return sum1 < sum2 ? -1 : 0;
            }
          }
        });
        
        for(int i = 0; i < nums1.length; i++) {
          minHeap.add(new int[]{i,0});
        }

        while(!minHeap.isEmpty() && k > 0) {
          int[] cur = minHeap.poll();
          list.add(new int[] {nums1[cur[0]],nums2[cur[1]]});
          if(++cur[0] < nums2.length) {
            minHeap.add(cur);
          }
          k--;
        }
        return list;
}