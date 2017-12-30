# Remove K digitals

## Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

```
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
```

```
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
```


```
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
```

## Solution - Stack

- Used a Stack to record those number made a min value
- Give a pointer i to record which number we gonna deal with
- K is to record how many number we still need to remove from string
- If the number in stack peek is larger than the next number, poll it out
- Then, the K will minus one and offer the next number into the Stack in the mean time i++
- Check a special situation when String equal to "1111111111"(poll out the rest of number to Make K zero)
- StringBuilder to deal with add and reverse number
- Remove all those character '0' in fort of number. ex "00324" -> "324"
- Make a StringBuilder back toString().
* Think about when the input = [1,0] and k = 2, what should I have to return.!!!!!!!!!


```

1  4  3  2  2  1  9


stack:

|     |
|__1__|

1 < 4 -> push 4 -> i++

stack:

|  4  |
|__1__|

3 < 4 -> pop() -> k-- -> push 3 -> i++

stack:

|  2  |
|__1__|

2 < 3 -> pop() -> k-- -> push 2 -> i++
2 = 2 -> push 2 -> i ++

stack:
|  2  |
|  2  |
|__1__|

1 < 2 - > pop() -> k-- -> push 1 -> i++
stack:
|  9  |
|  1  |
|  2  |
|__1__|

-> Transfor to Sting and reserve

```

```java
class Solution {
public String removeKdigits(String num, int k) {
    if(k == num.length()) {
        return "0";
    }
    Stack<Character> stack = new Stack<>();
    int i = 0;

    while(i < num.length()) {    
        while(k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
            stack.pop();
            k -- ;
        }
        stack.push(num.charAt(i));
    }

        while (k > 0) {
            stack.pop();
            k --;
        }
        
        StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

    while(sb.length()>1 && sb.charAt(0) == '0') {
        sb.deleteCharAt(0);
    }

    return sb.length() == 0? "0" : sb.toString();  
}
}
```