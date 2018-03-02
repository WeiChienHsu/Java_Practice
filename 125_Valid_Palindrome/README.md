# Valid Palindrome

- 雙指針，判斷方式：
- 遇到 !isLetterOrDigit -> 跳過!
- 先判斷 start
- 再判斷 end
- 此時，start 和 end 都在合法的char上面
- 判斷兩者單字是否相同，如果不同，直接return false
- 當 start == end (基數), start > end (偶數)，代表已經掃過一次String，沒有回報錯誤 Return true

```java
class Solution {
  public boolean isPalindrome(String s) {
      if(s.length() == 0) {
          return true;
      }
      
      s = s.toLowerCase().trim();
      int start = 0;
      int end = s.length() - 1;
      System.out.println(s);
      char cHead, cTail;
      while(start <= end) {
          cHead = s.charAt(start);
          cTail = s.charAt(end);
          if(!Character.isLetterOrDigit(cHead)) {
              start++;
          } else if (!Character.isLetterOrDigit(cTail)) {
              end--;
          } else {
              if(cHead != cTail) {
                  return false;
              }
              start++;
              end--;
          }
      }
      return true;
  }
}
```