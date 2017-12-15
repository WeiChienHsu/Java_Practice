# 150. Evaluate Reverse Polish Notation

## Evaluate the value of an arithmetic expression in Reverse Polish Notation.

- Valid operators are +, -, *, /. Each operand may be an integer or another expression.

### Some examples:

```
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
```

## Solution

- Used ArrayDeque: Since we just deal with the last number saved in Deque.
- When the pointer meet Number: stack.offerLast() -> Save into Deque.
- When the pointer meet Operator: stack.pollLast() -> Pull out two numbers and counting.
- Finish the Loop (which means the pointer get to the END) -> return the last number in Deque.
```java
stack.offerLast(Integer.parseInt(tokens.get(i)));
```
- Do not forget to transfer String into Number