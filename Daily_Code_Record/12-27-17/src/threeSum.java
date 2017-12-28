import java.util.Arrays;

public class threeSum {
    public static void main(String[] args) {
        int[] nums = {0, 2, 1, -3};
        int target = 100;
        System.out.println(threeSumClosest(nums, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int res = 0;
        if(len < 3) {
            for(int num: nums) {
                res += num;
            }
            return res;
        }

        res = nums[0] + nums[1] + nums[2];
        for(int i = 0; i <= len - 3; i++ ) {
            int left = i + 1;
            int right = len - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(target - sum) <= Math.abs(target - res)) {
                    res = sum;
                    if(res == target) return res;
                }
                if(sum > target) right--;
                else if(sum < target) left++;
            }
        }
        return res;
    }
}
