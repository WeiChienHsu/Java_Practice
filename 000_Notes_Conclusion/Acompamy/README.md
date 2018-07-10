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

記得，每次遇到重複時，是要更新起點 j ，而不是直接更新最大長度，應該在每次更新 Map 的時候，同時更新最大長度。

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

因為要回傳最長的 Result ， 所以必須要有 rightLongest 和 leftLongest 來記錄邊界，不段的檢測 inner 是不是 palindrome ，如果內部是，或是左右差距小於等於二，直接更新目前左右指標位置也是 palindrom。

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

當找到 nums[i] + nums[start] + nums[end] 之後，結果加入 list 中 (Arrays.asList())，

要先檢查 start end 下個結果有沒有 duplicated，然後在移動 start ++ end -- 不然會是死循環。

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

O(n^2) time complexity and O(1) space complexity

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
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 2; i ++) {
            /* Deduplicated */
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            
            int curNumber = nums[i];
            /* Find the target - curnumber */
            int start = i + 1;
            int end = nums.length - 1;
            
            while(start < end) {
                if(curNumber + nums[start] + nums[end] == 0) {
                    /* Found the result, add into list */
                    result.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    
                    /* Deduplicated */
                    while(start < end && nums[start] == nums[start + 1]) start++;
                    while(start < end && nums[end] == nums[end - 1]) end--;
                    
                    /* Move forward to next pair */
                    start ++;
                    end --;
                        
                } 
                else if(curNumber + nums[start] + nums[end] > 0) {
                    /* Result is too large */
                    end --;
                } 
                else {
                    /* Result is too small */
                    start ++;
                }
            }
        }
        return result;
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

```java
  public List<List<String>> findLadders(String start, String end, Set<String> dict) {
    // hash set for both ends
    Set<String> set1 = new HashSet<String>();
    Set<String> set2 = new HashSet<String>();
    
    // initial words in both ends
    set1.add(start);
    set2.add(end);
    
    // we use a map to help construct the final result
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    
    // build the map
    helper(dict, set1, set2, map, false);
    
    List<List<String>> res = new ArrayList<List<String>>();
    List<String> sol = new ArrayList<String>(Arrays.asList(start));
    
    // recursively build the final result
    generateList(start, end, map, sol, res);
    
    return res;
  }
  
  boolean helper(Set<String> dict, Set<String> set1, Set<String> set2, Map<String, List<String>> map, boolean flip) {
    if (set1.isEmpty()) {
      return false;
    }
    
    if (set1.size() > set2.size()) {
      return helper(dict, set2, set1, map, !flip);
    }
    
    // remove words on current both ends from the dict
    dict.removeAll(set1);
    dict.removeAll(set2);
    
    // as we only need the shortest paths
    // we use a boolean value help early termination
    boolean done = false;
    
    // set for the next level
    Set<String> set = new HashSet<String>();
    
    // for each string in end 1
    for (String str : set1) {
      for (int i = 0; i < str.length(); i++) {
        char[] chars = str.toCharArray();
        
        // change one character for every position
        for (char ch = 'a'; ch <= 'z'; ch++) {
          chars[i] = ch;
          
          String word = new String(chars);
          
          // make sure we construct the tree in the correct direction
          String key = flip ? word : str;
          String val = flip ? str : word;
              
          List<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();
              
          if (set2.contains(word)) {
            done = true;
            
            list.add(val);
            map.put(key, list);
          } 
          
          if (!done && dict.contains(word)) {
            set.add(word);
            
            list.add(val);
            map.put(key, list);
          }
        }
      }
    }
    
    // early terminate if done is true
    return done || helper(dict, set2, set, map, !flip);
  }
  
  void generateList(String start, String end, Map<String, List<String>> map, List<String> sol, List<List<String>> res) {
    if (start.equals(end)) {
      res.add(new ArrayList<String>(sol));
      return;
    }
    
    // need this check in case the diff between start and end happens to be one
    // e.g "a", "c", {"a", "b", "c"}
    if (!map.containsKey(start)) {
      return;
    }
    
    for (String word : map.get(start)) {
      sol.add(word);
      generateList(word, end, map, sol, res);
      sol.remove(sol.size() - 1);
    }
  }
  ```

***

# 127| Word Ladder

## Problem Analysis

- Like a BFS Level Traversal problem, need to try every possible words of the current Words.

## Algorithm Analysis

Used a visited Set to record the Path (Word could not be used for twice)

Used a dict Set to search for wordList : O(n) -> O(1)

Used a Queue to record next possible words. 

Start form the begin Word, put it into the Queue, and use a Queue to do the level traversal, for loop in the size of queue, and chcek if the current word equals to the end word.

How to do the bfs? Used a double for loop to swtich words in the all possible positions in the String and skip the duplicated one. Then, check if this new word is both in the word list and not been used before.

If this is a possible word, push it into the queue and marked as used!

Before each level, we increment or steps rocorder. If met the end word, return that recorder. Else, in the end return 0.

## Time Complexity Analysis

## Code

```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /* Set to record our used words */
        Set<String> used = new HashSet<>();
        /* Set to store the wordList */
        Set<String> dict = new HashSet<>();
        for(String str : wordList) {
            dict.add(str);
        }
        /* Queue the handle the level processing of word converted */
        Deque<String> queue = new ArrayDeque<>();
        queue.offerLast(beginWord);
        used.add(beginWord);
        
        /* Check if the end word is in the wordList */
        if(!dict.contains(endWord)) return 0;
        
        int steps = 0;
        
        /* Level Traverse the Queue */
        while(!queue.isEmpty()) {
            steps ++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String currentWord = queue.pollFirst();

                if(currentWord.equals(endWord)) return steps;
                /* bfsHelper funciton helps to convert all possible word */
                bfsHelper(currentWord, endWord, used, dict, queue);
            }
        }
        
        /* There is no way to convert to the endWord */
        return 0;
    }
    
    public void bfsHelper(String currentWord, String endWord, Set<String> used, Set<String>dict, Deque<String> queue) {
        for(int i = 0; i < currentWord.length(); i++) {
            char[] currentCharArray = currentWord.toCharArray();
            /* change mulitple characters and different position */
            for(char c = 'a'; c <= 'z'; c++) {
                if(currentWord.charAt(i) == c) continue;
                currentCharArray[i] = c;
                String word = String.valueOf(currentCharArray);
                
                if(dict.contains(word) && !used.contains(word)) {
                    queue.offerLast(word);
                    used.add(word);
                }
            }
        }
    }
}
```

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

# 141| Linked List Cycle

## Algorithm Analysis

- Fast and Slow pointer
- If there is a loop, they will meet each other in the future
- If fast pointer meet the null, mean there is no loop.

## Code
```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        
        return false;
    }
}
```

***

# 146 LRU Cache

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

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

# 155 Min Stack

## Problem Analysis

Used a Linked List Structure to create a min Stack class

## Algorithm Analysis

init a head pointer a first.

In the Node class, we need to record the current Min value and node's val.

Push -> Check if there is already have node connected with HEAD pointer, if no, point Head to the new Node, if yes, create a new node with it's min value compare with the peek and connect to head. Then, use Head to point that new Node.

Pop -> if head != null, move head forward

Top -> Return the value of head

getMin -> Return the current Min of head

## Code

```java
class MinStack {
    
    private Node head;

    /** initialize your data structure here. */
    public MinStack() {
        /* no need to do anything when constructing */
    }
    
    public void push(int x) {
        if(head == null) {
            head = new Node(x, x);
        } else {
            Node newNode = new Node(x, Math.min(x, head.curMin));
            newNode.next = head;
            head = newNode;
        }
    }
    
    public void pop() {
        if(head != null) {
            head = head.next;
        }
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.curMin;
    }
}

class Node {
    int val;
    int curMin;
    Node next;
    public Node(int val, int curMin) {
        this.val = val;
        this.curMin = curMin;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

```

## Fellow up

***

# 160 Intersection of Two Linked Lists

## Algorithm Analysis

Find the different between two original linked lists and move forward that steps to make them have same distance with the end.

Modify the longer one and move them in the same path to find the same node.

## Time Complexity Analysis

O(2n)

## Code
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int diff = 0;
        
        if(headA == null || headB == null) return null;
        
        /* Find the different between lA and lB */
        while(curA.next != null && curB.next != null) {
            curA = curA.next;
            curB = curB.next;
        }
        
        /* Finish the rest of Node and move forward the same steps for the head */
        while (curA != null && curA.next != null) {
            headA = headA.next;
            curA = curA.next;
        }
        
        while (curB != null && curB.next != null) {
            headB = headB.next;
            curB = curB.next;
        }
        
        /* headA and headB now have same distance to the end */
        /* Move headA and headB together until they meet the same node or return null*/
        while(headA != headB && headA != null & headB != null) {
            headA = headA.next;
            headB = headB.next;
        }
        
        return headA == headB ? headA : null;
        
    }
}
```

***

# 167| Two Sum II - Input array is sorted

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***

# 189| Rotate Array 

## Problem Analysis

Find the new Array's first element and put the rest of number in to the new Array from the given array by k.

Try to do it in-place with O(1) extra space:

Reverse the 0 -> k - 1 number and reverse the k -> end number and reverse whole array.

## Time Complexity Analysis

O(n) time complexity O(n) Space -> O(1) Space

## Code
- O(N) space
```java
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length; 
        int[] newArr = new int[nums.length];
        int count = 0;
        
        for(int i = nums.length - k; i < nums.length; i++) {
            newArr[count++] = nums[i];
        }
        
        for(int j = 0; j < nums.length - k; j++) {
            newArr[count++] = nums[j];
        }
        
        for(int c = 0; c < nums.length; c++) {
            nums[c] = newArr[c];
        }
    }
}
```

- O(1) space

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % nums.length; 
        reverse(nums, 0, len - 1 - k);
        reverse(nums, len - k, len - 1);
        reverse(nums, 0, len - 1);
        
    }
    
    public void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
```

## Fellow up

***

# |199| Binary Tree Right Side View |

## Algorithm Analysis

Level Traversal and record the last Node.

## Code

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            TreeNode currentNode = null;
            for(int i = 0; i < size; i++) {
                currentNode = queue.pollFirst();
                if(currentNode.left != null) queue.offerLast(currentNode.left);
                if(currentNode.right != null) queue.offerLast(currentNode.right);
            }
            res.add(currentNode.val);
        }
        return res;
    }
}
```

***

# 200| Number of Islands

## Problem Analysis

DFS

## Algorithm Analysis

DFS Problem, traverse whole matrix, if found "1", put it into DFS helper funciton.

Dfs helper function : Change current value to "0" and reaverse the surrond index if there is another 1 and put it into Dfs Helper function. (Check validation and value inside the dfs function)

Try not to change the input value by using another boolean array to record the visited

## Time Complexity Analysis

O(row * column)

## Code

- Modify the input array 

```java
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        int row = grid.length;
        if(row == 0) return 0;
        int col = grid[0].length;
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    dfsHelper(grid, i, j, row, col);
                }
            }
        }
        
        return count;
    }
    
    public void dfsHelper(char[][] grid, int r, int c, int row, int col) {
        /* Directions helper array */
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        /* Change the current land to sea */
        grid[r][c] = '0';
        
        /* Traverse the land's surrounded lan */
        for(int[] dir : directions) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];
            /* check validation and if the nearby is '1' */
            if(newRow >= 0 && newRow < row && newCol >= 0 && newCol < col && grid[newRow][newCol] == '1') {
                dfsHelper(grid, newRow, newCol, row, col);
            }
        }
        
    } 
}
```

- Used another space

```java
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        
        int row = grid.length;
        if(row == 0) return 0;
        int col = grid[0].length;
        
        boolean[][] visited = new boolean[row][col]; /* Default fales */
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    dfsHelper(grid, i, j, row, col, visited);
                }
            }
        }
        
        return count;
    }
    
    public void dfsHelper(char[][] grid, int r, int c, int row, int col, boolean[][] visited) {
        /* Directions helper array */
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        /* Change the current land to sea */
        visited[r][c] = true;
        
        
        /* Traverse the land's surrounded lan */
        for(int[] dir : directions) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];
            /* check validation and if the nearby is '1' */
            if(newRow >= 0 && newRow < row && newCol >= 0 && newCol < col 
               && grid[newRow][newCol] == '1' && !visited[newRow][newCol]) {
                dfsHelper(grid, newRow, newCol, row, col, visited);
            }
        }
        
    } 
}
```

## Fellow up

***

# 204| Count Primes

## Problem Analysis

We could not chech every number since it will take O(n^2) time complexity when the input is larget, need to go through every number from 2 to n.


## Algorithm Analysis

Stored the futrue state by the current prime number.

init a boolean array isNotPrime default to false, boolean[n + 1], index stands for the number.

Used i from 2 (Which is prime number), if isNotPrime[i] is true, we mark all number i time j from 2 to ... i * j < n as true(which means that number is not Prime).


## Time Complexity Analysis

O(N) time complexity and O(N) space

## Code

```java
class Solution {
    public int countPrimes(int n) {
        /* Dp- mark future numbers as not Prime by the prime number */
        
        boolean[] isNotPrime = new boolean[n + 1]; /* Default false -> isPrime */
        int count = 0;
        for(int i = 2; i < n; i++) {
            if(!isNotPrime[i]) { /* i isPrime */
                count ++;
                /* mark all i * j as not Prime -> true */
                for(int j = 2; j * i < n; j++) {
                    isNotPrime[j * i] = true;
                }
            }
        }
        return count;

    }
}
```

***

# 215| Kth Largest Element in an Array 

## Problem Analysis

We could sort the array and find the N - k number

We could use a PriorityQueue to save the number, then when the size over k, starts to pop out(Too Large) the new insert node to keep the queue have k the largest Element.

## Code

### Solution: Sorted 
- O(N lg N) running time + O(1) memory

```java
public int findKthLargest(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
}
```
### Solution: Priority Queue

- O(N lg K) running time + O(K) memory

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        /* Priority Default to get increment, could dismiss Comparator override */
        PriorityQueue<Integer> pq = new PriorityQueue<>(nums.length, new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1 - i2;
            }
        });
        
        for(int val : nums) {
            pq.offer(val);
            /* Keep the Kth largest number in the Queue */
            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }
}
```
### Solution: Quick Selection 

- (N) best case / O(N^2) worst case running time + O(1) memory

```java
public int findKthLargest(int[] nums, int k) {

        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }
  ```

## Fellow up - Solitoin Quick Selcetion - Guaranteed O(N) time

O(N) guaranteed running time + O(1) space

So how can we improve the above solution and make it O(N) guaranteed? The answer is quite simple, we can randomize the input, so that even when the worst case input would be provided the algorithm wouldn't be affected. So all what it is needed to be done is to shuffle the input.

```java
public int findKthLargest(int[] nums, int k) {

        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

private int partition(int[] a, int lo, int hi) {

    int i = lo;
    int j = hi + 1;
    while(true) {
        while(i < hi && less(a[++i], a[lo]));
        while(j > lo && less(a[lo], a[--j]));
        if(i >= j) {
            break;
        }
        exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
}

private void exch(int[] a, int i, int j) {
    final int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
}

private boolean less(int v, int w) {
    return v < w;
}

private void shuffle(int a[]) {

    final Random random = new Random();
    for(int ind = 1; ind < a.length; ind++) {
        final int r = random.nextInt(ind + 1);
        exch(a, ind, r);
    }
}
```

***

# 234| Palindrome Linked List 

## Problem Analysis

## Algorithm Analysis
Used Fast and Slow pointer to find the middle point in the linked list.

Revers the rest of middle point(Slow pointer pointed to), and used two dummy nodes to point to their head for comparing their values.

Let the middle position points to null.

The reverse method will automaticaly let the reversed linked list point to pre = null.

```
1 -> 2 -> 3 -> 2 -> 1
s
f

     s
          f
          s
                    f

D1 -> 1 -> 2 -> 3 -> null

D2 -> 1 -> 2 -> null

```

## Code

```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        dummy1.next = head;
        
        while(fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        /* dummy2 connect to the reversed one D1 -> 1 -> 2 -> null */
        dummy2.next = reverse(slow.next);
        /* dummy1 connect to the original one end on null D2 -> 1 -> 2 -> 3 -> null*/
        slow.next = null;
        
        /* Compare two linked list 1 -> 2  */
        while(dummy1 != null && dummy2 != null) {
            if(dummy1.val != dummy2.val) return false;
            dummy1 = dummy1.next;
            dummy2 = dummy2.next;
        }
        
        return true;
    }
    
    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
```

***


# 235 Lowest Common Ancestor of a Binary Search Tree

## Algorithm Analysis

Use the speicl characteristics of BST, if two node are both smaller, move root to left, on the other hand, if two nodes are both larger, move root to the right. Or, if the root was between two nodes, return root itself!

## Time Complexity Analysis

O(height of Tree)

## Code
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null) {
            if(root.val > p.val && root.val > q.val) root = root.left;
            else if(root.val < p.val && root.val < q.val) root = root.right;
            else return root;
        }
        return root;
    }
}

```

***

# 236| Lowest Common Ancestor of a Binary Tree

## Problem Analysis

## Algorithm Analysis

1. Find q and p in the tree. 
2. Record those path when you're traversing the tree.
3. Used Stack to traverse if child != null record child and parent in the Map.
4. if child != null push it into the stack
5. Used the Map we collected and a Set to record which parent node we now are going to visit.
6. From p to root, record all visited node.
7. From q to root, if there is a node in the path has already been put in the set, means this node is the LCA, stop traverse, return q.

```
Map<Child, Parent> -> Counld use this map to find back the Parent
```

## Time Complexity Analysis

## Code - Iteration

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /* Child - Parent Map */
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        /* Set to record path */
        Set<TreeNode> path = new HashSet<>();
        /* Stack to help traversing */
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        /* Init the stack and parent-child map */
        stack.offerLast(root);
        parent.put(root, null);
        
        /* Find the p and q */
        while(!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pollLast();
            /* Chekch node's two children and put them into Stack and Map */
            if(node.left != null) {
                stack.offerLast(node.left);
                parent.put(node.left, node);
            }
            
            if(node.right != null) {
                stack.offerLast(node.right);
                parent.put(node.right, node);
            }
        }
        
        /* Found q and p and put them into map */
        
        /* Start from q to the root and record the path */
        while(q != null) {
            /* Add q into our path */
            path.add(q);
            /* Move forward to q's parent to try */
            q = parent.get(q);
        }
        
        /* Start from p and see if set has existed it's parent */
        while(!path.contains(p)) {
            /* Look up to see if there is a same node appeared */
            p = parent.get(p);
        }
        
        return p;
    }
}

```

## Fellow up - Recursion

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if(root == null) return null;
    if(root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    return left != null && right != null ? root : left == null ? right : left; 
}
```

***

# 238| Product of Array Except Self


## Algorithm Analysis

Record the product from left without itself in an array fromLeft.
Record the product from right without itself in an array fromRight.

````
   1        2       3       4
l  1        1*1     1*1*2   1*1*2*3
r  1*4*3*2  1*4*3   1*4     1
````

## Time Complexity Analysis

Time O(n) Space O(n)

## Code

```java

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] fromLeft = new int[len];
        int[] fromRight = new int[len];
        int[] result = new int[len];
        
        fromLeft[0] = 1;
        
        for(int i = 0; i < len - 1; i++) {
            fromLeft[i + 1] = fromLeft[i] * nums[i];
        }
        
        fromRight[len - 1] = 1;
        
        for(int j = len - 1; j > 0; j--) {
            fromRight[j - 1] = fromRight[j] * nums[j];
        }
        
        for(int i = 0; i < len; i++) {
            result[i] = fromLeft[i] * fromRight[i];
        }
        
        return result;
    }
}
```

## Fellow up - Optimization

Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    res[0] = 1;
    for (int i = 1; i < n; i++) {
        res[i] = res[i - 1] * nums[i - 1];
    }
    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
        res[i] *= right;
        right *= nums[i];
    }
    return res;
}
```


***

# 239| Sliding Window Maximum

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up


***

# 240| Search a 2D Matrix II

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

- DFS 

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        return dfsHelper(matrix, 0, col - 1, row, col, target);
    }
    
    public boolean dfsHelper(int[][] matrix, int r, int c, int row, int col, int target) {
        if(r < 0 || r >= row || c < 0 || c >= col) {
            return false;
        } else if(matrix[r][c] == target) {
            return true;
        } else if(matrix[r][c] > target) {
            return dfsHelper(matrix, r, c - 1, row, col, target);
        } else if(matrix[r][c] < target) {
            return dfsHelper(matrix, r + 1, c, row, col, target);
        }
        return false;
    }
}
```

## Fellow up

- No DFS

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row == 0) return false;
        int col = matrix[0].length;
        
        /* Start from (0, col - 1) */
        
        int r = 0;
        int c = col - 1;
        
        while(r >= 0 && r < row && c >= 0 && c < col) {
            if(matrix[r][c] == target) {
                return true;
            } else if(matrix[r][c] > target){
                /* move left */
                c--;
            } else {
                /* move down */
                r++;
            }
        }
        return false;
    }
}
```

## Fellow - Divide and Conquer

[傳統解法](https://github.com/WeiChienHsu/Java_Practice/tree/master/OA#%E5%82%B3%E7%B5%B1%E8%A7%A3%E6%B3%95--%E6%B2%92%E6%9C%89%E7%89%B9%E6%AE%8A%E8%A6%8F%E5%BE%8B%E6%99%82%E7%94%A8divide-and-conquer)

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int[] upperLeft = new int[]{0, 0};
        int[] lowerRight = new int[]{row - 1, col - 1};
        return searchMatrix(matrix, upperLeft, lowerRight, target);
    }
    
    public boolean searchMatrix(int[][] matrix, int[] upperLeft, int[] lowerRight, int target) {
        
        // If the current point is invalid
        if(upperLeft[0] > lowerRight[0] || upperLeft[1] > lowerRight[1] 
           || upperLeft[1] >= matrix[0].length || lowerRight[1] >= matrix[0].length) {
            return false;
        }
        
        // If there is only one point in this matrix
        if(upperLeft[0] - lowerRight[0] == 0 && upperLeft[1] - lowerRight[1] == 0) {
            return matrix[upperLeft[0]][upperLeft[1]] == target;
        }
        
        // Capture the middle Row and Col
        int rowMid = (upperLeft[0] + lowerRight[0]) / 2;
        int colMid = (upperLeft[1] + lowerRight[1]) / 2;
        
        // If Center < target, discard zone 1 (Which are all smaller than center)
        // If Center > targer, discard zone 4 (Which are all larger than center)
        // Zone1: upperLeft, {rowMid, colMid}
        // Zone2: {upperLeft[0], colMid + 1}, {rowMid, lowerRight[1]}
        // Zone3: {rowMid + 1, upperLeft[1]}, {lowerLeft[0], colMid}
        // Zone4: {rowMid + 1, colMid + 1}, lowerRight
        
        if(matrix[rowMid][colMid] < target) {
            return searchMatrix(matrix, new int[]{upperLeft[0], colMid+1}, new int[]{rowMid, lowerRight[1]}, target)
				|| searchMatrix(matrix, new int[]{rowMid+1, upperLeft[1]}, new int[]{lowerRight[0], colMid}, target)
				|| searchMatrix(matrix, new int[]{rowMid+1, colMid+1}, lowerRight, target);
        } else if(matrix[rowMid][colMid] > target) {
            return searchMatrix(matrix, upperLeft, new int[]{rowMid, colMid}, target)
				|| searchMatrix(matrix, new int[]{upperLeft[0],colMid+1}, new int[]{rowMid, lowerRight[1]}, target)
				|| searchMatrix(matrix, new int[]{rowMid+1,upperLeft[1]}, new int[]{lowerRight[0], colMid}, target);  
        } else {
            return true;
        }
    }
}

```

***

# 242| Valid Anagram

## Problem Analysis

Find if the String consists by the same characters

## Algorithm Analysis

Store all character in String s into Map, and use String t to decrement the Value in the Map by Character.

If there is value out of 0, return false.

## Time Complexity Analysis
O(NlogN) Time and additional Space O(N) -> try to opt in O(N) (using map)

## Code

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        Arrays.sort(charS);
        Arrays.sort(charT);
        
        return String.valueOf(charS).equals(String.valueOf(charT));
    }
}
```

## Fellow up - O(N) time complexity

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        for(int j = 0; j < t.length(); j++) {
            if(!map.containsKey(t.charAt(j))) return false;
            map.put(t.charAt(j), map.get(t.charAt(j)) - 1);
        }
        
        for(int val : map.values()) {
            if(val != 0) return false;
        }
        
        return true;
    }
}
```

***

# 297| Serialize and Deserialize Binary Tree 

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up


***

# 380| Insert Delete GetRandom O(1)

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code - Used LinkedList to implement

- Insert(val) : O(1)
- Remove(val) : O(1)
- Access/Randomly get : O(n)

```java
public class RandomizedSet {
    private Node head;
    private int currentNumber;

    public RandomizedSet() {
        this.currentNumber = 0;
        this.head = null;
    }

    public boolean insert(int val) {
        /* If the set is empty */
        if(head == null) {
            head = new Node(val);
            this.currentNumber++;
        }
        /* Check if the inserted value has already in the set */
        else {
            Node cur = head;
            for(; cur.next != null; cur = cur.next) {
                if(cur.val == val) return false;
            }

            /* cur points on the last node */
            if(cur.val == val) {
                return false;
            } else {
                cur.next = new Node(val);
                this.currentNumber++;
            }
        }
        return true;
    }

    public boolean remove(int val) {
        /* If the set is empty */
        if(head == null) {
            return false;
        } else if(head.val == val) {
            head = head.next;
            this.currentNumber--;
            return true;
        } else {
            /* Find the Value and remove it */
            Node pre = head;
            Node cur = head.next;

            while(cur != null) {
                if(cur.val == val) {
                /* Found the value and remove it */
                pre.next = cur.next;
                this.currentNumber--;
                return true;
                }
                pre = pre.next;
                cur = cur.next;
            }

            return false;
        }
    }
    /* Linked List get Random takes O(n) time complexity */
    public int getRandom() {
        if(head == null) return 0;
        int randomSteps = (int) (Math.random() * (this.currentNumber));
        Node cur = head;
        for(int i = 0; i < randomSteps; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public int numberInTheSet() {
        return this.currentNumber;
    }

    public boolean isEmpty() {
        return this.currentNumber == 0;
    }
}



class Node {
    int val;
    Node next;
    public Node(int val) {
        this.val = val;
    }
}
```

## Fellow up

***

# Time Complexy Cheat Sheet

|Data Structure | Time  |        |           |        |       |        |           |        |           Space  |
|:-------------:|:-----:|:------:|:---------:|:------:|:-----:|:------:|:---------:|:------:|:----------------:|
|               |Average|        |           |        | Worst |        |           |        | Worst            |
|               |Indexing | Search | Insertion | Delete |Indexing | Search | Insertion | Delete |                  |
| Basic Array   | O(1)  | O(n)   |     -      | -  | O(1)  | O(n)   | -      | -          | O(n)             |
| Dynimic Array | O(1)  | O(n)   | O(n)      | O(n)   | O(1)  | O(n)   | O(n)      | O(n)   | O(n)             |
| Stack         | O(n)  | O(n)   | O(1)      | O(1)   | O(n)  | O(n)   | O(1)      | O(1)   | O(n)             |
| Queue         | O(n)  | O(n)   | O(1)      | O(1)   | O(n)  | O(n)   | O(1)      | O(1)   | O(n)             |
|Single LinkedList| O(n)  | O(n)   | O(1)      | O(1)   | O(n)  | O(n)   | O(1)      | O(1)   | O(n)             |
|Double LinkedList| O(n)  | O(n)   | O(1)      | O(1)   | O(n)  | O(n)   | O(1)      | O(1)   | O(n)             |
| Hash Table    | N/A  | O(1)   | O(1)      | O(1)   | N/A  | O(n)   | O(n)      | O(n)   | O(n)             |
| BST           | O(logN)  | O(logN)    | O(logN)  | O(logN) | O(n)  | O(n)   | O(n)      | O(n)   | O(n)    |
| Red Black Tree| O(logN)  | O(logN) | O(logN) | O(logN)  | O(logN)  | O(logN) | O(logN) | O(logN)   | O(n)      |
| AVL Tree      | O(logN)  | O(logN) | O(logN) | O(logN)  | O(logN)  | O(logN) | O(logN) | O(logN)   | O(n)      |

***

# 387| First Unique Character in a String

## Problem Analysis

## Algorithm Analysis

## Code

- Map put and search -> O(N)
- IndexOf seatch in String -> O(N)

```java
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        for(Character c : map.keySet()) {
            if(map.get(c) == 1) return s.indexOf(c);
        }
        return -1;
    }
}

```

## Fellow up

- Remove the indexOf method

```java
class Solution {
    public int firstUniqChar(String s) {
        /* Save character and integer */
        Map<Character, Integer> map = new LinkedHashMap<>();
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            /* If in the map, change index to -1*/
            if(map.containsKey(c)) {
                map.put(c, -1);
            } else {
                map.put(c, i);
            }
        }
        
        for(char c : map.keySet()) {
            if(map.get(c) != -1) return map.get(c);
        }
        
        return -1;
    }
}
```

## Fellow - Used an array to save the frequency 

```java
public class Solution {
    public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}
```


***

# 535| Encode and Decode TinyURL

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up

***

# 538| Convert BST to Greater Tree

## Problem Analysis

Meet the tree problems, thought about how to traverse this tree.

In this problem, we need to visit right and then deal with the root, then deal with the left child. Since the BST root.right > root > root.left.

## Algorithm Analysis

DFS helper funciton to pass the right sum variable(use an array) and child of tree. In each dfs, change the value of current node (+ right sum) and change the value of right sum (+ current Value)


## Code

```java
class Solution {
    public TreeNode convertBST(TreeNode root) {
        int[] rightSum = new int[]{0};
        convert(root, rightSum);
        return root;
    }
    
    public void convert(TreeNode root, int[] rightSum) {
        if(root == null) return;
        convert(root.right, rightSum);
        root.val += rightSum[0];
        rightSum[0] = root.val;
        convert(root.left, rightSum);
    }
}
```




***

# 617| Merge Two Binary Trees

## Problem Analysis

PreOrder traverse to sum up the nodes.

Need to check if root == null, t1 == null, t2 == null then could call root.left, t1.left, t2.right etc.

## Code

```java
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        /* Both of the roots are null */
        if(t1 == null && t2 == null) return null;
        TreeNode root = new TreeNode ((t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val));
        root.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        root.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
        return root;
    }
}
```


***

# 682| Baseball Game

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up


***

# 746| Min Cost Climbing Stairs

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = cost[0];
        dp[1] = cost[1];
        
        for(int i = 0; i < len - 2; i++) {
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + cost[i + 1]);
            dp[i + 2] = Math.min(dp[i + 2], dp[i] + cost[i + 2]);
        }
        
        return Math.min(dp[len - 1], dp[len - 2]);
    }
}
```

## Fellow up


***

# 771| Jewels and Stones

## Problem Analysis

## Algorithm Analysis

## Time Complexity Analysis

## Code

## Fellow up


***

