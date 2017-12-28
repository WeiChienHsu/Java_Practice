# Valid Parenthesis String

## Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

```
Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
```

## Solution - Two Stacks
- One Stack for recording "("
- One Stack for recording "*"
```java
Deque<Integer> pStack = new ArrayDeque<>();
Deque<Integer> sStack = new ArrayDeque<>();

```
- When cur is ")"
* Seek the pStack first
* if pStack is empty, seek the sStack
* if both of them are empty return false
```java
for(int i = 0; i < s.length(); i++) {
    if(s.charAt(i) == '(') {
        pStack.offerLast(i); // Offer into an index to know the position between "(" & "*"
    } else if (s.charAt(i) == '*') {
        sStack.offerLast(i);
    } else { // ')'
        if(!pStack.isEmpty()) {
            pStack.pollLast();
        } else if (!sStack.isEmpty() && pStack.isEmpty()){
            sStack.pollLast();
        } else {
            return false;
        }
    }
}
```

## Corner Case when going through String
- After going through the string, chek the index of s and p Sracks
- Since if * is behind (, it couldn't be seen as ).
- Record the index instead of char, then we could use this value to see the result.
* if sStack.pop < pStack.pop return false
```java
while(!pStack.isEmpty() && !sStack.isEmpty()) {
    if(pStack.pollLast() > sStack.pollLast()) {
        return false;
    }
}

return pStack.isEmpty();
```