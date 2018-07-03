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

We have two linked lists represented to the single number, and we would like to sum them up. In addition, it will be presented in another linkedlist

- Deal with the Carry (5+6 = 11) -> 1 -> 1 in another level
- Treaves the whole linkedlist nodes by nodes and get their value of sum
- Check if the sum is larger than 9, i'll change the carry to 1 so could be used in sum.
- While there is one of the linkedlist meet the end, I'll just look at single linked list.
- Use a dummy node to make those behavior in a while loop and return the dummy.next.

## Algorithm Analysis

O(n) time comlexity!

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
Given a long stringa and We need to find the longest substrg (which have no duplicated character inside the string).

## Algorithm Analysis

I used a HashMap to record the current index and in the meanwhile I would like to store the character that I've visited before.

So, we need two pointer to record the len. By doing so, the i means the pointer travse the string and the j pointer is the LEFT-MOST one.

How could I get the value of J? Use the MAP! since the map has record the left recently apprear character so when we meet the same one which is in the map, j will equal to the value of the Max one between j or map.get(character) + 1.

So when we meet the same character, update the left poiner(j)

and wheh we are traversing the string, we need to update two things, one is the recently appear character in the map and the max Lenght with the substring!

## Time Complexity Analysis

time complexity : O(n) An additional space(Map) : O (n)

## Code

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;
        int maxLen = Integer.MIN_VALUE;
        
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i = 0, j = 0; i < s.length(); i++) {
            /* Meet the same character */
            if(map.containsKey(s.charAt(i))) {
                /* Update the Left Most index */
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            /* Update the current index of Character */
            map.put(s.charAt(i), i);
            /* Upadte the current max length*/
            maxLen = Math.max(maxLen, i - j + 1);
        }
        
        return maxLen;
    }
}
```

## Fellow up

### Longest Substring with At Most Two Distinct Characters

To find a longest substring which contains "at most" two distinct characters

aaabbabacc -> aaabbab is the longest one

Used a Map to record last seen index of specific character, and a leftMost pointer to point to the left window, update the max Length when we move i to the next character.

If we have more than two characters in the map (Which mean there is a third character), we need to find the leftMost and used the current i and left pointer to get the current MAX length.


```java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length() == 0) return 0;
        int leftMost = 0;
        int maxLen = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
            /* If meet the third characters */
            if(map.size() > 2) {
                int indexToRemove = i;
                /* Find the leftMost and remove the farest character out of the map */
                for(int v: map.values()) {
                    indexToRemove = Math.min(indexToRemove, v);
                }
                
                /* Remove the Character from map and Update the LeftMost */
                map.remove(s.charAt(indexToRemove));
                leftMost = indexToRemove + 1;
            }
            
            /* Update the current Max Length */
            maxLen = Math.max(maxLen, i - leftMost + 1);
        }
        return maxLen;
    }
}
```


***

# 5 Longest Palindromic Substring



## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

```java
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        int leftLongest = 0;
        int rightLongest = 0;
        /* stored the boundry relationship with palindrome */
        boolean[][] isPalindrome = new boolean[len][len];
        
        /* Two pointer for the left and right window */
        for(int right = 1; right < len; right++) {
            for(int left = 0; left < right; left++ ) {
                
                /* See if the inner word is palindrome or if the right and left len smaller than 2 */
                boolean isInnerWordPalindrome = isPalindrome[left + 1][right - 1] || right - left <= 2;
                
                /* Check if the current characeter is the same and the ineer also Palindrome */
                if(s.charAt(left) == s.charAt(right) && isInnerWordPalindrome) {
                    isPalindrome[left][right] = true;
                    
                    /* Update the current leftLongest and rightLongest */
                    if(right - left > rightLongest - leftLongest) {
                        rightLongest = right;
                        leftLongest = left;
                    }
                }
            }
        }
        return s.substring(leftLongest, rightLongest + 1);
    }
}
```

## Fellow up

***

# 8 String to Integer

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***
