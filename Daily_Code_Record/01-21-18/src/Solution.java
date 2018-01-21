import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int ans = maxThree(nums);
        System.out.println(getFactors(4));
    }

    public static int maxSubArray(int[] nums) {
        int maxEndHere = nums[0];
        int maxSoFar = nums[0];
        for(int i = 1; i < nums.length; i++) {
            maxEndHere = Math.max(nums[i], maxEndHere + nums[i]);
            maxSoFar = Math.max(maxEndHere, maxSoFar);
        }
        return maxSoFar;
    }

    public static int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int result = max;
        for(int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]) , nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            result = Math.max(result, max);
        }
        return result;
    }

    public static int maxThree(int[] nums){
         if(nums.length == 0) return 0;
         if(nums.length == 1) return nums[0];
         if(nums.length == 2) return nums[0] * nums[1];
         int max = Integer.MIN_VALUE;
         Arrays.sort(nums);
         int n = nums.length -1;
         max = Math.max((nums[0]*nums[1]*nums[n]),nums[n]*nums[n-1]*nums[n-2]);
         return max;
    }

    public static boolean isUgly_naive(int num) {
        if(num == 1) return true;
        List<Integer> factors = getFactors(num);
        int count = 0;
        for(int i = 0; i < factors.size(); i++){
            if(factors.get(i) != 2 && factors.get(i) != 3 && factors.get(i) != 5) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUgly(int num){
        if(num == 1 || num == 2 || num == 3 || num == 5) return true;
        int i = 2;
        while(num > 0){
            if(num % i == 0){
                num /= i;
                if(i != 2 && i != 3  && i != 5) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }


    public static List<Integer> getFactors(int n) {

        List<Integer> list = new ArrayList<>();
        for(int i = 2; i < n / 2; i ++) {
            if(n%i == 0){
                n /= i;
                list.add(i);
            }
        }
        return list;
    }
//
//    public static boolean isPrime(int n){
//        if (n < 2) return false;
//        if (n == 2) return true;
//        if (n % 2 == 0) return false;
//        for(int i = 3; i * i <= n; i+=2){
//            if(n % i == 0) return false;
//        }
//        return true;
//    }

    public static int[] plusOne(int[] nums) {

    }
}