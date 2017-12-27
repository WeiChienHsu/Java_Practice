public void sortColors(int[] nums) {
    if(nums == null || nums.length < 1){
        return;
    }
    
    int left = 0;
    int right = nums.length - 1;
    int cur = 0;
    
    while(cur <= right){
        if(nums[cur] == 0) {
            swap(nums, cur++, left++);
        } else if(nums[cur] == 2) {
            swap(nums, cur, right--);
        } else{
            cur++;
        }
    }
}

public void swap(int[] nums, int a, int b) {
    int tmp = nums[a];
    nums[a] = nums[b];
    nums[b] = tmp;
}