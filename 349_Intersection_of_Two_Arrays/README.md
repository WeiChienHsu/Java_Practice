# 349. Intersection of Two Arrays



# Solution 

## O(n) time complexity

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> results = new HashSet<>();
        for(int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for(int j = 0; j < nums2.length; j++) {
            if(set.contains(nums2[j])){
                results.add(nums2[j]);
            }
        }
        
        int[] resultArray = new int[results.size()];
        int count = 0;
        for(int r : results) {
            resultArray[count++] = r;
        }
        
        return resultArray;
        
    }
}
```