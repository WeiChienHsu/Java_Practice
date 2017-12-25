# 150. Evaluate Reverse Polish Notation

## Evaluate the value of an arithmetic expression in Reverse Polish Notation.

- Valid operators are +, -, *, /. Each operand may be an integer or another expression.

### Some examples:

```
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
```

## Solution

## In java, comparing string should use equals not ==.

- Check if the input is invaild
```java
if(tokens == null || tokens.size() == 0){
    return 0;
}
```

- Used ArrayDeque: Since we just deal with the last number saved in Deque.
```java
Deque<Integer> stack = new ArrayDeque<Integer>();
```
- When the pointer meet Number: stack.offerLast() -> Save into Deque.
```java
for (int i = 0; i < tokens.size(); i++) {
  default:
      stack.offerLast(Integer.parseInt(tokens.get(i)));
      break;
}


```
- When the pointer meet Operator: stack.pollLast() -> Pull out two numbers and counting.
```java
switch (tokens.get(i)){
    // Meet Operator, Pull two numbers and count it, Save into Deque
    case "+":
        stack.offerLast(stack.pollLast() + stack.pollLast());
        break;
    case "-":
        int numTwo = stack.pollLast();
        int numOne = stack.pollLast();
        stack.offerLast(numOne - numTwo);
        break;
    case "*":
        stack.offerLast(stack.pollLast() * stack.pollLast());
        break;
    case "/":
        int numTwo1 = stack.pollLast();
        int numOne1 = stack.pollLast();
        stack.offerLast(numOne1 / numTwo1);
        break;
```

- Finish the Loop (which means the pointer get to the END) -> return the last number in Deque.
```java
return stack.pollLast();
```

- Do not forget to transfer String into Number
```java
stack.offerLast(Integer.parseInt(tokens.get(i)));
```