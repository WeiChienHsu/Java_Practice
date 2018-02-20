# Clone Graph
## Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

## DFS
- Time: O(V + 2E)
- Space: O(V)

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
```

## BFS


1. 只給了一個點，要如何透過該點找到「所有點」： 從 node 出發，找到所有點 （node -> nodes)
2. 複製所有的點，成為一個新的圖 (copy nodes)
3. 原本圖中有連接的部分，到了新圖中也要連接 (copy edges)
4. return 的值為map.get(node)，Map中對應的node


- Use Queue to record the next level
- Map to find copied Node
- While Creating a new Node, put it into the Queue
- While poll out the Node in Queue, we connect current node with it by searching in Map
- 將現在處理的Node放入Queue和Map中，每次都處理Queue彈出來的Node，複製一個點，將其鄰居遍歷一次，如果沒在Map內的，放入Map同時放入Queue，有在Map內的，將複製的點連接至剛放入Map內的值，下回繼續從Queue中彈出來Node。

```
0  <->  1  <->  4
|       |       |
3  <->  2       5
--------------------
放0到Queue & Map內

0  <->  1  <->  4
|       |       |
3  <->  2       5

____________
0
____________

0

-------------------
彈出0
1,3不在Map內
Map中放入1,3
Queue放入1,3
0連接 1,3


0  <->  1  <->  4
|       |       |
3  <->  2       5

____________
  3   1       0->
____________

0  -> 1
|
3
-------------------

彈出1
0在Map內
2,4不在Map內
Map中放入2,4
Queue放入2,4
1連接 0, 2,4


0  <->  1  <->  4
|       |       |
3  <->  2       5

____________
 2  4  3       1 ->     
____________

0 <-> 1   ->  4
|     |
3     2
-------------------

彈出3
0,2在Map內
3連接 2,0


0  <->  1  <->  4
|       |       |
3  <->  2       5

____________
 2  4         3 ->     
____________

0 <-> 1   ->  4
|     |
3  -> 2
-------------------

彈出4
1在Map內
5不在Map內
Map中放入5
Queue放入5
4連接 1, 5


0  <->  1  <->  4
|       |       |
3  <->  2       5

____________
    5   2        4 ->     
____________

0 <-> 1   <->  4
|     |        |
3  -> 2        5
-------------------

彈出2
1 3在Map內
2連接 1, 3


0  <->  1  <->  4
|       |       |
3  <->  2       5

____________
    5          2  ->     
____________

0 <-> 1   <->  4
|     |        |
3 <-> 2        5
-------------------


彈出5
4在Map內
5連接 4


0  <->  1  <->  4
|       |       |
3  <->  2       5

____________
             5  ->     
____________

0 <-> 1   <->  4
|     |        |
3 <-> 2        5
-------------------
```