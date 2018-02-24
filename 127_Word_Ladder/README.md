# Word Ladder

## Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord

```
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
```



## Solution - Slow method
- Record the node in each level : Queue
- Make visited : Set<String>
- Path length : int steps




```
Word lists List<String>
{let, set, lit, lig, sit, lib}
StartWord: leg
EndWord: lab

          / lib -> lab
      lig    |
    /     \ lit
leg
    \     / lit -> sit
      let
          \ set -> sit
```

- How to find from leg to lig???
* Find from a to z, and replace the word to find if that's on the word Lists
```java
    private static String replace(String str, int i, char c) {
        char[] chs = str.toCharArray();
        chs[i] = c;
        return new String(chs);
    }
```

- Run the BFS
* put leg in set and quete
```
   Queue
     ________
        leg
     ________
   Visit Set
    ________
    leg
    ________
    count = 0
```

```
   Queue
     ________
      let lig     leg->
     ________
   Visit Set
    _______________
    leg let lig
    _______________
    count = 1
```

```
   Queue
     ______________
      lit set lig     let ->
     _____________
   Visit Set
    ________________________
    leg let lig set lit 
    ________________________
    count = 2
```

```
   Queue
     ______________
      lib set lit     lig -> (lit in set)
     _____________
   Visit Set
    ________________________
    leg let lig set lit lib
    ________________________
    count = 2
```

```
   Queue
     ______________
      sit lib set      lit ->
     _____________
   Visit Set
    ____________________________
    leg let lig set lit lib sit
    ____________________________
    count = 3
```
```
   Queue
     ______________
      sit lib         set -> (sit in set)
     _____________
   Visit Set
    ____________________________
    leg let lig set lit lib sit
    ____________________________
    count = 3
```

```
   Queue
     ______________
      lab sit         lib -> (sit in set)
     _____________
   Visit Set
    ____________________________
    leg let lig set lit lib sit lab
    ____________________________
    count = 4
```

## HashSet Methods

- Give a Dist(HashSet) to record the WordList -> HashSet(wordList)
- Give a visited to avoid it go through again
- Give a queue to recored the string in the same leve

```java
Set<String> dict = new HashSet<>(wordList);
Set<String> visited = new HashSet<>();
Deque<String> queue = new ArrayDeque<>(1000);

int count = 1;
```

- 1. offer the first String into both queue and visited set
```java
queue.offerLast(beginWord);
visited.add(beginWord);
```

- 2. go through each level, pull out the String in queue and check if it is the endWord

- 3. if Not, try to chagne those words by getNextWords function

```java
while (!queue.isEmpty()) {
    int size = queue.size();
    for (int i = 0; i < size; ++ i) {
        String head = queue.pollFirst();
        if (head.equals(endWord)) return count;
        for (String nextWord : getNextWords(head, dict))
```

- 4. Loop through the charactor from a to z, and give a new String
- 5. Used this new String to check if it was in dict(HashSet)
- 6. if it was in the dict, add it into a nextWords Set
- 7. Return the nextWords Set and go through those intended String
```java
    private static Set<String> getNextWords(String curr, Set<String> dict) {
        Set<String> nextWords = new HashSet<String>();
        for (int i = 0; i < curr.length(); i++) {
            char[] chars = curr.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String temp = new String(chars);
                if (dict.contains(temp)) {
                    nextWords.add(temp);
                }
            }
        }
        return nextWords;
    }
```

- 8. Check if it has been visited, if not add into the Quese
- 9. if it didn't mathch we go to the next level and Count ++
- 10. Until the queue becomes empty which means there is no possible to give the word in wordList
- 11. Return 0 when it didn't match any words.

```java
        for (String nextWord : getNextWords(head, dict))
            if (visited.add(nextWord)) queue.offerLast(nextWord);

    }
    ++ count;
}
return 0;
}
```