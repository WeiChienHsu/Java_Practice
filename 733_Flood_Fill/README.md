## Flood Fill

簡單 DFS 題目，注意檢查條件：
1. 當該點已經訪問 或是 該點值已經成為目標值，略過
2. 該點已經超過限制邊界，跳過
3. 該點不是我們的目標點，跳過

```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int row = image.length;
        int col = image[0].length;
        
        int currentColor = image[sr][sc];
        Solution.dfsHelper(image, sr, sc, row, col, currentColor, newColor);
        return image;
    }
    
    public static void dfsHelper(int[][] image, int sr, int sc, int row, int col, int currentColor, int newColor) {
        if(sr < 0 || sc < 0 || sr >= row || sc >= col) return;
        if(image[sr][sc] != currentColor) return;
        if(image[sr][sc] == newColor) return;
        
        image[sr][sc] = newColor;
        
        Solution.dfsHelper(image, sr - 1, sc, row, col, currentColor, newColor);
        Solution.dfsHelper(image, sr + 1, sc, row, col, currentColor, newColor);
        Solution.dfsHelper(image, sr, sc - 1, row, col, currentColor, newColor);
        Solution.dfsHelper(image, sr, sc + 1, row, col, currentColor, newColor);
        return;
    }
}
```