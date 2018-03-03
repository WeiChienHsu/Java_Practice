import java.util.ArrayList;
import java.util.List;

public class Unique {
    public static void main(String[] args) {
        int[] nums = new int[6];
        nums[0] = 1;
        nums[1] = 1;
        nums[2] = 21;
        nums[3] = 21;
        nums[4] = 21;
        nums[5] = 21;
        int target = 42;
        List<List<Integer>> results = uniquePairs(nums, target);
        for( List<Integer> res : results){
            System.out.println(res);
        }
    }

    private static List<List<Integer>> uniquePairs(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            if(nums[left] + nums[right] == target) {
                List<Integer> res = new ArrayList<>();
                res.add(nums[left]);
                res.add(nums[right]);
                results.add(res);
                right--;
                left++;

                while(left < right && nums[left] == nums[left - 1]) {
                    left++;
                }

                while (right > left && nums[right] == nums[right + 1]) {
                    right--;
                }

            }else if(nums[left] + nums[right] > target) {
                right--;
            } else {
                left--;
            }
        }
        return results;
    }
}
