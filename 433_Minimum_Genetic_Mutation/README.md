# 433. Minimum Genetic Mutation

## Solution - Word Bank 變種題 (限制能更換的的Characters)

1. 將原始 Start 放入 Queue 當中， 待進行 BFS
2. 使用Set紀錄 visited 和 word bank
3. 對Queue進行Level Traversal，每到下一層時，Level + 1
4. 檢查 Current String (pop out from Queue) 是否 == end -> Return level
5. 變換每個位置的 Character from charSet {A, C, G, T} by using char[] currentArray = current.toCharArray()
6. 如果 !visited && contains in Bank -> 丟入 Queue 中並標記 visited


```java
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        int level = 0;
        if(start.equals(end)) return level;
        
        Deque<String> queue = new ArrayDeque<>();
    
        Set<String> wordBank = new HashSet<>(Arrays.asList(bank));
        Set<String> visited = new HashSet<>();
        char[] charSet = new char[]{'A', 'C', 'G', 'T'};
        
        queue.offerLast(start);
        visited.add(start);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String current = queue.pollFirst();
                /* Meet the end String */
                if(current.equals(end)) return level;
                
                /* Change character one by one */
                char[] currentArray = current.toCharArray();
                for(int j = 0; j < currentArray.length; j++) {
                    char originalChar = currentArray[j];
                    for(char c : charSet) {
                        currentArray[j] = c;
                        String temp = new String(currentArray);
                        /* Check if this temp String is in the bank and was not visited before */
                        if(!visited.contains(temp) && wordBank.contains(temp)) {
                            visited.add(temp);
                            queue.offerLast(temp);
                        }
                    }
                    /* Have been changed all 4 characters in the specific position and now change back */
                    currentArray[j] = originalChar;
                } /* End of characters switching */
            } /* Level Traversal */
            level ++;
        }
        return -1;
    }
}
```