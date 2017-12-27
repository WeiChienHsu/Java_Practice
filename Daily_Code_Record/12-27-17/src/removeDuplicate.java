public class removeDuplicate {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3,5,5,10};
        int[] new_nums = removeDuplicates(nums);

        for(int i = 0; i < new_nums.length; i++ ){
            System.out.println(new_nums[i]);
        }
    }

    public static int[] removeDuplicates(int[] nums){
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] == nums[i]) {
                continue;
            } else {
                nums[++index] = nums[i];
            }
        }
        return nums;
    }
}
