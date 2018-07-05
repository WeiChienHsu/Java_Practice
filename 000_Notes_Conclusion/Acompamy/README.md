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

Get the combination ! -> DFS + backtracking

## Algorithm Analysis

BackTracking Algorithm + DFS Helper

dfsHelper: original String(to go through digits), result array, combination in StringBuilder(Save space), indexOfDigit (the pointer for the digits)

In the dfsHelper: base case is when indexOfDigit is equal the length of originl String which means we have already went through whole digits. Add the current combination (temp result) into the result.

The feature of combination is to recored the index of currentString from the for loop. Since each digit represented a signal string which has three charaters so we use a for loop to implement the backtracking algorithm. Add the specifc character at i in the current String into combination and put those variables into next DFS (with indexOfDigit + 1) which mean to pass the next digit in original string. After passing the dfsHelper when we meet the base case and return back to this level, remove the last character in the combination to the next i + 1 loop.

## Time Complexity Analysis

The time complexity of this should be 3^n, where n is the number of digits. For each digit you have 3 possible characters (excluding 9), and then for each subsequent digit, you get another 3 more possible characters per character of the previous digit.

O(N^3) if we use third for loop to solve
opt: O(N^2) : Backtracking

## Code

```java
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length() == 0) return result;
        StringBuilder combination = new StringBuilder();
        dfsHelper(digits, 0, result, combination);
        return result;
    }
    
    /* a -> ad -> a -> ae -> a -> af -> a -> b -> bd -> b -> bf -> .... -> c */
    public void dfsHelper(String digits, int indexOfDigits, List<String> result, StringBuilder combination) {
        if(indexOfDigits == digits.length()) {
            result.add(combination.toString());
            return;
        }
        
        /* get the current String by using the arugment of indexOfDigits to go through the next digit in the inpit */
        /* ex. 2 for  "abc" -> 3 for "def"  */
        String currentString = getString(digits.charAt(indexOfDigits));
        
            /* Get the first word and second word and third word in different level */
            /* Put the word into the StringBuilder and give it for the next handler */
            /* ex. "abc" chose "a" and put it in the dfs helper with index + 1, later, in "def" chose 'd' for next character*/
            /* a -> b -> c  d -> e -> f */
        
        for(int i = 0; i < currentString.length(); i++) {
            combination.append(currentString.charAt(i));
            dfsHelper(digits, indexOfDigits + 1, result, combination);
            /* Backtracking - Remove the laste character in the StringBuilder */
            combination.deleteCharAt(combination.length() - 1);
        }
    }
    
    public String getString(char num) {
        switch (num) {
            case '2':
                return "abc";
            case '3':
                return "def";
            case '4':
                return "ghi";
            case '5':
                return "jkl";
            case '6':
                return "mno";
            case '7':
                return "pqrs";
            case '8':
                return "tuv";
            case '9':
                return "wxyz";
        }
        return "";
    }
}
```


***

# 20 Valid Parentheses

## Problem Analysis

Stack problem

## Algorithm Analysis

the front one store into a Stack and when we meet the back one, check if there is the pair in our Stack peek (poll out to check), need to take care of one specific situation when the first meet is on the back, Stack could not be empty!

Return Stack.isEmpty() to check if all front are being matched.

## Time Complexity Analysis
O(n)

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

Merege K Sorted Lists

## Algorithm Analysis

Actually, this is the fellow up problem of merge two sorted arrays.

Used a dummy node to point to the first list, and keep merging the rest of the array with dummy.next and return the value to duumy.next as well.

## Time Complexity Analysis

O(N)

## Code

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if(len == 0) return null;
        
        ListNode dummy = new ListNode(0);
        dummy.next = lists[0];
        
        for(int i = 1; i < len; i++) {
            dummy.next = mergeTwoLists(dummy.next, lists[i]);
        }
        
        
        return dummy.next;
    }
    
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

- Tricky Solution

## Algorithm Analysis

```
Switch Up and Down 

for i = 1 to i < row
  for j = 0 to j < r
Switch matrix[r][c] and matric[c][r]
```

## Time Complexity Analysis

O(n * m)

## Code

```java
/* Clockwise Rotate */
public void rotate(int[][] matrix) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
    int rows = matrix.length;
    int cols = matrix[0].length;
    for(int first=0, last=rows-1; first<last; first++,last--) {
        int[] tmp = matrix[first];
        matrix[first] = matrix[last];
        matrix[last] = tmp;
    }
    for(int i = 0; i < rows; i++){
        for(int j = i+1; j < cols; j++){
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = tmp;
        }
    }
}

/* Counter-clockwise Rotate */
public void antiRotate(int[][] matrix) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
    int rows = matrix.length;
    int cols = matrix[0].length;
    for(int first=0, last=cols-1; first<last; first++,last--) {
        for(int i=0; i<matrix.length; i++) {
            int tmp = matrix[i][first];
            matrix[i][first] = matrix[i][last];
            matrix[i][last] = tmp;
        }
    }
    for(int i = 0; i < rows; i++){
        for(int j = i+1; j < cols; j++){
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = tmp;
        }
    }
}
```
# 49| Group Anagrams

## Problem Analysis

- Check the duplicated we could think about using a Map.

- If order doesn't matter why not just sort it to figure the answer.

- What we should store in the Map

## Algorithm Analysis

Switch String into a Character Array and sort that array to get a key String. Used that keyString to recoginzed if the string is same as previous one. If the string has existed in the Map, just add that one into the List<String> in the Map.

## Time Complexity Analysis
O(nlogn) additional O(n)

## Code
```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String keyStr = String.valueOf(c);
            
            /* Chekch if the key String has already exisited in the Map */
            if(!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<String>());
            }
            map.get(keyStr).add(strs[i]);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
```


***

# 73| Set Matrix Zeroes

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis
O(M*N)

## Code

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        if(row == 0) return;
        int col = matrix[0].length;
        if(col == 0) return;
        
        List<int[]> meetZeroes = new ArrayList<>();
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 0) meetZeroes.add(new int[]{i,j}); 
            }
        }
        
        for(int k = 0; k < meetZeroes.size(); k++) {
            int[] zeroPos = meetZeroes.get(k);
            makeRowZeroes(matrix, zeroPos[0]);
            makeColZeroes(matrix, zeroPos[1]);
        }
    }
    
    public void makeRowZeroes(int[][] matrix, int row) {
        for(int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] = 0;
        }
    }
    
    public void makeColZeroes(int[][] matrix, int col) {
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }
}
```

## Fellow up - Optimization

Stored the Status about have Zero or not in the fiset Col and first Row.

Then, loop through again the rest of the marix (i = 1 to row and j = 1 to col), if meet it [i][0] ot [0][j] is 0 -> chagne it's value to 0

Lately, change the first row and first col into 0 if needed.

```java
public void setZeroes(int[][] matrix) {
    boolean fr = false,fc = false;
    for(int i = 0; i < matrix.length; i++) {
        for(int j = 0; j < matrix[0].length; j++) {
            if(matrix[i][j] == 0) {
                if(i == 0) fr = true;
                if(j == 0) fc = true;
                matrix[0][j] = 0;
                matrix[i][0] = 0;
            }
        }
    }
    for(int i = 1; i < matrix.length; i++) {
        for(int j = 1; j < matrix[0].length; j++) {
            if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    if(fr) {
        for(int j = 0; j < matrix[0].length; j++) {
            matrix[0][j] = 0;
        }
    }
    if(fc) {
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 0;
        }
    }
}
    // Use first row and first column as markers. 
    // if matrix[i][j] = 0, mark respected row and col marker = 0;  
    // indicatingthat later this respective row and col 
    // must be marked 0;
    
    // And because you are altering first row and collumn, 
    // you need to  have two variables to track their own status. 
    // So, for ex, if any one of the first row is 0, fr = 0, 
    // and at the end set all first row to 0;
```

***

# 78| Subsets  

## Problem Analysis

Backtracking - Used the number from first to the last, when we go through the first number, in the dfs, we need a for loop to deal with the rest of its number, and add a signle number into the tempList, put tempList and current index + 1 (which mean 2 as start point) into the next Level.


## Algorithm Analysis

```
 1 -> 2 -> 3
 | \   \
 12 13 23
 |
 123
```
## Time Complexity Analysis

O(2^n)

## Code

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        // Arrays.sort(nums);
        dfsHelper(nums, res, tempList, 0);
        return res;
    }
    
    public void dfsHelper(int[] nums, List<List<Integer>> res, List<Integer> tempList, int startIndex) {
        res.add(new ArrayList<Integer>(tempList));
        for(int i = startIndex; i < nums.length; i++) {
            tempList.add(nums[i]);
            dfsHelper(nums, res, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}

```

- Not using Backtracking

```java
public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(int n : nums){
            int size = result.size();
            for(int i=0; i<size; i++){
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(n);
                result.add(subset);
            }
        }
        return result;
    }
```

## Fellow up


### Subset II (Contains duplicates)

```java

public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){

    list.add(new ArrayList<>(tempList)); /* Add current temp List into the result */
    for(int i = start; i < nums.length; i++){
        if(i > start && nums[i] == nums[i-1]) continue;  /* Skip the duplicated */
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1); 
        tempList.remove(tempList.size() - 1);
        /* new Start point will be the current index + 1 */
    }
} 
```

### Permutations

[Permutations](https://leetcode.com/problems/permutations/)

```java
public List<List<Integer>> permute(int[] nums) {
   List<List<Integer>> list = new ArrayList<>();
   // Arrays.sort(nums); // not necessary
   backtrack(list, new ArrayList<>(), nums);
   return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
   if(tempList.size() == nums.length){
      list.add(new ArrayList<>(tempList));
   } else{
      for(int i = 0; i < nums.length; i++){ 
         if(tempList.contains(nums[i])) continue; // element already exists, skip
         tempList.add(nums[i]);
         backtrack(list, tempList, nums);
         tempList.remove(tempList.size() - 1);
      }
   }
} 
```

### Permutations (Contains duplicate)

[Permutations II (contains duplicates)](https://leetcode.com/problems/permutations-ii/)

```java
public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
    if(tempList.size() == nums.length){
        list.add(new ArrayList<>(tempList));
    } else{
        for(int i = 0; i < nums.length; i++){
            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
            used[i] = true; 
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, used);
            used[i] = false; 
            tempList.remove(tempList.size() - 1);
        }
    }
}

```

### Combination Sum

```java
public List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
    if(remain < 0) return;
    else if(remain == 0) list.add(new ArrayList<>(tempList));
    else{ 
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
            tempList.remove(tempList.size() - 1);
        }
    }
}
```

### Combinatino Sum II (Cant reuse same element)

```java
public List<List<Integer>> combinationSum2(int[] nums, int target) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, target, 0);
    return list;
    
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
    if(remain < 0) return;
    else if(remain == 0) list.add(new ArrayList<>(tempList));
    else{
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i + 1);
            tempList.remove(tempList.size() - 1); 
        }
    }
} 
```


***

# 89| Gray Code 

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***

# 98| Validate Binary Search Tree 

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***

# 119 Pascal's Triangle 

## Problem Analysis

Find the rule in pascal's triangle 

Dp Problem -> Save the previous result for the future result

## Algorithm Analysis

Add 1 int the head of result.

From the second to the last - 1, number of i will be the value of sum i and i + 1.

## Time Complexity Analysis

O(rowIndex) -> O(n)

## Code

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        
        while(rowIndex >= 0) {
            /* Add 1 in the "head" of result list */
            res.add(0, 1);
            
            /* from the second element, modify it's value by adding number on i and i + 1 */
            for(int i = 1; i < res.size() - 1; i++) {
                res.set(i, res.get(i) + res.get(i + 1));
            }
            rowIndex --;
        }
        
        return res;
    }
}
```

***

# 121| Best Time to Buy and Sell Stock 

[Youtube](https://www.youtube.com/watch?v=8pVhUpF1INw)

## Problem Analysis

Need to find a max_profit = max{price[j] - price[i]} (n - 1 > j > i > 0)

Buy: price[i] -> min{princ[k]} k <= i
Sell: princ[j] -> max{princ[k]} k >= j

## Algorithm Analysis - DP

Find the min Price so far to i-th day save in the L array
Find the max Profit so far by the max of price[j] - L[j] or P[j - 1].

Return P[n - 1]

## Time Complexity Analysis

O(n) time complexity, O(n) space -> O(1)

## Code

- DP: O(N) space

```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n < 2) return 0;
        
        int[] low_price = new int[n];
        int[] high_profit = new int[n];
        low_price[0] = prices[0];
        high_profit[0] = 0;
        
        for(int i = 1; i < n; i++) {
            low_price[i] = Math.min(prices[i], low_price[i - 1]);
            high_profit[i] = Math.max(high_profit[i - 1], prices[i] - low_price[i]);
        }
        
        return high_profit[n - 1];
        
    }
}
```

- DP: O(1) space

```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n < 2) return 0;
        
        int[] high_profit = new int[n];
        high_profit[0] = 0;
        
        for(int i = 1; i < n; i++) {
            /* Update the max profit by choosing last highest profit or current price - last min price */
            high_profit[i] = Math.max(high_profit[i - 1], prices[i] - Math.min(prices[i], prices[i - 1]));
            /* Update the min price so far to i */
            prices[i] = Math.min(prices[i], prices[i - 1]);
        }
        return high_profit[n - 1];   
    }
}
```

## Fellow up

***

# 126| Word Ladder II 

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***

# 127| Word Ladder

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***

# 138| Copy List with Random Pointer

## Problem Analysis

Copy whole Linked List with next and random pointers.

## Algorithm Analysis

Used a Map to store relationship between old Node and new Node.

Loop Thorugh linked list once to record those old and new Nodes.

Used a dummy node to point the the head of new node.

In a while loop, use the head poiner to get new Node from map.

1. connect with next. (We get a whole new lists)
2. connect with random. (Might have a new Node or not)
3. Move the head forward until it meet the null


## Time Complexity Analysis

O(2n)

## Code
```java
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        
        if(head == null) return null;
        
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        
        /* Copy all the nodes into the Map <oldNode, newNode> */
        RandomListNode cur = head;
        while(cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        
        /* Connect the next and random with newNode */
        RandomListNode dummy = new RandomListNode(0);
        dummy.next = map.get(head);
        
        while(head != null) {
            /* connect the current new Node with next/random newNode 
            by mapping with old next/random oldNode */
            
            map.get(head).next = map.get(head.next);
            map.get(head).random = map.get(head.random);
            head = head.next;
        }
        
        return dummy.next;
  
    }
}
```

***

# 139| Word Break

## Problem Analysis

We need to find a breaking point for the substring and record that state then go through the whole string to see the result.

### DFS Solution (Recursion + Memorized)

- Improved from the Brute Force.

- Used a memory set to record the previous substring false (for example, substring cut on specific index would be found in the dictionar). Check from the first index and use a for loop form index + 1 to get the substring, check this substring, if this substring was not in the dict, go for the next i, if yes, put it's rest of the substring in to the dfsHelper to see the result.

- If the for loop end, there is not Substring inside the dict, mark that index as fales and return a false.

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        /* Set is much easier to find existed String*/
        Set<String> dict = new HashSet<>();
        for(String str : wordDict) {
            dict.add(str);
        }
        
        /* Record those substring which could not be found in the dict */
        Set<Integer> memory = new HashSet<>();
        
        return canBreak(s, 0, dict, memory);
    }
    
    public boolean canBreak(String s, int index, Set<String> dict, Set<Integer> memory) {
        /* Base Case */
        if(index == s.length()) return true;
        
        /* Check if current index is in the memory */
        if(memory.contains(index)) return false;
        
        for(int i = index + 1; i <= s.length() ; i++) {

            String subString = s.substring(index, i);
            
            /* Check left substring and put right substring into dfs helper function */
            if(dict.contains(subString) && canBreak(s, i, dict, memory)) {
                return true;
            }
        }
        
        /* Couldn not be found in the dict */
        memory.add(index);
        return false;
    }
}
```

### DP Solution

For example, we got s = abcdfeg and diction {abc, de, fg}.

Used a dp to record previous state of wheter that substring could be found in the dictionary.

- State = boolean[n+1] : Recornd the substring with lengh = i could or could not be found

- Init = dp[0] = true
- Fucntion : [i] = from j = i to j = 1, .substring(i - j, i) to see if this substring was in the dict -> by seeing boolean[i - j]
- Return dp[n]

Take abcde for example, when j = 3, substring(3, 5) de is in the dict, then check if substring(0, 3) which is abc in the dict by looking into boolean[3].


O(N^2)

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || wordDict == null) return false;
        HashSet<String> dict = new HashSet<String>();
        for(String str : wordDict) {
            dict.add(str);
        }
        
        boolean[] breakable = new boolean[s.length() + 1];
        breakable[0] = true;
            
        for(int i = 0; i <= s.length(); i++) {
            for(int j = i; j > 0; j--) {
                /* Get the substring from i - j to i*/
                String subString = s.substring(i - j, i);
                /* check if the current substring is in the dict */
                if(dict.contains(subString)) {
                    /* if the previous strings is breakable */
                    if(breakable[i - j] == true) {
                        /* mark the current substring + previous substring as true */
                        breakable[i] = true;
                        break;
                    }
                }
            }
        }
        return breakable[s.length()];
    }
}
```

## Fellow up



***

# 151 Reverse Words in a String

## Problem Analysis

Split the single word and rebuilt it in a reversed order

## Algorithm Analysis

- regular expression: + means at least 1 so in this case " +" means at least one space
- Used String.split() and String.trim().
- Used a StringBuilder.append() and StringBuilder.toString()

## Time Complexity Analysis

O(n) time and O(n) space

## Code
```java
public String reverseWords(String s) {
    String[] sArr = s.trim().split(" +");
    StringBuilder sb = new StringBuilder();
    for(int i = sArr.length - 1; i >= 0; i--) {
        sb.append(sArr[i].trim());
        sb.append(" ");
    }
    return sb.toString().trim();
}
```

## Fellow up

### Reverse Words in String II

Given a char array and reverse the string word by word.

- A word is defined as a sequence of non-space characters.
- The input string does not contain leading or trailing spaces.
- The words are always separated by a single space.

```java
class Solution {
    public void reverseWords(char[] str) {
        /* Reverse whole char Array */
        reverse(str, 0, str.length - 1);
        
        int currentStart = 0;
        int currentEnd = str.length - 1;
        
        /* Reverse single word except last word */
        for(int i = 0; i < str.length; i++) {
            if(str[i] == ' ' && i > 0) {
                currentEnd = i - 1;
                reverse(str, currentStart, currentEnd);
                currentStart = i + 1;
            }
        }
        
        /* Reverse last word */
        reverse(str, currentStart, str.length - 1);
    }
    /* Reverse Helper */
    public void reverse(char[] str, int start, int end) {
        while(start < end) {
            char temp = str[start];
            str[start++] = str[end];
            str[end--] = temp;
        }
    }
}
```


***


# 102 Binary Tree Level Order Traversal

## Problem Analysis

We need to print the Node in Tree by a Level Order Traversal way

## Algorithm Analysis

Used A Queue! push each leavel and record it. After recording single node, check if it's left and right exist, if exist push then into the queue for next level in their order. While queue is empty means we had already traversed the whole tree. 

## Time Complexity Analysis

O(N) 

## Code

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode cur = queue.pollFirst();
                list.add(cur.val);
                if(cur.left != null) queue.offerLast(cur.left);
                if(cur.right != null) queue.offerLast(cur.right);
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }
}
```
