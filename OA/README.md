# Search a 2D Matrix

## 當 Matrix 的row是 sorted Array ， col 也是從大到小

特殊條件判斷，因為Matrix是經過排序的，從左上角開始，只要目標數字比較大，就往下搜索，如果目標數字比較小，就往左搜索：

當搜索到邊界以外，代表該值不存在，如果搜索到target，則直接Return True。

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        return dfsHelper(matrix, 0, col - 1, row, col, target);
    }
    
    public boolean dfsHelper(int[][] matrix, int r, int c, int row, int col, int target) {
        if(r < 0 || r >= row || c < 0 || c >= col) {
            return false;
        } else if(matrix[r][c] == target) {
            return true;
        } else if(matrix[r][c] > target) {
            return dfsHelper(matrix, r, c - 1, row, col, target);
        } else if(matrix[r][c] < target) {
            return dfsHelper(matrix, r + 1, c, row, col, target);
        }
        return false;
    }
}
```

## 優化解法的Code -> 改成 Iteration

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        
        // Init the start point
        int r = 0;
        int c = matrix[0].length - 1;
        
        while(r >= 0 && c >= 0 && r < row && c < col) {
            if(matrix[r][c] == target) {
                return true;
            } else if(matrix[r][c] > target) {
                c--;
            } else if(matrix[r][c] < target) {
                r++;
            }
        }
        return false;
    }
}
```
## 傳統解法-> 沒有特殊規律時，用Divide and Conquer

每次都傳入一兩個int[]， upperLeft 和 lowerRight ，第一個點是 int[]{0,0} & int[]{matrix.length - 1, matrix[0].length - 1}

1. 如果 uppperLeft 和 lowerRight 超過邊界 ， Return false
```java
(upperLeft[0]>lowerRight[0] || upperLeft[1]>lowerRight[1] || lowerRight[0]>=matrix.length || lowerRight[1]>=matrix[0].length) 
```
2. 如果切分到只剩下一個點，檢查該點的值是否等於target
```java
if(upperLeft[0] - lowerRight[0] == 0 && upperLeft[1] - lowerRight[1] == 0) 
  return matrix[upperLeft[0], lowerRight[1]] == target
```

3. 取得新的中點， midRow and midCol

4. 如果 matrix |midRow][midCol] > target -> 放棄搜索 Zone 4 (通通比Center大)
5. 如果 matrix |midRow][midCol] < target -> 放棄搜索 Zone 1 (通通比Center小)

注意新的 upperLeft 是 {row, col} ， 所以在判定 valid 的時候，適用 upperLeft[1] >= matrix[0].length return false

```
Zone1: upperLeft, {rowMid, colMid}
Zone2: {upperLeft[0], colMid + 1}, {rowMid, lowerRight[1]}
Zone3: {rowMid + 1, upperLeft[1]}, {lowerLeft[0], colMid}
Zone4: {rowMid + 1, colMid + 1}, lowerRight
```
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int[] upperLeft = new int[]{0, 0};
        int[] lowerRight = new int[]{row - 1, col - 1};
        return searchMatrix(matrix, upperLeft, lowerRight, target);
    }
    
    public boolean searchMatrix(int[][] matrix, int[] upperLeft, int[] lowerRight, int target) {
        
        // If the current point is invalid
        if(upperLeft[0] > lowerRight[0] || upperLeft[1] > lowerRight[1] 
           || upperLeft[1] >= matrix[0].length || lowerRight[1] >= matrix[0].length) {
            return false;
        }
        
        // If there is only one point in this matrix
        if(upperLeft[0] - lowerRight[0] == 0 && upperLeft[1] - lowerRight[1] == 0) {
            return matrix[upperLeft[0]][upperLeft[1]] == target;
        }
        
        // Capture the middle Row and Col
        int rowMid = (upperLeft[0] + lowerRight[0]) / 2;
        int colMid = (upperLeft[1] + lowerRight[1]) / 2;
        
        // If Center < target, discard zone 1 (Which are all smaller than center)
        // If Center > targer, discard zone 4 (Which are all larger than center)
        // Zone1: upperLeft, {rowMid, colMid}
        // Zone2: {upperLeft[0], colMid + 1}, {rowMid, lowerRight[1]}
        // Zone3: {rowMid + 1, upperLeft[1]}, {lowerLeft[0], colMid}
        // Zone4: {rowMid + 1, colMid + 1}, lowerRight
        
        if(matrix[rowMid][colMid] < target) {
            return searchMatrix(matrix, new int[]{upperLeft[0], colMid+1}, new int[]{rowMid, lowerRight[1]}, target)
				|| searchMatrix(matrix, new int[]{rowMid+1, upperLeft[1]}, new int[]{lowerRight[0], colMid}, target)
				|| searchMatrix(matrix, new int[]{rowMid+1, colMid+1}, lowerRight, target);
        } else if(matrix[rowMid][colMid] > target) {
            return searchMatrix(matrix, upperLeft, new int[]{rowMid, colMid}, target)
				|| searchMatrix(matrix, new int[]{upperLeft[0],colMid+1}, new int[]{rowMid, lowerRight[1]}, target)
				|| searchMatrix(matrix, new int[]{rowMid+1,upperLeft[1]}, new int[]{lowerRight[0], colMid}, target);  
        } else {
            return true;
        }
    }
}
```

***

# Simple Maze

given 2D array, 1 represenst path, 0 represents wall, 9 is the destination. return 1 if one can find the destination through path (start at [0][0])

- Test Case
```java
import java.util.ArrayDeque;
import java.util.Deque;

public class maze {

    public static void main(String[] args) {
        int[][] sample1 = new int[][] { {1, 1, 1, 1},
                                        {1, 1, 1, 1},
                                        {1, 1, 1, 1},
                                        {1, 1, 1, 9}};

        int[][] sample2 = new int[][] { {1, 1, 0, 0},
                                        {1, 0, 0, 1},
                                        {1, 1, 9, 1},
                                        {1, 1, 1, 0}};

        int[][] sample3 = new int[][] { {1, 0, 0, 0},
                                        {1, 1, 1, 1},
                                        {1, 0, 0, 0},
                                        {1, 0, 1, 9}};

        int[][] sample4 = new int[][] { {0, 1, 1, 1},
                                        {1, 1, 1, 1},
                                        {1, 1, 1, 1},
                                        {1, 1, 1, 9}};

        int[][] sample5 = new int[][] { {1, 1, 1, 1},
                                        {1, 0, 1, 1},
                                        {1, 0, 0, 1},
                                        {1, 1, 1, 1}};

        System.out.println(bfsFindTheTargetWithHelperFunction(sample1));
        System.out.println(bfsFindTheTargetWithHelperFunction(sample2));
        System.out.println(bfsFindTheTargetWithHelperFunction(sample3));
        System.out.println(bfsFindTheTargetWithHelperFunction(sample4));
        System.out.println(bfsFindTheTargetWithHelperFunction(sample5));
    }
```
## BFS Solution

使用一個Queue，裡面存放的是每個位置(int[]{row, col})，再使用一個 visited array 來記錄哪一些點曾經遍訪過。

將當前遍訪的點周圍右方與下方（有些題目要放入八個方位，所以可以使用一個for loop來遍歷），只要是 valid && 內容是 path && 不曾造訪過，就放入Queue當中，繼續造訪，直到Queue為空，這中間都沒遇到 Target ， Return false。


```java
    public static boolean bfsFindTheTargetWithHelperFunction(int[][] matrix) {
        int row = matrix.length;
        if(row == 0) return false;
        int col = matrix[0].length;

        if(matrix[0][0] == 0) return false;

        boolean[][] visited = new boolean[row][col];
        // Pass the position of row and col
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[]{0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            int[] currentNode = queue.pollFirst();
            int curRow = currentNode[0];
            int curCol = currentNode[1];

            if(matrix[curRow][curCol] == 9) return true;
            visited[curRow][curCol] = true;

            // helper variables
            int[][] directions = new int[][]{{1, 0}, {0, 1}};

            for(int[] dir : directions) {
                int newRow = curRow + dir[0];
                int newCol = curCol + dir[1];
                if(newRow < row && newCol < col &&
                   matrix[newRow][newCol] != 0 && !visited[newRow][newCol]) {
                    queue.offerLast(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return false;
    }


    public static boolean bfsFindTheTarget(int[][] matrix) {
        int row = matrix.length;
        if(row == 0) return false;
        int col = matrix[0].length;

        if(matrix[0][0] == 0) return false;

        boolean[][] visited = new boolean[row][col];
        // Pass the position of row and col
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[]{0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            int[] currentNode = queue.pollFirst();
            int curRow = currentNode[0];
            int curCol = currentNode[1];

            if(matrix[curRow][curCol] == 9) return true;
            visited[curRow][curCol] = true;

            if(curRow + 1 < row && matrix[curRow + 1][curCol] != 0 && !visited[curRow + 1][curCol]) {
                queue.offerLast(new int[]{curRow + 1, curCol});
                visited[curRow + 1][curCol] = true;
            }

            if(curCol + 1 < col && matrix[curRow][curCol + 1] != 0 && !visited[curRow][curCol + 1]) {
                queue.offerLast(new int[]{curRow, curCol + 1});
                visited[curRow][curCol + 1] = true;
            }
        }

        return false;
    }
```

## DFS Solution

從起點出發，利用 recursive function 來處理下一個點，需要檢查是否valid，接著檢查是否visited，如果都沒有，先標記該點為visited，然後將 c + 1 和 r + 1 分別丟入 DFS 當中。

```java
    public static boolean dfsFindTheTarget(int[][] matrix) {
        int row = matrix.length;
        if(row == 0) return false;
        int col = matrix[0].length;

        if(matrix[0][0] == 0) return false;

        boolean[][] visited = new boolean[row][col];

        return dfsFindTheTarget(matrix, visited, 0, 0);
    }

    public static boolean dfsFindTheTarget(int[][] matrix, boolean[][] visited, int r, int c) {
        // Check the input is valid
        if(r >= matrix.length || c >= matrix[0].length) return false;
        if(visited[r][c]) return false;

        // Mark the visited node
        visited[r][c] = true;

        // Dfs Traversal
        if(matrix[r][c] == 9) {
            return true;
        } else if(matrix[r][c] == 0) {
            return false;
        } else {
            return dfsFindTheTarget(matrix, visited, r + 1, c)
                    || dfsFindTheTarget(matrix, visited, r, c + 1);
        }
    }

}
```


***
# 3 Sum Closest

先排序過 input Array O(nlogn)

如果input長度小於等於三，直接將數字加總 return 

初始化一個 res = num[0] + nums[1] + nums[2] 使用前三個數字。

遍歷一次整個Array，從第一個數字到倒數第三個數字，每次遍訪一個數字時，就將其後一位，以及最後一個位置放上兩個指針，將這三個指針指到的數字加總，當作該輪的 sum = nums[i] + nums[left] + nums[right]，將target - sum 與 target - res 的絕對值做比較，如果 sum 比 res 更靠近目標數的話，將sum 的數值賦予 res，並同時檢查是否等於target。

檢查完差值後，我們要讓差值更小，如果 sum > target ，右指針左移數量變少，反之左指針右移。

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int res = 0;
        
        if(len <= 3) {
            for(int n : nums) {
                res += n;
            }
            return res;
        }
        
        Arrays.sort(nums);
        
        res = nums[0] + nums[1] + nums[2];
        
        for(int i = 0; i < len - 2; i++) {
            int left = i + 1;
            int right = len - 1;
            
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum;
                    if(res == target) return res;
                }
                if(sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;   
    }
}
```

***
# Round Robin Scheduling

一个处理器要处理一堆request，一次只能处理一条，每次执行一个任务最多执行时间q，接着执行等待着的下一个任务。
若前一个任务没执行完则放到队尾，等待下一次执行。
假设只要有任务开始以后cpu是不会空闲的，也就是说cpu开始后如果空闲了就说明没有任务了，另外Robin Round最后返回值是float

[我的詳解](https://github.com/WeiChienHsu/Java_Practice/tree/master/RoundRobin)

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class RoundRobin {
    public static void main(String[] args) {
        int[] arrTime = new int[]{0,1,3,5,6};
        int[] exeTime = new int[]{5,3,6,1,4};
        int quanTime = 3;

        float averageWaitingTime = countAveWaitTime(arrTime, exeTime, quanTime);
        System.out.println(averageWaitingTime);
    }

    public static float countAveWaitTime(int[] arrTime, int[] exeTime, int q) {
        // Init current Time, waiting Time and processor pointer
        if(arrTime == null || exeTime == null || arrTime.length != exeTime.length) return 0;

        int curTime = 0, waitTime = 0, index = 0;
        int length = arrTime.length;
        Deque<process> queue = new ArrayDeque<>();

        // While there are some processes in the queue
        // haven't been handled or the processes have never been used
        while(!queue.isEmpty() || index < length) {
            // There are still have some processes in the Queue
            if(!queue.isEmpty()) {
                process curProcess = queue.pollFirst();
                // Update the waiting Time when we are starting using a process
                waitTime += curTime - curProcess.arrTime;
                // Avoid the execution Time exceed the quan Time limitation
                curTime += Math.min(curProcess.exeTime, q);

                // Put those rest of processes which's arrival time has already exceed current Time
                for(; index < length && arrTime[index] <= curTime; index++) {
                    queue.offerLast(new process(arrTime[index], exeTime[index]));
                }
                // If the current processor didn't run to the end, put it back
                // into Queue with new arrTime and exeTime
                if (curProcess.exeTime > q) {
                    queue.offerLast(new process(curTime, curProcess.exeTime - q));
                }
            }
            // There is no process in the Queue but the index doesn't not point to the end
            else {
                queue.offerLast(new process(arrTime[index], exeTime[index]));
                // Update only the current Time, no need to change the total waiting time
                curTime = arrTime[index];
                // Point to the next process
                index++;
            }
        }
        return (float) waitTime / length;
    }
}

class process {
    int arrTime;
    int exeTime;
    process(int arr, int exe) {
        this.arrTime = arr;
        this.exeTime = exe;
    }
}
```


***

# findOptimalWeights


***

# LRU Cache count miss

***

# Rotate Matrix

***

# Sum tree

***

# number of valid parentheses


***

# BST minimum sum path


***

# Reverse Second Half of Linked List

***
# Maze

***
# Shorted job first


***