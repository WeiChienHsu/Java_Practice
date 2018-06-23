# Reverse Voewl of A String

Write a function that takes a string as input and reverse only the vowels of a string.

```
Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
```

## Solution

非常好去辨別母音的方式，就是將母音放在一個String裡面，然後把char拿進去找，indexOf(char)，如果return -1 代表不在String裡面。

如果要Switch String 裡面的 character ， 要使用 String string.toCharArray() 讓他變成一個 char[]。

```java
    public static String reverseVowels(String s) {
        char[] str = s.toCharArray();

        int start = 0, end = s.length() - 1;

        while (start < end) {
            while(!isVowel(str[start]) && start < end){
                start++;
            }

            while (!isVowel(str[end]) && start < end) {
                end--;
            }

            if(start < end) {
                // Swap char in start and end
                char temp = str[start];
                str[start] = str[end];
                str[end] = temp;
                start++ ;
                end-- ;
            }

        }
        return new String(str);
    }

    public static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }

```