import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[5];
        nums[0]= 1;
        nums[1]= 2;
        nums[2]= 100;
        nums[3]= 2;
        nums[4]= 2;
        int size = 3;
        double allowIncrease = 1.5;
        System.out.println(alerter(nums, size, allowIncrease));

        }

    private static boolean alerter(int[] nums, int size, double incre) {
    // Two Conditions to alert:
    // 1. The Max number in each Window is greater than minAverage times allowIncrease.
        // We need to track the Max number and count the Average and also record the min Average number.
    // 2. Current Average is greater than Last Average times allowIncrease.
        // Reuse the Average Number and record the Last Average Number.


        Deque<Integer> deque = new ArrayDeque<>();
        int curMax = Integer.MIN_VALUE;
        int curSum = 0;
        double curAve = Integer.MIN_VALUE;
        double minAve = Integer.MAX_VALUE;
        double lastAve = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i ++) {

            curMax = findMaxNumber(nums, size, deque, i);
            curSum = countSum(nums, size, i, curSum);
            curAve = curSum / (size * 1.0);
            lastAve = curAve;

            // Update the min Average
            if(minAve > curAve && i + 1 >= size) {
                minAve = curAve;
            }

            // if the amounts of number is greater than size and one of two conditions
            // happen, we'll return true to alert

            if(i + 1 >= size &&
                    (maxLargerThanAveTimesIncreAlert(curMax, minAve, incre) ||
                    curAveLargerThanLastAveTimesIncreAlert(curAve, lastAve,incre))) {
                return true;
            }
        }
        return false;
    }


    private static boolean maxLargerThanAveTimesIncreAlert(int curMax, double minAve, double incre ) {
        return curMax > minAve * incre;
    }

    private static boolean curAveLargerThanLastAveTimesIncreAlert(double curAve, double lastAve, double incre ) {
        return curAve > lastAve * incre;
    }


    private static int countSum(int[] nums, int size, int curIndex, int lastSum ) {
        int sum = lastSum;
        // If total amounts of numbers are not greater than size
        // Just add up the number
        if(curIndex < size){
            sum += nums[curIndex];
            return sum;
        }

        // If total amounts of numbers are greater than size
        // Minus the first number we used before
        sum += (nums[curIndex] - nums[curIndex - size]);
        return sum;
    }


    private static int findMaxNumber(int[] nums, int size, Deque<Integer> deque, int curIndex) {

        // Two Situations we need to pop out Index from Deque:
        // 1. Out of the size
        // 2. Current number is larger than tha index of Last number in Deque

            // Remove Elements out of window if the size too large
            // If the index of Peek is equal current index - size
            // means that index shouldn't exist in the Deque
            if(!deque.isEmpty() && deque.peekFirst() == curIndex - size) {
                deque.pollFirst();
            }
            // When the current number is larger than the last index of number,
            // pop out the Last Index and keep comparing until that number becomes
            // the largest one which means previous number won't exist in Deque

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[curIndex]) {
                deque.pollLast();
            }

            // Keep Pushing the current element into the Deque
            deque.offerLast(curIndex);

            // If current index is larger than size - 1, start recording number
            if(curIndex >= size - 1) {
                return nums[deque.peekFirst()];
            }
        return nums[curIndex];
        }


    }
