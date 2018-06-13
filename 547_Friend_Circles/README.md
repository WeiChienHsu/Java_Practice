# 547_Friend_Circles

There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

```
Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
```

## Solution - DFS

類似 Number of Island 的解法，使用DFS：

1. 將連結到的friend index 標記為 visited，只要再次造訪，代表他是在原本的朋友圈內
2. 遍歷一次整個圖，只要遇到該值 == 1 的時候，就將該點丟入 dfs 中
3. dfs 遍歷該點的那一個row (會遍歷所有該學生的朋友)，如果該學生曾經被造訪返回空值不處理，如果尚未造訪，則是標記該值並丟入dfs中繼續找這串friend circle連結到的人。
4. 跳出 dfs 代表這個環結束，count ++，並繼續遍歷下個沒有被visited的學生。

不需要和 Number of Islands 一樣引用row與col找邊界值，因為這裡的0與1有不同的意義。

```java
class Solution {
    public int findCircleNum(int[][] M) {
        if(M == null || M.length == 0 || M[0].length == 0) return 0;
        int n = M.length; // row == col, we used n to present number of students
        
        boolean[] visited = new boolean[n];
        int count = 0;
        
        // 遍歷過一次所有的row，代表遍歷過所有的學生
        // 標記visited過的，代表已經進入了朋友圈
        for(int i = 0; i < n; i++) {
                if(!visited[i]) {
                    count++;
                    Solution.dfsHelper(M,i, n, visited);
                }
        }
        return count;
    }
    public static void dfsHelper(int[][] M, int currentStudent, int n, boolean[] visited) {
        
        // If this student has been visited
        // it means he or she will be someone's friend in the current friend circle
        // Dismiss
        
        if(visited[currentStudent]) return;
        
        // Marked the current Studnet as visited
        visited[currentStudent] = true;
        
        // Loop through the relationship of currentStudent with others
        for(int i = 0; i < n; i++) {
            // Find currentStudent's friend and put it into DFS
            if(M[currentStudent][i] == 1) {
                Solution.dfsHelper(M, i, n, visited);
            }
        }
    }
}
```

***


## Solution - Union Find

使用 UnionFind，將每個學生視為一個Node，只要兩個學生是朋友，就將這兩個Node連再一起，中間的邏輯是使用Union Find的邏輯，rank低得到高，每次Find到該Node的root node。

與其他題不同之處，為了方便計算，我將Count variable寫入Class當中，Constructor的時候預設為n，只要需要Union連結兩個點時（代表著將少一個Circle，併入一個大個)，減少Count，最後Return UnionFind.count()。

```java
class Solution {
    public int findCircleNum(int[][] M) {
        if(M == null || M.length == 0 || M[0].length == 0) return 0;
        int n = M.length; // row == col, we used n to present number of students
        int m = M[0].length;
        
        int count = 0;
        
        UnionFindSet set = new UnionFindSet(n);
        
        for(int i = 0; i < n; i++) {
          for(int j = 0; j < m; j++ ) {
              if(M[i][j] == 1) set.Union(i, j);
          }  
        }
        return set.count();
    }
    
}

class UnionFindSet {
    private int ranks[];
    private int parents[];
    private int count;
    public UnionFindSet(int n) {
        this.ranks = new int[n];
        this.parents = new int[n];
        this.count = n;
        for(int i = 0; i < n; i++) {
            ranks[i] = 1;
            parents[i] = i;
        }
    }
    
    public void Union(int u, int v) {
        int rootU = Find(u);
        int rootV = Find(v);
        
        if(rootV == rootU) return;
        
        if(ranks[rootU] > ranks[rootV]) {
            parents[rootV] = rootU;
        } else if(ranks[rootV] > ranks[rootU]) {
            parents[rootU] = rootV;
        } else {
            parents[rootU] = rootV;
            ranks[rootV]++;
        }
        
        this.count--;
        
        return;
    }
    
    public int Find(int u) {
        while(parents[u] != u) {
            parents[u] = parents[parents[u]];
            u = parents[u];
        }
        return u;
    }
    
    public int count() {
        return this.count;
    }
}

```