# Intersection of Two Arrays II

Given two arrays, write a function to compute their intersection.

```
Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
```

## HashMap
紀錄每個 elements 出現的 frequencies，traversal 一遍 Array2，檢查是否有被加入過map中，並且加入Dynamic Array當中。

- map.getOrDefault(key, default value)
- List<Integer> dArray = new ArrayList<>();
- map.put(key, value)

```java
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
```

***

