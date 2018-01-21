# Length of the last word
## Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

## Solution
- .trim to remove outer space
- .lastIndexOf: point the last word
- .split(" ") to seperate the words into String array

```java
class Solution {
  public static int lengthOfLastWord(String s) {
      String[] strings = s.split(" ");
      if(strings.length < 1) return 0;
      return strings[strings.length - 1].length();
  }
}
```
```java
class Solution {
  public static int lengthOfLastWord(String s) {
    return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
  }
}
```