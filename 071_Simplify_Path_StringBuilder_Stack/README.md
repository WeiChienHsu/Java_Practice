# Simplfy Path

## Given an absolute path for a file (Unix-style), simplify it.

```
For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
```

## Solution
- Split the string by "/", and get a string[]
- Give a StringBuilder to record the results
- Back from the end to start
- If meet "." && "", nothing happen
- If meet "..", we won't count the future appeared string
- Thus, use a counter that if met ".." count++, and if met a future string nearest, count -- which means we won't add it into our StringBuilder
- if count == 0, insert "/" + strs[i] into sb
- Check if StringBuilder.length() == 0 or transfer it into a String value to return.

```java
 path = path.trim();
        StringBuilder sb = new StringBuilder();
        String[] strs = path.split("/");

        int count = 0;
        // Pointer from right to left
        for(int i = strs.length - 1; i >= 0; i--) {
            if(strs[i].equals("..")){
                count ++;
            } else if (!strs[i].equals(".") && !strs[i].equals("")) {
               // Meet number, if count = 0, insert in sb (first index)
                // If count > 0, ignore the words
               if(count > 0) {
                   count --;
               } else {
                   sb.insert(0, "/" + strs[i]);
               }
            }
        }
        // if there is no words in sb, give a "/", or transfer it into String
        return sb.length() == 0 ? new String("/") : sb.toString();
    }

```