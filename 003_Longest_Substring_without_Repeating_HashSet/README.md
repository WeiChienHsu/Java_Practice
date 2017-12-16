# Longest Substring Without Repeating Characters

## Given a string, find the length of the longest substring without repeating characters.

```
Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

```

## First Thought
- HashSet: Find out if there is a repeat number (if repeated, it couldn't be added into Set)
- Queue: to record the character (First in First Out)
- If it could be added in the Set, then it should be offer into ArrayDeque(Queue)
```java
// Save the Character - not repeat
Deque<Character> queue = new ArrayDeque<>();
// HashSet to check if there is a repeat number
Set<Character> set = new HashSet<>();
int max = Integer.MIN_VALUE;
```

-- Implement of the solution
```java
// Separate the String into Characters
for(char ch: s.toCharArray()) {

    while(!set.add(ch)) {
        // If there is a repeat number, remove it in both Deque and Set
        set.remove(queue.pollFirst());
    }
    // add number in queue and HashSet
    queue.offerLast(ch);
    set.add(ch);

    // Count the length and compare to the existed max number
    max = Math.max(max, queue.size());
}

return max;
}
```

# But it fails!!!! - Space Complexiuty Optimzie 

## Second thought 
- We Don't need Queue, insteads of Two Pointers.

```java
int start = 0;
int end = 0;
```

- We Don't need HashSet, insteads of array.
```java
int[] map = new int[128];
```
* HashSet is amatized O(1), init cost is too high.

- If there is a same number start move right (same as remove number from queue)
- If there is no same number end move right (same as add number in Queue)
- If there is a same number that we have checked, remove that number from map as well
```java
while(end < sToChar.length) {

    // > 0 meant there is a same number

    while(map[sToChar[end]] > 0) {
        map[sToChar[start++]]--;
    }

    map[sToChar[end++]]++;
    max = Math.max(max, end - start);
}
```


