import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> list = new ArrayList<>();
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length ==0 || k <= 0) return list;

        // Use an anonymous Class here
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int sum1 = nums1[o1[0]] + nums2[o1[1]];
                int sum2 = nums1[o2[0]] + nums2[o2[1]];
                if(sum1 == sum2) {
                    return 0;
                } else {
                    return sum1 < sum2? -1 : 1;
                }
            }});

        // Put the number in first column into Heap
        for(int i = 0; i < nums1.length; i++) {
            minHeap.add(new int[]{i,0});
        }

        // Only Go right for next node
        while(!minHeap.isEmpty() && k > 0) {
            int[] cur = minHeap.poll();
            list.add(new int[] {nums1[cur[0]], nums2[cur[1]]});
            if(++cur[1] < nums2.length) {
                // Go Right
                minHeap.add(cur);
            }
            k--;
        }
        return list;
    }



    public static void main(String[] args) {
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int k = 3;
        System.out.println(kSmallestPairs(nums1,nums2,k));
    }

    }
