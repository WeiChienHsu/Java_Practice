class Solution {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length < 1){
            return;
        }
        
        int left = 0; //Left boundary of processed 0
        int right = nums.length -1; // Right boundary of processed 2
        int cur = 0; // Scanner for un processed data
        
        while(cur <= right) {
            if(nums[cur]  == 2) {
                nums[cur] = nums[right];
                nums[right] = 2;
                right --;
                cur --;
            } else if(nums[cur] == 0) {
                nums[cur] = nums[left];
                nums[left] = 0;
                left ++;
            } 
            // Meet 1 and after switched
            cur ++;

        }
    }
}