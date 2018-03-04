# Next Greater Number
- 記得num2[] 的 排序和 num1[] 是不同的
- num1[4,1,2]
- num2[1,3,4,2]
- 要先在num2中找到num1[i]的數字，再從他後面去比較，找出「下個比num1[i]大的數」
- Easy ! But you need to clearify what the problems are!

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] results = new int[nums1.length];
        
        //[2,4] [1,2,3,4]
        // c       c n
        
        for(int i = 0; i < nums1.length ; i++){
            int curNum = nums1[i];
            int nextLargestNum = findNextLargestNumber(curNum, nums2);
            if(nextLargestNum <= curNum){
                results[i] = -1;
            } else{
                results[i] = nextLargestNum;
            }
            
        }
        return results;
    }
    // Return the next largest
    // 2 -> [1,2,3,4]
    private int findNextLargestNumber(int num, int[] nums) {
        boolean metNum = false;
        for(int i = 0; i < nums.length; i ++) {
            if(nums[i] == num) metNum = true;
            if(metNum && nums[i] > num) {
                return nums[i];
            }
        }
        return num;
    }
}
```

## Used Map + Stack

Suppose we have a decreasing sequence followed by a greater number
```
For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers in the sequence
```

We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek() we pop all elements less than x and for all the popped ones, their next greater element is x

```
For example [9, 8, 7, 3, 2, 1, 6]
The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1 so we pop 1 2 3 whose next greater element should be 6
```

```java
class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(), num);
            stack.push(num);
        }   
        for (int i = 0; i < findNums.length; i++)
            findNums[i] = map.getOrDefault(findNums[i], -1);
        return findNums;
    }
}
```