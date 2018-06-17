# Partition Label


A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

```
Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
```

## Solution
1. 將字串內的character以及其index(開始與結束)記錄在LinkedHashMap當中

```java
  Map<Character, List<Integer>> map = new LinkedHashMap<>();
  for(int i = 0; i < S.length(); i++) {
      char n = S.charAt(i);
      if(!map.containsKey(n)) map.put(n, new ArrayList<Integer>());
      List<Integer> list = map.get(n);
      
      // Add number into the list
      if(list.size() < 2) {
          list.add(i);
          list.add(i);
      } else {
          list.set(1, i);
      } 
  }
```

```
a :  start - 0 end - 8
----
b :  start - 1 end - 5
----
c :  start - 4 end - 7
----
d :  start - 9 end - 14
----
e :  start - 10 end - 15
----
f :  start - 11 end - 11
----
g :  start - 13 end - 13
----
h :  start - 16 end - 19
----
i :  start - 17 end - 22
----
j :  start - 18 end - 23
----
k :  start - 20 end - 20
----
l :  start - 21 end - 21
----
```

2. 把Map遍歷一次，並作條件判斷
- newFirst > oldFirst && newFirst < oldEnd : 判斷 newEnd > oldEnd 需要更新oldEnd，不然直接continue
- 如果不符合以上條件，代表最新的字母在原本的區間範圍外(newFisrt > oldEnd)，紀錄當時的長度
- res.add(oldEnd - oldStart + 1)
- 更新oldEnd和oldStart都等於oldEnd

```java
List<Integer> res = new ArrayList<>();
int oldStart = -1;
int oldEnd = -1;

for(char n : map.keySet()) {
    int newStart = map.get(n).get(0);
    int newEnd = map.get(n).get(1);
    
    // Init the First Boundry
    if(oldStart == -1 && oldEnd == -1) {
        oldStart = newStart;
        oldEnd = newEnd;
    }
    
    // 如果新的開始大於目前的結尾，必須要紀錄目前為止的區間
    if(newStart > oldEnd) {
        res.add(oldEnd - oldStart + 1);
        oldEnd = newEnd;
        oldStart = newStart;
    } else {
        // 如果新的開始在目前結尾前，要檢查新的結尾是否超過原本區間，更新區間
        if(newEnd > oldEnd) {
            oldEnd = newEnd;
        }
    }
}

res.add(oldEnd - oldStart + 1);
```

## Two Pointer Solution
magine a bus moving forward, and imagine each char as a person yelling "I need to go that far!". If a newcomer yelled a further position, we extend our expected ending position to that position. Eventually, if we reached a position that satisfied everybody in the bus at the moment, we partition and clear the bus.

```java
class Solution {
    public List<Integer> partitionLabels(String S) {
        Integer[] positions = new Integer[26];
        char[] chs = S.toCharArray ();
        for (int i = 0; i < chs.length; i++)
            positions[chs[i] - 'a'] = i;
        List<Integer> resLs = new ArrayList<> ();
        int pos = 0, end = 0, anchor = 0;
        while (pos < chs.length) {
            end = Math.max (positions[chs[pos] - 'a'], end);
            if (pos == end) {
                resLs.add (pos - anchor + 1);
                anchor = pos + 1;
            }
            pos++;
        }
        return resLs;
    }
}
```