# Clone Graph



## DFS
- When Creating a New Copy Node, put the original and copy Nodes both into Map <ori, copy>
-  Map<UndirectedGraphNode, UndirectedGraphNode>
```java
map.put(node new UndirectedGraphNode(node.label) )
```
- When Dealing with the Node processing:
- Check all its neighbors by original node's "node.neighbors" list
```java
for(UndirectedGraphNode neighbor : node.neighbors) {
```
- Create a New CopyNode by get the original value from map
- If there is no such Node has been created and been put into map, Go DFS that Node,  Create and put:
```java
  if(newNeighbor == null) {
      newNeighbor = DFSHelper(neighbor, map);
  }
```
- Then, connect the new Neighbor with the Copy Node

- 原點出發，Map放入原點與複製的點，依據原點的鄰居找鄰居連接，如果沒在Map裡，用DFS到那個點運行，有在Node裡的話，直接連接，不斷DFS下一個節點再返回，將會串連所有複製的點。

```
0  <->  1  <->  4
|       |       |
3  <->  2       5
____________________
0層：複製0 放入map

*
0  <->  1  <->  4
|       |       |
3  <->  2       5

0

____________________

0 層 : 往1找，沒在Map，DFS
1 層 : 複製1 放入map

        *
0  <->  1  <->  4
|       |       |
3  <->  2       5

0       1

____________________

1 層 : 往4找，沒在Map，DFS
4 層 : 複製4 放入map

                *
0  <->  1  <->  4
|       |       |
3  <->  2       5

0       1       4

____________________

4 層 : 往5找，沒在Map，DFS
5 層 : 複製5 放入map

                
0  <->  1  <->  4
|       |       |
3  <->  2       5
                *
0       1       4

                5
____________________


5 層 : 往4找，在Map，連上4，沒鄰居了，返回4

                *
0  <->  1  <->  4
|       |       |
3  <->  2       5
                
0       1       4

                5
____________________


4 層 : 5有了Node，連接，找下個鄰居
4 層 : 1也有了Node，連接，沒鄰居了，返回1

                *
0  <->  1  <->  4
|       |       |
3  <->  2       5
                
0       1   <-  4
                |
                5
____________________


1 層 : 4有了Node，連接，找下個鄰居
1 層 : 往2找，沒再Map，DFS

        
0  <->  1  <->  4
|       |       |
3  <->  2       5
        *        
0       1   <-> 4
                |
                5
____________________

2 層 : 複製2，放入Map，找鄰居
2 層 : 找到1，有Node，連接
2 層 : 找到3，沒在Map，DFS

        
0  <->  1  <->  4
|       |       |
3  <->  2       5
        *        
0       1   <-> 4
        |       |
        2       5
____________________
3 層 : 複製3，放入Map，找鄰居
3 層 : 找到0，有Node，連接
3 層 : 找到2，有Node，連接
3 層 : 沒鄰居了，返回2層

        
0  <->  1  <->  4
|       |       |
3  <->  2       5
       
0       1   <-> 4
｜      |       |
3   ->  2       5
*
____________________
2 層 : 3有Node了，連接
2 層 : 沒鄰居了，反回1 


        
0  <->  1  <->  4
|       |       |
3  <->  2       5
*        
0       1   <-> 4
｜      |       |
3  <->  2       5
        *
____________________

1 層 : 2有Node了，連接
1 層 : 找鄰居，0有Node，連接
1 層 : 沒鄰居了，返回0 


        
0  <->  1  <->  4
|       |       |
3  <->  2       5
        * 
0   <-  1   <-> 4
｜      |       |
3  <->  2       5
        
____________________

0 層 : 1有Node了，連接 
0 層 : 找鄰居，3有Node，連接
0 層 : 沒有鄰居了，回傳Node 


        
0  <->  1  <->  4
|       |       |
3  <->  2       5
        * 
0   <-  1   <-> 4
｜      |       |
3  <->  2       5
        
____________________