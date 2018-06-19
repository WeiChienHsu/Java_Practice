# Group Anagrams
Given an array of strings, group anagrams together.

```
Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

Note:

All inputs will be in lowercase.
The order of your output does not matter.

## Java String (注意!)



### 比較 String.valueOf() vs. Object.toString()

valueOf() method of String class is static. whereas toString() method of String class is non static. 

In Java, is there any difference between String.valueOf(Object) and Object.toString()?

As the javadoc explains, String.valueOf(null) will be treated as a special case, whereas null.toString() will just give you an NPE.

```java
public static void main(String args[]){  
    String str = null;
    System.out.println(String.valueOf(str));  // it will prints null        
    System.out.println(str.toString()); // it will throw NullPointerException
} 
```

### equals vs ==

```java
char[] c1 = {'a', 'b', 'c'};
char[] c2 = {'b', 'a', 'c'};
Arrays.sort(c2);

String s1 = String.valueOf(c1);
String s2 = String.valueOf(c2);

System.out.println(s1);
System.out.println(s2);
System.out.println(s1 == s2);
System.out.println(s1.equals(s2));
```

```
abc
abc
false
true
```

## Solution O(n * mlogm)
1. 轉換成 char[], Arrays.sort 這些 Characters 讓 Oreder doesn't matter.
2. 利用新的char[] 轉換回 StringKey，使用Map<Key: StrginKey, Value: Original String>來記錄。
3. 將所有的Key遍歷一次，取出相同的Strgin加入result的ArrayList內。

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
       
        List<List<String>> result = new ArrayList<>();
        Map< String, List<String>> map = new HashMap<>();
        
        for(int i = 0; i < strs.length; i++) {
            String currentString = strs[i];
            
            // Transfer String into Char Array inorder to sort them
            char[] currentCharArray = currentString.toCharArray();
            
            // Sorted the characters array and format it to our key
            // Make the order doesn't matter
            // Used String.valueOf instead toString() 
            Arrays.sort(currentCharArray);
            String currentKey = String.valueOf(currentCharArray);
            
            if(!map.containsKey(currentKey)) {
                map.put(currentKey, new ArrayList<String>());
                map.get(currentKey).add(currentString);
            } else {
                map.get(currentKey).add(currentString);
            }
        }
        
        // Used those key String we stored in map to get the results
        for(String key : map.keySet()){
            result.add(map.get(key));
        }
        
        return result;
    }    
}
```

## Simplify Syntax
```java
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
```