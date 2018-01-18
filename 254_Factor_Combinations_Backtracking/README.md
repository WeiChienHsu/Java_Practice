# Factor Combinactions
## Any number can be broken as product of its factors, Find all possible combinations of its factor for an integer n.

```
12 = 2 * 2 * 3
   = 3 * 4
   = 2 * 6
[[2,2,3],[2,3],[2,6]]
```

## Soluction

#### Factorization : Recurision Tree
- Draw the Solution Tree
```
             12
     2/          3/  4\   6\
     6           4     3    2
   3/ 2\ 6\    2/4\  3|    |      
  2    3  1    2  1   1    1
 2|   3|      2|
  1    1       1

[223] [23] [322] [34] [43] [62] [12] -> too much 
```
- Pruning: brunch factor
#### Why not use sort + Set
- It is the method for if you needed all Lists from the result

### Pruning : If breath has dupilcate ? If Depth has dupilcate ?
- Depth: Everytimes when we do down, need to increase the number
```
      12
     2/    
     6    
   3/ 2\ 6\    
  2    3  1
 2|   3| 
  1    1  

2 -> 2 -> 3 V
2 -> 3 -> 2 X(3>2)
2 -> 6 V
```
- Remove those greater than the last one && remove the last one (list.size() = 1)
```
          12
     2/       3/     
     6        4     
      2\ 6\   4\          
       3   1   1   
      3| 
       1  
[223] [26] [34]       
```
```java
    private static void treeWithPrune(List<List<Integer>> res, List<Integer> list, int n, int startFactor) {
        if(n == 1) {
            if(list.size() > 1) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        for(int i = startFactor ; i <= n ; i++) {
            if(n % i == 0) {
                list.add(i);
                treeWithPrune(res,list,n/i, i);
                list.remove(list.size() - 1);
            }
        }
    }
```

## Optizamtion 
- 12/2 -> is dupicate to 12/6
- Math.sqrt()
- 12除以2和12除以6帶來的效果相同，替所有數開根號後，左邊的樹才是有意義的
- 但第二層以後右邊的數也會被去除，要在再把數本身加回來
```
             12
     2/          3/    
     6           4        
     2\ 6\    2/  4\           
      3  1    2     1   
     3|      2|
      1       1

[223] [23] [322] [34] [43] [62] [12] -> too much 
```
```java
       for(int i = firstFactor; i <= Math.sqrt(n); i++){
            if(n % i == 0) {
                list.add(i);
                helper(res, list, n/i, i);
                list.remove(list.size()-1);
            }
        }

        list.add(n);
        helper(res,list,1,n);
        list.remove(list.size()-1);
```