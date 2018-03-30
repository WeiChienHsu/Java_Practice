#

## Solution
- 需要兩個Stacks紀錄目前的倍數與符號 multiStack & charStack
- 先置入一個StringBuilder 在 charStack 內，準備放答案
- Meet "[" : 將累積的"倍數"，加入"multi"的Stack中，並且歸零 && 加入一個空的StringBuilder在charStack中，讓之後的char可以append
- Meet number : 累積目前的"倍數"，將先前累積的 *10 + curNumber
- Meet char : 將此char append進入
- Meet "]" : 進行運算 -> pop multiStack 當做要乘的倍數 , pop charStack 是我們要乘以的char，相乘之後加入一個新的StringBuilder，結束後將這結果重新加入已在charStack內的值


```
3 [ a 2 [ c ] ]

3:

Update the curMulti -> curMulti = curMulti * 10 + 3 = 3
 
multiStack :
 
charStack :

curMulti : 3

----------

[:

poll curMulti into multiStack 
 
set curMulti to 0 

gut a empty StringBuilder into charStack

multiStack : 3
 
charStack : __ 

curMulti : 0

----------
a:

append a into peek of charStack -> charStack.push(charStack.pop().append('a'))

multiStack : 3
 
charStack : a | 

curMulti : 0

----------

2:

Update the curMulti -> curMulti = curMulti * 10 + 2 = 2

multiStack : 3
 
charStack : a | 

curMulti : 2

----------


[:

poll curMulti into multiStack 
 
set curMulti to 0 

gut a empty StringBuilder into charStack


multiStack : 3 | 2
 
charStack : a | __

curMulti : 0

----------

c:

append a into peek of charStack -> charStack.push(charStack.pop().append('a'))

multiStack : 3 | 2
 
charStack : a | c

curMulti : 0

----------

]:

pop multiStack as times -> int times = 2;
pop charStack as multiChar -> char multiChar = 'c' 
Give a New tempStr = new StringBuilder()
Used a for loop to create this new String -> "cc"
Append it into the peek of charStack -> "acc"

multiStack : 3 
 
charStack : acc

curMulti : 0

----------

]:

pop multiStack as times -> int times = 3;
pop charStack as multiChar -> char multiChar = 'acc' 
Give a New tempStr = new StringBuilder()
Used a for loop to create this new String -> "accaccacc"
Append it into the peek of charStack -> "accaccacc"

multiStack : 
 
charStack : accaccacc

curMulti : 0

----------

Return the peek of charStack -> "accaccacc"
```

```java
class Solution {
    public String decodeString(String s) {
        Deque<Integer> multiStack = new ArrayDeque<>();
        Deque<StringBuilder> charStack = new ArrayDeque<>();
        charStack.offerLast(new StringBuilder());
        int multi = 0;
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '['){ 
                // 將累積的"倍數"，加入"multi"的Stack中，並且歸零
                // 加入一個空的StringBuilder在charStack中，讓之後的char可以append
                multiStack.offerLast(multi);
                multi = 0;
                charStack.offerLast(new StringBuilder());
                
            } else if (Character.isDigit(c)) {
                // 累積目前的"倍數"，將先前累積的 *10 + curNumber
                multi = multi * 10 + Character.getNumericValue(c);
            } else if(c == ']') {
                // pop multiStack 當做要乘的倍數
                // pop charStack 是我們要乘以的char 
                // 相乘之後加入一個新的StringBuilder 
                //結束後將這結果重新加入已在charStack內的值
                
                int times = multiStack.pollLast();
                StringBuilder multiChar = charStack.pollLast();
                StringBuilder tempStr = new StringBuilder();
                for(int j = 0; j < times; j++ ) {
                    tempStr.append(multiChar);
                }
                charStack.offerLast(charStack.pollLast().append(tempStr));
            } else {
                // Meet char
                // 將此char append進入
                charStack.offerLast(charStack.pollLast().append(c));
            }
        }
        return charStack.peek().toString();
    }
}
```