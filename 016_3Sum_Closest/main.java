public int threeSumClosest(int[] nums, int target) {
        
    Arrays.sort(nums);
    
    int lens = nums.length;
    int res = 0;
    if(lens <= 3) {
        for(int num : nums) {
            res += num;
        }
        return res;
    }
    
    res = nums[0] + nums[1] + nums[2];
    for (int i = 0 ; i <= lens -3; i++ ) {
        int left = i + 1;
        int right = lens - 1;
        
        while(left < right) {
            int sum = nums[i] + nums[left] + nums[right];
            if(Math.abs(target - sum) <= Math.abs(target - res)) {
                res = sum;
                if (res == target) return res;
            }
        
            if(target > sum) left++;
            else if(target < sum) right--;
        }
    }        
    return res;
}