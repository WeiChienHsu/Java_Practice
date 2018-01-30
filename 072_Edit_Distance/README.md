## Solution - Recursion Rule (Time Limit Exceeded)
```
head -> sad
/i |d \r
                           head match(i,j)
                        /   |    \
                     sead   ead   sead
                     /     / | \      \
 match(i+1,j+1) (ad)ead  sead ad sad   ead(ad)  match(i,j+1) 
                   / |  \        |      / | \
                sead ad* aad     ad  aead  ad* sad
                                 |
                    match(i+1,j) a
                                 |
                                 null
```
- 記錄改變的位置
- 增加一個正確char， i+1 , j+1 ，同時處理下一個
- 刪除不正確的char， i+1 , j ，i處理下一個，j沒有被消掉
- 取代一個char， i，j+1，i停留在同個位置，j處理下一個
```java
     if(word1.charAt(i) == word2.charAt(j)) {
          res = match(word1, word2, i+1, j+1);
      } else {
          // Case1 : Insert
          int insert = match(word1, word2, i, j + 1);
          // Case2 : Delete
          int delete = match(word1, word2, i + 1, j);
          // Case3 : Replace
          int replace = match(word1, word2, i + 1, j + 1);
          
          res = Math.min(Math.min(insert, delete), replace) + 1;
      }
```
- 但這DFS方法有太多重複步驟(Treavse Recursion Tree)，如何優化？ DP:對Recursive做Cache。

## Solution Memorized Search
- Memorized Serach: 紀錄 count[i][j]，將每一步記錄下來
- Time : O(len1 * len2)
- Space : O(len1 * len2)

## DP
match(i,j) -> {s1(0,i), s2(0,j)} matched!
match[i,j] -> {s1(0,i) , s1(0,j)} min steps to match

- 先初始化 match[][]，把字串都放入match中
