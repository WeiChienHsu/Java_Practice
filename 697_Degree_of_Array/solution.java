class Solution {
  public int findShortestSubArray(int[] nums) {
      Map<Integer, List<Integer>> map = new HashMap<>();
      int degree = 0;
      int result = nums.length;
      boolean notFound = false;
      
      for(int i = 0; i < nums.length; i++) {
          if(!map.containsKey(nums[i])) map.put(nums[i], new ArrayList<>());
          map.get(nums[i]).add(i);
      }
      
      for(int num : map.keySet())
          degree = Math.max(degree, map.get(num).size());
      
      for(int num : map.keySet()) {
          if(map.get(num).size() == degree) {
              List<Integer> list = map.get(num);
              result = Math.min(result, list.get(0 + degree - 1) - list.get(0) + 1);
          }
      }
      
      return result;
      
  }
}