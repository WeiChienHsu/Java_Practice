import java.util.Comparator;
import java.util.PriorityQueue;

public class kLarge {

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(kLarge(nums,k));
    }

    public static int kLarge(int[] nums, int k) {
        if(nums == null || nums.length < k) return Integer.MIN_VALUE;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, new MyComparator());

        // Init
        for(int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        // Add Rest - Smaller than peek : ignore
        for(int i = k; i < nums.length; i++) {
            if(nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.poll();
    }


}

class MyComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
        if(i1.equals(i2)) return 0;
        else return i1 - i2;
    }
}
