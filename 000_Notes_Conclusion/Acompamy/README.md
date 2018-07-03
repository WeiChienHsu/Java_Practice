# A company 

[1 Two Sum](#1-two-sum)

[2 Add Tow Numbers](#2-add-two-numbers)

[3 Longest Substring Without Repeating Characters](#3-longest-substring-without-repeating-characters)

[5 Longest Palindromic Substring](#5-longest-palindromic-substring)

[8 String to Integer](#8-string-to-integer)


# 1 Two Sum

## Problem Analysis

1. Each input would have exactly one soultion.
2. We may not use the same element twice.
3. An array of itegers.
4. Return : "indices" of two numbers they add up to target!

## Algorithm Analysis
Used a HashMap to store the <(target - num[i]), i> value and loop through our array. If the value of num[i] is inside the map, just return a new int[]{i, map.get(nums[i])} but if there is not in the Map. Just add the key-value pair into the map.

## Time Complexity Analysis
- HashMap : O(n) time O(n) space

## Code

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        /* Map : Number, index */
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            } else {
                map.put(target - nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }
}
```

## Fellow up

How could you not use an additional data structure? 

If we would like to get an actual values, then could sort the array first and use two pointers to modify the value.

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        
        while(start < end) {
            int sum = numbers[start] + numbers[end];
            if(sum == target) {
                return new int[]{start + 1, end + 1};
            } else if(sum > target) {
                end--;
            } else if(sum < target) {
                start++;
            }
        }
        return new int[]{-1, -1};
    }
}
```



***

# 2 Add Two Numbers

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis
Used three single while loop cause O(3n) time complexity with additional dummy ListNode O(n)

## Code
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while(l1 != null && l2 != null) {
            int newVal = (l1.val + l2.val + carry);
            carry = 0;
            if(newVal > 9) {
                carry = 1;
                newVal %= 10;
            }
            
            cur.next = new ListNode(newVal);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while(l1 != null) {
            int newVal = l1.val + carry;
            carry = 0;
            if(newVal > 9) {
                carry = 1;
                newVal %= 10;
            }
            cur.next = new ListNode(newVal);
            cur = cur.next;
            l1 = l1.next;
        }
        
        while(l2 != null) {
            int newVal = l2.val + carry;
            carry = 0;
            if(newVal > 9) {
                carry = 1;
                newVal %= 10;
            }
            cur.next = new ListNode(newVal);
            cur = cur.next;
            l2 = l2.next;
        }
        
        if(carry != 0) cur.next = new ListNode(carry);

        return dummy.next;
    }
    
}
```

## Fellow up

***

# 3 Longest Substring Without Repeating Characters

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***

# 5 Longest Palindromic Substring

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***

# 8 String to Integer

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***
