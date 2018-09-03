class MovingAverage {
    
  int size;
  int[] nums;
  double sum = 0.0;

  /** Initialize your data structure here. */
  public MovingAverage(int size) {
      this.size = 0;
      this.nums = new int[size];
  }
  
  public double next(int val) {
      if(nums.length == size) {
          for(int i = 1; i < size; i++) {
              nums[i - 1] = nums[i]; 
          }
          nums[size - 1] = val;
      } else {
          nums[size] = val;
          size++;
      }
      
      for(int i = 0; i < nums.length; i++) {
          sum += nums[i];
      }
      
      return sum / (double) size;
  }
}

/**
* Your MovingAverage object will be instantiated and called as such:
* MovingAverage obj = new MovingAverage(size);
* double param_1 = obj.next(val);
*/