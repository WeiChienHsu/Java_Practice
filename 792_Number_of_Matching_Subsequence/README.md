# Number of Matching Subsequence

Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

```
Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].
```

## Solution
Is Subsequence 的延伸問題，讓 processSingleWord function 依次完成搜索。


```java
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, List<Integer>> map = new HashMap<>();
        // Put all charactors into the Map 
        for(int i = 0; i < S.length(); i++) {
            if(!map.containsKey(S.charAt(i))) {
                map.put(S.charAt(i), new ArrayList<Integer>());
            }
            map.get(S.charAt(i)).add(i);
        }
        
        int count = 0;
        
        for(int i = 0; i < words.length; i++) {
            if(Solution.processSingleWord(words[i], map)) count++;
        }
        
        return count;        

    }
    
    public static boolean processSingleWord(String s, Map<Character, List<Integer>> map){
        int previousIndex = -1;
        for(int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            // Could not find the charactor in the Map
            if(!map.containsKey(currentChar)) return false;
            
            // Binary Search the index more than previousIndex
            int currentIndex = Solution.binarySearchForNextIndex(map.get(currentChar), previousIndex);
            
            // The index of current charactor is less the the previous charactor
            if(currentIndex < 0) return false;
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
        return list.get(end) <= previousIndex? -1 : list.get(start) > previousIndex? list.get(start) : list.get(end);
    }
    
}
```