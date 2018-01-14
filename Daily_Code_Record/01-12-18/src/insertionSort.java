public class insertionSort {

    public static void main(String[] args) {
        int[] nums = {5,8,4,2,3,1};
        sort(nums);
        for(int num : nums) {
            System.out.println(num);
        }
    }

    private static void sort(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        for(int i = left, j = i ; i < right; j = ++i) {
            int ai = nums[i + 1];
            while (ai < nums[j]) {
                nums[j + 1] = nums[j];
                if(j-- == left) {
                    break;
                }
            }
            nums[j +1] = ai;
        }
    }
}
