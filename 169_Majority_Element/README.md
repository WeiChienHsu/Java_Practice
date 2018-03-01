# Majority

- Used a Map to record all number frequencies.
- Go thorough to see if the frquency is the most.
- Record the majority number!

```java
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i],0) + 1);
        }
        int mostFreqNum = 0;
        int mostFreq = 0;
        
        for(int key: freqMap.keySet()) {
            if(freqMap.get(key) > mostFreq) {
                mostFreq = freqMap.get(key);
                mostFreqNum = key;
            }
        }
        return mostFreqNum;
    }
}
```
