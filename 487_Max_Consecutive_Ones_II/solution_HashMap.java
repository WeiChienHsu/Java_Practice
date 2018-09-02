class Solution {
  public int findMaxConsecutiveOnes(int[] nums) {
      Map<Integer, Integer> map = new HashMap<>();
      map.put(-1, Integer.MIN_VALUE);
      int lastIndexOfZero = -1;
      int currentLength = 0;
      int max_len = 0;
      
      for(int i = 0; i < nums.length; i++) {
          
          if(nums[i] == 0) {
              map.put(i, currentLength);
              map.put(lastIndexOfZero, map.get(lastIndexOfZero) + currentLength);
              currentLength = 0;
              lastIndexOfZero = i;
          } else {
              currentLength++;
          }
          
      }
      
      if(lastIndexOfZero != nums.length - 1) {
          map.put(lastIndexOfZero, map.get(lastIndexOfZero) + currentLength);
      }
      
      if(lastIndexOfZero == -1) {
          return nums.length;
      }
      

      for(int num : map.keySet()) {
          System.out.println(num + ":" + map.get(num));
          if(max_len < map.get(num)) {
              max_len = map.get(num);
          }
      }
      
      return max_len + 1;
      
  }
}