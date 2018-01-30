class Solution {
public int search(int[] nums, int target) {
    if(nums == null || nums.length == 0) return -1;
    int start = 0;
    int end = nums.length -1;

    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if(nums[mid] == target) return mid;
        
        if(nums[mid] > nums[start]) { // left:  1 2 3 4 5 6 7 
            if(target >= nums[start] && target < nums[mid]) {
                end = mid; // target in left, remove right
            } else {
                start = mid;
            }
        } else{ // nums[mid] < nums[start] : Right   5 6 7 1 2 3 4
            if(target > nums[mid] && target <= nums[end]) {
                start = mid;
            } else {
                end  = mid;
            }
        }
    }
    
    if(nums[start] == target) return start;
    if(nums[end] == target) return end;
    return -1;
}
}