# Palindorme Partition

- 是一種 Combination 問題
- a1b2c -> 幾種選擇切 1 , 2 的方式， [1] [2] [1,2] []
- 每一層必須放入 i + 1 的startIndex
- 利用 s.substring(startIndex, i + 1)的方式分割字串
- 當 startIndex == s.length() （當指針從起點走向終點）， results 加入 combination
- 初始化的 startIndex就是切第一刀的地方0，接著會切在後面1,2的數字上

```
(0,1) a      
(1,2) a,a
(2,3) a,a,b
---------
(0,2) aa,
(2,3) aa,b
---------
(0,3) abc
```

```java
    public static List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        if(s.length() == 0 ) {
            return results;
        }

        List<String> combination = new ArrayList<>();
        dfsHelper(results, combination, 0, s);
        return results;
    }

    private static void dfsHelper(List<List<String >> results,
                                  List<String> combination,
                                  int startIndex,
                                  String s) {
        if(s.length() == startIndex) {
            results.add(new ArrayList<>(combination));
            return;
        }

        for(int i = startIndex; i < s.length(); i++) {
            String subString = s.substring(startIndex, i + 1);
            if(!isPart(subString)){
                continue;
            }
            combination.add(subString);
            dfsHelper(results,combination, i + 1, s);
            combination.remove(combination.size() - 1);
        }
    }

    private static boolean isPart(String s) {
        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
```

## 優化： 先處理paril判斷
```java
        for(int i = startIndex; i < s.length(); i++) {
            if(isPart(s,startIndex,i)){
                combination.add(s.substring(startIndex,i + 1));
                dfsHelper(results,combination, i + 1, s);
                combination.remove(combination.size() - 1);
            }


        }
    }

    private static boolean isPart(String s , int low, int high) {
        while (low < high) {
            if(s.charAt(low++) != s.charAt(high--)){
                return false;
            }
        }
        return true;
    }
}
```