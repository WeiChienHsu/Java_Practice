class Solution {
  public int[] intersect(int[] nums1, int[] nums2) {
      
      if(nums1.length == 0 || nums2.length == 0) return new int[]{};
      
      List<Integer> result = new ArrayList<>();
      
      Map<Integer, Integer> map = new HashMap<>();
      
      // Put All Integer into the Map
      for(int i = 0; i < nums1.length; i++) {
          map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
      }
      
      for(int j = 0; j < nums2.length; j++) {
          if(map.containsKey(nums2[j]) && map.get(nums2[j]) != 0) {
              result.add(nums2[j]);
              map.put(nums2[j], map.get(nums2[j]) - 1);
          }
      }
      
      int listSize = result.size();
      int[] ans = new int[listSize];
      
      for(int i = 0; i < listSize; i++) {
          ans[i] = result.get(i);
      }
      
      return ans;
      
  }
}