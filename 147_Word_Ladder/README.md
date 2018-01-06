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