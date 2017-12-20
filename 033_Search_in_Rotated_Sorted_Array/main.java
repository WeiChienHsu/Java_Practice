class Solution {
public int search(int[] nums, int target) {
    if(nums == null || nums.length == 0) return -1;
    // Binary Serach two pointers
    int start = 0;
    int end = nums.length -1;
    // Give a never be same while loop
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        
        if(nums[mid] == target) return mid;
        
        if(nums[mid] > nums[start]) { // left
            if(target >= nums[start] && target < nums[mid]) {
                end = mid; // target in left, remove right
            } else {
                start = mid;
            }
        } else{ // nums[mid] < nums[start] : Right
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