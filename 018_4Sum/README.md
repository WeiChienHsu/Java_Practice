# 4 Sum

- 一層一層往下，4Sum 挑一個數字， 進入 3Sum 挑一個數字， 進入 2Sum 找到結果。
- 每層開始前，先判斷目前狀況是否合法？ number not enough? Boundry is too small or large?
- 每一層都要進行以下判斷：
- 1. 選的數字是否與前一個數字相同？ Duplicate (Continue)
- 2. 選的數字是否 too samll ? 4 * nums[len - 1] < target (Continue)
- 3. 選的樹字是否 too large ? 4 * nums[i] > target (Break!)
- 4. 選到Boundry(4*nums[i] == target) -> 判斷nums[i+3]是否等於nums[i]，如果相同加入ans(不同的話結束break)
- To each level, we need to send current number, targetRemain, start and end point, recorded Array into next Level

#### threeSum(int[] nums, int target(target - nums[i]), i + 1, len - 1, res, nums[i])

## Solution

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fourSum {
    public static void main(String[] args) {
        int[] nums = { 1, 0, -1, 0, -2, 2 };
        int target = 0;

        List<List<Integer>> results = getFourSum(nums, target);
        System.out.println(results);
    }

    private static List<List<Integer>> getFourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        int len = nums.length;
        if(len < 4) return results;
        int start = 0;
        int end = len - 1;

        // Too Large or Too small
        if(nums[start] * 4 > target || nums[end] * 4 < target) return results;

        for(int i = 0; i < len - 1; i++){
            int z0 = nums[i];
            if(i > 0 && z0 == nums[i-1]) continue; // Duplicate
            if(z0 + 3 * nums[end] < target) continue; // Too small
            if(z0 * 4 > target) break; // Too large
            // Boundary
            if(4 * z0 == target) {
                if(nums[i + 3] == z0) {
                    results.add(Arrays.asList(z0,z0,z0,z0));
                }
                break;
            }

        getThreeSumFromFourSum(results, i + 1, end, z0, target - z0, nums);

        }
        return results;
    }

    private static void getThreeSumFromFourSum(List<List<Integer>> results,
                                               int start,
                                               int end,
                                               int z0,
                                               int target,
                                               int[] nums) {

        if(start +1  >= end) return;
        if(nums[start] * 3 > target || nums[end] * 3 < target) return;

        for(int i = start; i < end; i++) {
            int z1 = nums[i];
            if(i > 0 && z1 == nums[i-1]) continue; // Duplicate
            if(z1 + 2 * nums[end] < target) continue; // Too small
            if(z1 * 3 > target) break; // Too large
            // Boundary
            if(3 * z1 == target) {
                if(nums[i + 2] == z1) {
                    results.add(Arrays.asList(z0,z1,z1,z1));
                }
                break;
            }

            getTwoSumFromThreeSum(results, i + 1, end, z0, z1, target - z1, nums);
        }
    }

    private static void getTwoSumFromThreeSum(List<List<Integer>> results,
                                              int start,
                                              int end,
                                              int z0,
                                              int z1,
                                              int target,
                                              int[] nums) {
        if(start >= end) return;
        if(nums[start] * 2 > target || nums[end] * 2 < target) return;

        int left = start;
        int right = end;

        while (left < right){

            if(nums[left] + nums[right] == target) {
                results.add(Arrays.asList(z0, z1, nums[left], nums[right]));

                // Deduplicate
                while ( left + 1  < right && nums[left] == nums[left + 1]) left++;
                while ( right -1  > left && nums[right] == nums[right - 1]) right--;
            }

            if(nums[left] + nums[right] < target ) {
                left++;
            } else {
                right--;
            }
        }
    }
}

```

