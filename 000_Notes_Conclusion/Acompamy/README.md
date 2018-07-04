# A company 

[1 Two Sum](#1-two-sum)
[2 Add Tow Numbers](#2-add-two-numbers)
[3 Longest Substring Without Repeating Characters](#3-longest-substring-without-repeating-characters)
[5 Longest Palindromic Substring](#5-longest-palindromic-substring)
[8 String to Integer](#8-string-to-integer)
[15 3 Sum](#15-3-sum)


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

Need to find the longest palindromic substring in the given string

(We could find the left and right boundry and use .substring method to return the result)

## Problem Analysis

Need a boolean to record the relationship between left and right boundry 

If the innerWords is Palinfromic, we only need to check the outside two pointer (If they are the same characters) and update the new left and right

## Algorithm Analysis

Given a leftLongest and rightLongest integer to track the longest Len

Used a left and right two pointer to loop through our string, rihgt from 1 outside and left from 0 to right inside.

Used a variable isInnerParlidrom to check left + 1 and right - 1, if left and right are nearby each other, then we skip this step

Check the characters ar left and right -> If they are the same and inner word is palidorm as well. Means that we could update the boolean and longest Left and Right.


## Time Complexity Analysis

Two pointer in the nested for loop -> O(n^2) and used an additional boolean 2D array -> O(n)

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

If we want to make 2 to k , just need to chagne the size() condition

but if we want a more efficient algorithm：

The basic idea is to traverse all the palindromes with its pivot range from the first char of string s to the last char of string s (consider both even length and odd length situation). Use StringBuilder to minimize the space complexity. 

```java
public class Solution {
StringBuilder longest = new StringBuilder("");

public String longestPalindrome(String s) {
    if (s.length() <= 1) return s;
    
    for (int i = 0; i < s.length(); i++) {
        expand(s, longest, i, i); //odd
        expand(s, longest, i, i + 1); //even
    }
    
    return longest.toString();
}

private void expand(String s, StringBuilder longest, int i, int j) {
    while (i >= 0 && j < s.length()) {
        if (s.charAt(i) == s.charAt(j)) {
            if (j - i + 1 > longest.length()) {
                longest.delete(0, longest.length());
                longest.append(s.substring(i, j + 1));
            }
            i--;
            j++;
        }
        else
            break;
    }
}

```

***

# 8 String to Integer

## Problem Analysis

- First not whitespace could not be characters
- The string could not just include whitespace
- If the string is empty, just return 0
- Need to consider the sign -> Return signe * total
- Need to consider the MAX_VALUE and MIN_VALUE (If current total * 10 + digit will pass the MAX_VALUE)
- Deal with the largest (earliest) digit and * 10 + digit

## Algorithm Analysis

- Need to understand the conditions!

## Time Complexity Analysis
- O(n) time complexity

## Code

```java
class Solution {
    public int myAtoi(String str) {
        int index = 0, sign = 1, sum = 0;
        
        /* Deal with empty str */
        if(str.length() == 0) return 0;
        
        /* Remove the white space */
        while(index < str.length() && str.charAt(index) == ' ') {
            index++;
        }
        
        /* Check if the whole string are whitespaces */
        if(index == str.length()) return 0;
        
        /* Check the first character */
        if(str.charAt(index) >= 'a' && str.charAt(index) <= 'z') return 0;
        
        /* Get the Sign */
        if(str.charAt(index) == '+' || str.charAt(index) == '-' ) {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }
        
        /* Convert to the actual integer and make sure it won't overflow */
        while(index < str.length()) {
            int digit = str.charAt(index) - '0';
            
            /* Make sure to dismiss the rest of string which is not digits*/
            if(digit < 0 || digit > 9) break;
            
            /* sum * 10 > MAX VALUE || sum * 10 + digit > MAX VALUE */
            if(Integer.MAX_VALUE / 10 < sum || Integer.MAX_VALUE / 10 == sum && Integer.MAX_VALUE % 10 < digit) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            sum = sum * 10 + digit;
            index++;
        }
        return sum * sign;
    }
}
```


***

# 15 3Sum

## Problem Analysis

Firstly, we need to ask some problems about the conditions.

- Is the answer has a duplicated number? (If no, need to deal with the duplicated number in the original array)

- Is the original array has been sorted? (If no, sorting an array take O(nlogn) time complexity)

- Is the original array only have unique number? (If no, again, need to consider how to dedupliactd -> use while loop to find next not same value)


## Algorithm Analysis

Used a for loop to go through each number from i = 0 to i < len - 2.

(Need to deal with the duplicated number -> i == 0 || nums[i] != nums[i-1])

The rest of the number seems to be the 2 sum problem!

Similaryly, we used a start and end pointer to find the sum is equal to target or larger or less than target.


Then, deal with the duplicated number in the rest of numbers

```
if(nums[start] == nums[currentStart] && start < end) start ++
if(nums[end] == nums[currentEnd] && start < end) end--.
```

## Time Complexity Analysis

O(n^2) + O(nlogn) time complexity and O(1) space complexity

## Code
- Version 1: Didn't deal with the rest of number when meet the target, put it into the rest of while loop

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
      
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 2; i++) {
            if(i == 0 || nums[i] > nums[i - 1]) {
                int start = i + 1;
                int end = nums.length - 1;
                
                while(start < end) {
                    if(nums[i] + nums[start] + nums[end] == 0) {
                        res.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    } 
                    
                    /* nums[i] + nums[start] + nums[end] < 0 */
                    if(nums[i] + nums[start] + nums[end] < 0) {
                        int currentStart = start;
                        while(nums[start] == nums[currentStart] && start < end) {
                            start++;
                        }
                    }
                    /* num[i] + nums[start] + nums[end] >= 0 */
                    /* Even we had found the target still need to try the rest of numbers*/
                    else {
                        int currentEnd = end;
                        while(nums[end] == nums[currentEnd] && start < end) {
                            end--;
                        }
                    }
                }
            }
        }
        return res;
    }
}
```

- Version 2: Check if there is a duplicated number when their in an answer List be added into result. (更有效率)

```java
class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>(); 
        for (int i = 0; i < num.length-2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
               }
            }
        }
        return res;
    }
}
```

## Fellow up

### 16 3 Sum Closest

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        /* Init the result as first three elements */
        int result = nums[0] + nums[1] + nums[2];
        
        for(int i = 0; i < len - 2; i++) {
            int start = i + 1;
            int end = len - 1;
            
            while(start < end) {
                /* get the current sum */
                int sum = nums[i] + nums[start] + nums[end];
                if(sum == target) return target;
                
                /* Modify two pointers */
                if(sum < target) {
                   start ++;
                } else {
                    end --;
                }
                
                /* Compare with the result to see if the current sum is the cloest*/
                if(Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }
            }

        }
        return result;
    }
}
```

### 18 K Sum


```java
public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {

        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        // state
        // f[i][j][t] take j numbers from the first i numbers, how many combinations' sum is t
        int[][][] f = new int[n + 1][k + 1][target + 1];

        // initialize
        for (int i = 0; i <= n; i++) {
            f[i][0][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    f[i][j][t] = f[i - 1][j][t];
                    /* 如果目前的 t >= A[i - 1] */
                    /* f(i,j,t) = f(i-1,j,t) + f(i-1,j-1,t) - A[i - 1] */
                    if (t >= A[i - 1]) {
                        f[i][j][t] = f[i - 1][j][t] + f[i - 1][j - 1][t - A[i - 1]];
                    }
                }
            }
        }

        return f[n][k][target];
    }
}
```

***

# 17 Letter Combinations of a Phone Number

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***

# 20 Valid Parentheses

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code
```java
class Solution {
    public boolean isValid(String s) {
        if(s.length() == 0) return true;
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if(curChar == '{' || curChar == '[' || curChar == '(') {
                /* Meet the front and push into the Stack*/
                stack.offerFirst(curChar);
            } else {
                /* Meet the back and check in the Stack */
                if(stack.isEmpty()) return false;
                char peekChar = stack.pollFirst();
                if(curChar == '}' && peekChar != '{') return false;
                else if(curChar == ')' && peekChar != '(') return false;
                else if(curChar == ']' && peekChar != '[') return false;
            }
        }
        return stack.isEmpty();
    }
}
```

***

# 21 Merge Two Sorted Lists 

## Problem Analysis

Comparing with both l1 and l2, and then add the rest of l1 or l2 into main linkedlist

## Algorithm Analysis

Used a dummy node and cur pointer to solve the problem

## Time Complexity Analysis

Time complexity = O(n)

## Code

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(l1 != null && l2 != null) {
            if(l1.val > l2.val) {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            cur = cur.next;
        }
        
        if(l1 != null) {
            cur.next = l1;
        }
        
        if(l2 != null) {
            cur.next = l2;
        }
        
        return dummy.next;
    }
}
```


# 23 Merge k Sorted Lists


## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up


***

# 42 Trapping Rain Water 

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***

# 48 Rotate Image

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

