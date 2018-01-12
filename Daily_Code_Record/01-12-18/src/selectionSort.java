public class selectionSort {
    public static void main(String[] args) {
        int[] nums = {5,8,4,2,3,1};
        sort(nums);
        for(int num : nums) {
            System.out.println(num);
        }
    }

    private static void sort(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] > nums[j]) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
