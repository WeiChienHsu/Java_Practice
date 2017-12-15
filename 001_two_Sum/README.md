# 2 Sum
## Given an array of integers, return indices of the two numbers such that they add up to a specific target.

- Example
```
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

### First Thought
- Not good. Used two for loops.
- Time Complexity: O(n^2)


```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for( int i = 0; i < nums.length; i++) {
            int findTarget = target - nums[i];
            for( int j = i + 1; j < nums.length; j++){
                if(nums[j] == findTarget ) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            } // Search the Rest array
        } // Point one number and Search all array
        return result;
    }
}
```

### Second Thought - Used another HashMap
- Used target - value to find a remian value and save in a Hashmap. Then, find if there is a same value there in the hashmap

```
4 -3 2 11 7 15  target:9
```
- HashMap: 
```
  12 7 -2 2 -6  ----> find 2!!!
```
- Hash: O(1) method to find a number in bounch of numbers
- HashMap: Since we need to return a pair of index and need value to calculate. 
- HashMap <target - num[i]. i>

- Not the best Way (O(1)). Since we path two times:

* Search for a number to find remain value
* Find up the same number in HashMap

### Third Thought - Try to Path ONE time!!!

- Save and Find Value in the same time

```
4  -3  2  11  7*  15   target = 9
 \  \   \  \
  5  12  7  -2
  0  1   2   3      find 7 in both places -> [2, 4]
```


- Build up a Map
```java
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
```

- Find the same value, get the first result from HashMap and second one from numbers[i]
```java
if (map.containsKey(target - numbers[i])) {
    result[1] = i + 1;
    result[0] = map.get(target - numbers[i]);
    return result;
}
``` 

- If there is no same value, just save the remian value into HashMap
```java
map.put(numbers[i], i + 1);
```