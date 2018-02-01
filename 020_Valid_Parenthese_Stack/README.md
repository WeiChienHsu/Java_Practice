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