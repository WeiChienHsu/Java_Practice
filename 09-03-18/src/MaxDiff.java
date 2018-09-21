public class MaxDiff {
    public static void main(String[] args) {
        int nums[] = new int[]{2, 3, 10, 2, 4, 8, 1};
        int result = getMaxDiff(nums);
        System.out.println(result);
    }

    public static int getMaxDiff(int[] nums) {
        int max = Integer.MIN_VALUE;

        for(int i = nums.length - 1; i > 0; i--) {
            for(int j = i - 1; j >= 0; j--) {
                if(nums[i] - nums[j] > max) {
                    max = nums[i] - nums[j];
                }
            }
        }
        return max;
    }

}
