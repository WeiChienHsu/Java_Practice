public class test {
    public static void main(String[] args) {
        int[] nums = {1,2,2,2,3};
        System.out.println(findLast(nums, 2));
    }

    public static int findLast(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(target == nums[mid]) {
                // Find the Last number : check left side
                start = mid;
            } else if(target > nums[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if(nums[end] == target) {
            return end;
        }

        if(nums[start] == target) {
            return start;
        }

        return -1;
    }
}
