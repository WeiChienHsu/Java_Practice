import java.util.*;

public class fourSum {
    public static void main(String[] args) {
        int[] nums = { 0, 0, 0, 0 };
        int target = 0;
        Set<Integer> set = new HashSet<>();
set.add()


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
                if(i + 3 < len && nums[i + 3] == z0) {
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
            if (i > 0 && z1 == nums[i - 1]) continue; // Duplicate
            if (z1 + 2 * nums[end] < target) continue; // Too small
            if (z1 * 3 > target) break; // Too large
            // Boundary
            if (3 * z1 == target) {
                if (i + 2 < end && nums[i + 2] == z1) {
                    results.add(Arrays.asList(z0, z1, z1, z1));
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
        return;
    }

}
