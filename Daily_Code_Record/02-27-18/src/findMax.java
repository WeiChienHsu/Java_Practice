import java.util.ArrayDeque;
import java.util.Deque;

public class findMax {

    public static void main(String[] args) {
        int[] nums = new int[5];
        nums[0]= 1;
        nums[1]= 2;
        nums[2]= 100;
        nums[3]= 2;
        nums[4]= 2;

        int[] findMax = findMaxNumber(nums, 3);
    }

    private static int[] findMaxNumber(int[] nums, int size) {

        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < nums.length; i ++) {
        // Two Situations we need to pop out Index from Deque:
        // 1. Out of the size
        // 2. Current number is larger than tha index of Last number in Deque

            // Remove Elements out of window if the size too large
            // If the index of Peek is equal current index - size
            // means that index shouldn't exist in the Deque
            if(!deque.isEmpty() && deque.peekFirst() == i - size) {
                deque.pollFirst();
            }
            // When the current number is larger than the last index of number,
            // pop out the Last Index and keep comparing until that number becomes
            // the largest one which means previous number won't exist in Deque

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Keep Pushing the current element into the Deque
            deque.offerLast(i);

            // If current index is larger than size - 1, start recording number
            if(i > size - 1) {

            }


        }
    }
}
