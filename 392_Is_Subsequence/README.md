# Is Subsequence


Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

```
Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.
```

## Solution - Easy 

Used Two Pointer

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        int sP = 0;
        int tP = 0;
        while(sP < s.length() && tP < t.length() ) {
            if(s.charAt(sP) == t.charAt(tP)) {
                sP++;
            }
            tP++;
        }
        return sP == s.length();
    }
}
```


## Fellow Up

If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

1. 先將字母表 t 內的 String 以 char 和 List<Integer>(index) 的形式存在 Map 當中 O(n)
2. 遍歷一次 s ， 從 Map 裡面找到對應的 List O(n)，並且帶著 previous Found Index進去比較， prev 必須要 < 找到的那個Index
(如果Map當中沒有該char，直接Reture false)
3. 利用 Binary Search 找到在List內比prevous Index 大的那個 index，如果沒有，Return -1
4. 如果 Binary Search 出來的結果是 -1 ，直接return false，反之，更新previous Index value

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>> map = new HashMap<>();
        // Put all charactors into the Map 
        for(int i = 0; i < t.length(); i++) {
            if(!map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), new ArrayList<Integer>());
            }
            map.get(t.charAt(i)).add(i);
        }
        
        int previousIndex = -1;
        for(int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            // Could not find the charactor in the Map
            if(!map.containsKey(currentChar)) return false;
            
            // Binary Search the index more than previousIndex
            int currentIndex = Solution.binarySearchForNextIndex(map.get(currentChar), previousIndex);
            
            // The index of current charactor is less the the previous charactor
            if(currentIndex < 0) return false;
            System.out.println(currentChar);
            System.out.println("At");
            System.out.println(currentIndex);
            
            previousIndex = currentIndex;
        }
        
        return true;
    }
    
    public static int binarySearchForNextIndex(List<Integer> list, int previousIndex) {
        int start = 0, end = list.size() - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(list.get(mid) > previousIndex) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return list.get(end) < previousIndex? -1 : list.get(start) > previousIndex? list.get(start) : list.get(end);
    }
}
```