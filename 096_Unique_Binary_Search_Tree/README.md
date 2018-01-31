


```
        1             2                       3  
      / \            / \                    /  \
     0  2 3         1   3                 1 2  0
count(0) count(2)  count(1) count(1)  count(2) count(0)


count(3) = count(0) * count(2) + count(1) * count(1) + count(2) * count(1)
 
-> count(n) = sum(count(i) * count(n-1-i)) i = 0 to n - 1
```

- 使用一個Recursion :
```java
class Solution {
    public int numTrees(int n) {
        return count(n);
    }
    
    public int count(int n) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += count(i) * count(n-1-i);
        }
        
        return sum;
    }
}
```
- 在count(3)中，我們重複計算了兩次 count(2), 兩次 count(1)，使用一個array記錄下count
- int[] root -> root[i] = count(i) 
```java
class Solution {
    public int numTrees(int n) {
        int[] root = new int[n+1];
        return count(n, root);
    }
    
    public int count(int n, int[] root) {
        if(n == 0) return 1;
        if(n == 1) return 1;
        if(root[n] != 0) return root[n];
        
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += count(i, root) * count(n-1-i, root);
        }
        root[n] = sum;
        return sum;
    }
}
```

- 再簡化成不需要 recursion ， 用count[n]紀錄當層的計算
```java
class Solution {
    public int numTrees(int n) {
        if(n <= 0) {
            return 0;
        }
        int[] count = new int[n+1];
        count[0] = 1;
        
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= i-1; j++) {
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }
}
```