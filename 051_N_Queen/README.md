#
N Queens
- 有N個皇后，就會產生N個col & row
- 每個col都只能有一個Queen，斜的地方也只能有兩個皇后
(isValid判斷）
- [Q x x]
- [x x Q]
- [x Q x]
- dfs 去找 "colIndex應該在什麼位置"，如果 isValid就放進List中

#### isValid判斷方式：
- 判斷 cols.get(rowIndex) 有沒有 == colIndex(傳入的col)
- 判斷右上左下和左上右下，有無放置的Q
- col.get(rowIndex) + rowIndex = row + column
- col.get(roeIndex) - rowIndex = row - column

#### Transfer col into a String (drawChessboard):
- StringBuilder
- 兩個for循環：第一個從cols[]中拿出Q要放置的位置
- 第二個for loop 從第一個放置到最後一個，遇到Q的位置放Q，其餘放.

```java
class Solution {
public List<List<String>> solveNQueens(int n) {
List<List<String>> results = new ArrayList<>();
if(n == 0) {
return results;
}
List<Integer> cols = new ArrayList<>();
dfsHelper(results, cols, n);
return results;
}
////////
private void dfsHelper(List<List<String>> results, List<Integer> cols, int n) {
if(cols.size() == n) {
results.add(drawChessboard(cols));
return;
}
for(int colIndex = 0; colIndex < n; colIndex++) {
if(!isValid(cols, colIndex)) {
continue;
}
cols.add(colIndex);
dfsHelper(results, cols, n);
cols.remove(cols.size() - 1);
}
}
////////
// rwoIndex + col.get(rowIndex) == row + column (右上左下)
// rowIndex - col.get(rowIndex) == row - column (右下左上)
// cols.get(rowIndex) == column -> 使否使用過
////////

private boolean isValid(List<Integer> cols, int column) {
int row = cols.size();
for(int rowIndex = 0; rowIndex < row; rowIndex++ ){
// 檢查這個col是否有在前面cols[]內被選用過
if(cols.get(rowIndex) == column){
return false;
}
if(rowIndex + cols.get(rowIndex) == row + column){
return false;
}
// 檢查左上，右下
if(rowIndex - cols.get(rowIndex) == row - column) {
return false;
}
}
return true;
}
//////////
// cols [0,2,1]
// 拿 0 出來， for loop: Q . .
// 拿 2 出來， for loop: . . Q
//////////
private List<String> drawChessboard(List<Integer> cols) {
List<String> chessboard = new ArrayList<>();
for(int i = 0; i < cols.size(); i++){
StringBuilder sb = new StringBuilder();
for(int j = 0; j < cols.size(); j++){
sb.append(j == cols.get(i) ? 'Q' : '.');
}
chessboard.add(sb.toString());
}
return chessboard;
}
}
```