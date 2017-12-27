# Minimum Window Substring

## Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

```
For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".
```
## Solution 
### 1. Stroe Charactor in T - HashMap
### 2. When to move Start / End ? Need to run once to figure out!!!!
### 3. Identify a Match - Counter
### 4. Record the main length - Global min

- T: ABC
- S: A B D G C E B A N C
* end met number in T -> HashMap <X, -1> & count --
* When count == 0 -> claulate the end - start
* After calculating, move start.
* When start leave A -> Hashmap <A, 1> -> count ++ 
* Not Match, move end
* Match, move start

```
        A B D G C E B A N C
start   *
end     *

count = 3
<A, 1> <B, 1> <C, 1>
min = 0
```
- end meet A
```
        A B D G C E B A N C
start   *
end     *

count = 2
<A, 0> <B, 1> <C, 1>
min = 0
```
- Move end and meet B
```
        A B D G C E B A N C
start   *
end       *

count = 1
<A, 0> <B, 0> <C, 1>
min = 0
```

- Move end until meet C
```
        A B D G C E B A N C
start   *
end             *

count = 0
<A, 0> <B, 0> <C, 0>
min = end - start = 4
```

- Move start since count == 0
```
        A B D G C E B A N C
start     *
end             *

count = 1
<A, 1> <B, 0> <C, 0>
min = end - start = 4
```

- Not Match, move end to B but not match, still move A
```
        A B D G C E B A N C
start     *
end                 *

count = 1
<A, 1> <B, -1> <C, 0>
min = end - start = 4
```

- Match! Count the length and move start
```
        A B D G C E B A N C
start     *
end                   *

count = 0
<A, 0> <B, -1> <C, 0>
min = end - start = 4
```

- Match! Count the length and move start
```
        A B D G C E B A N C
start           *
end                   *

count = 0
<A, 0> <B, 0> <C, 1>
min = end - start = 4
```

- Move end to C, match, move Start
```
        A B D G C E B A N C
start           *
end                       *

count = 0
<A, 0> <B, 0> <C, 0>
min = end - start = 4
```

- Move end to C
```
        A B D G C E B A N C
start               *
end                       *

count = 0
<A, 0> <B, 0> <C, 0>
min = end - start = 3
```

- Move a out of B
```
        A B D G C E B A N C
start                 *
end                       *

count = 0
<A, 0> <B, 1> <C, 0>
min = end - start = 3
```
- Move C out of value, Stop, return min
```
        A B D G C E B A N C
start                 *
end                          *

count = 0
<A, 0> <B, 1> <C, 0>
min = end - start = 3
```