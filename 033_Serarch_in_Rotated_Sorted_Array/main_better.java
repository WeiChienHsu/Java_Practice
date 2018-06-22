class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        int start = 0;
        int end = nums.length -1 ;
        int mid;
        
        while(start < end) {
            mid = start + (end - start) / 2 ;
            if(nums[mid] > nums[end]) { // 4 5 6 7 0 1 2
                if(target > nums[mid] || target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }  
                if(nums[mid] < nums[end]){ // 5 6 7 0 1 2 3
                    if(target > nums[mid] && target <= nums[end]){
                        start = mid + 1;
                    } else {
                        end = mid;
                    }
            }
        }
        
        return start == end && target != nums[start]? -1 : start;

    }
}