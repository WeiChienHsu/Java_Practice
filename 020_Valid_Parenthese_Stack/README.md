# Valid Parenthese

## Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
```
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
```
## Solution
###  "" for String, '' for character

- Scaner from left to right, to see the nearest one if it got the pair.
- Used Stack Data Struture (First In, Last out)
```java
char[] str = s.toCharArray();
Deque<Character> stack = new ArrayDeque<>();
```

- Meet { [ ( , save into the Stack
- Meet } ) ], check the last one in Stack
```java
for (char ch : str) {
// Meet "{", "(", "["
if(ch == '{' || ch == '(' || ch == '[' ) {
    stack.offerLast(ch);
} else { // Meet "}", "]", ")"
    if(stack.isEmpty()){
        return false;
    }
    char left = stack.pollLast();
    if( (ch == ')' && left == '(') ||
        (ch == '}' && left == '{') ||
        (ch == ']' && left == '[')) {
        continue;
    } else {
        return false;
    }
```
- Return stack.Empty() since there might be a remain left parenthese
```java
return stack.isEmpty()
```

## Solution

```java
class Solution {
    public static boolean isValid(String s) {

        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++) {
            if(isPre(s.charAt(i))) stack.offerLast(s.charAt(i));
            if(isPost(s.charAt(i))) {
                if(stack.isEmpty()) return false;
                char peek = stack.pollLast();
                if(s.charAt(i) == ')' && peek != '(') return false;
                if(s.charAt(i) == ']' && peek != '[') return false;
                if(s.charAt(i) == '}' && peek != '{') return false;
            }
        }
        return stack.isEmpty();
    }

    public static boolean isPre(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    public static boolean isPost(char c) {
        return c == ')' || c == ']' || c == '}';
    }
}
```