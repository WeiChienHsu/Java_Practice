# Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

```
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```


## Solution - DFS
這是一個在圖中找最後節點的題目，使用DFS + backtracking。

先利用一個Map或是function將 digits 與輸出String之間Maping起來。

使用一個dfsHelper function 來處理 DFS，輸出條件是當當前處理index與被處理String長度相同時，將此時的combination加入result Array當中。

以23為例，DFS第一層是處理 2 輸出的 "abc"，依序取了'a', 'b', 'c'，加入combination當中，丟入下一層的DFS處理，使用index+1的方式讓指針指到3，處理'def'，依序加入combination，得到'ad'，進入下一層時，因為 index == 2 等於 23 的長度，所以將這個combination從StringBuilder轉換成String加入解答中。（記得，每次處理完當層的digits後，都要backtracking remove 最後一個 char。

```java
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length() == 0) return result;
        
        StringBuilder combination = new StringBuilder();
        Solution.dfsHelper(result, combination, 0, digits);
        return result;
    }

    public static void dfsHelper(List<String> result,
                                 StringBuilder combination,
                                 int index,
                                 String digits) {

        if(index == digits.length()){
            result.add(combination.toString());
            return;
        }

        String currentString = Solution.getString(digits.charAt(index));

        for(int i = 0; i < currentString.length(); i++) {
            combination.append(currentString.charAt(i));
            Solution.dfsHelper(result, combination, index + 1, digits);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
    
    static public String getString(char num) {
        switch (num) {
            case '2':
                return "abc";
            case '3':
                return "def";
            case '4':
                return "ghi";
            case '5':
                return "jkl";
            case '6':
                return "mno";
            case '7':
                return "pqrs";
            case '8':
                return "tuv";
            case '9':
                return "wxyz";
        }
        return "";
    }
}
```