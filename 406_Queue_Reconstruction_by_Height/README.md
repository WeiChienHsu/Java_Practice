# Queue Reconstruction by Height
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

## Solution - Build a customized LinkedLists

練習自己寫一個 Linked List 結構以及Override compare Comparator的題目。

[k, n] 此前有 n 個 >= k height的人

```
[7,0][4,4][7,1][5,0][6,1][5,2]

先照著身高排序，在照著身高後的數字排序

[7,0][7,1][6,1][5,0][5,2][4,4]

依序以一個新的 newNode 放入 original Node 當中

(7,0) init original Node

----
(7,1)

(7,0) -> (7,1)

----
(6,1)

(7,0) -> (6,1) -> (7,1)
----
(5,0)

(5,0) -> (7,0) -> (6,1) -> (7,1)
----
(5,2)

(5,0) -> (7,0) -> (5,2) -> (6,1) -> (7,1)

---
(4,4)

(5,0) -> (7,0) -> (5,2) -> (6,1) -> (4,4) -> (7,1)
  4        3        2         1
```

1. 先使用 Comparator 來排序，第一判斷條件是身高（從大到小 b[0] - a[0]，若身高相同，判斷後者數字(從小到大 a[1] -  b[1])
```java
Arrays.sort(people, new Comparator<int[]>() {
  @Override
  public int comapre(int[] a, int[] b) {
    if(a[0] != b[0]) return b[0] - a[0];
    else return a[1] - b[1];
  }
});
```

2. 自製一個 LinkedList 來處理 insert，並將所有的array放入List中
```java
class static Node {
  int[] person;
  Node next;
  public Node(int[] p) {
    this.person = p;
  }
}
```

3. 插入函數：
一開始new一個新的Node，並把該person的值賦予其中。
```java
Node newNode = Node(person[]);
```
有三種狀況：

#### 當 rank == 0 的時候，代表新加入的點前方沒有大於等於他的數字，所以直接插入Node的最前方
```java
newNode.next = originalNode;
return newNode;
```
#### 當 rank == 1 的時候，代表我們要放入的Node前方只有一個大於等於他的數字，所以要先用個臨時指針，找到原本Node的第一個，並且插入他的後面，然後將他原本後一位的Node，連接在我們的newNode上。
```java
Node cur = originalNode;
// 已經找到 rank == 1 該插入的位置
Node next = cur.next;
newNode.next = next;
cur.next = newNode;
return 
```
#### 當 rank > 1 的時候，代表我們還未找到該插入的點，前方目前只有一個大於等於Node的數字，所以要借助current指針往後走 n 步，讓rank == 1

```java
Node cur = originalNode;
while(rank > 1) {
  cur = cur.next;
  rank--
}
```

4. 將person從 LinkedList 中依序取出

```java
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if(people.length == 0) return people;
        int[][] results = new int[people.length][people[0].length];
        // Sort the original array by it's height and if it is the same
        // sorted by the rank(second element in the list)
        Arrays.sort(people, new Comparator<int[]>(){
           @Override
            public int compare(int[] a, int[] b) {
                if(a[0] != b[0]) return b[0] - a[0];
                else return a[1] - b[1];
            }
        });
        
        // Init the original Node
        Node originalNode = new Node(people[0]);
        
        // Put all person into the Node
        for(int i = 1; i < people.length; i++) {
            originalNode = Solution.insertNode(originalNode, people[i]);
        }
        
        // Put all person into the result array
        for(int i = 0; i < people.length; i++) {
            results[i] = originalNode.person;
            originalNode = originalNode.next;
        }
        return results;
    }
    
    // rank == 0: insert the newNode in front or original Node and return newNode
    // rank > 1: find the current node that makes the rank == 1 by pointing to the next current node
    // rank == 1: insert the newNode into the next one from the current node and points to the original current next
    
    public static Node insertNode(Node originalNode, int[] person) {
        // Given a new node
        Node newNode = new Node(person);
        int rank = person[1];
        if(rank == 0) {
            newNode.next = originalNode;
            return newNode;
        }
        
        // Given a current pointer to handle connection
        Node current = originalNode;
        while(rank > 1) {
            current = current.next;
            rank--;
        }
        
        // Insert the newNode to the current.next
        // connect current.next with newNode.next
        Node next = current.next;
        newNode.next = next;
        current.next = newNode;
        return originalNode;     
    }
}

class Node {
    public int[] person;
    public Node next;
    public Node(int[] p) {
        this.person = p;
    }
}

```

***

## 更精簡的方式！！！！(但時間較久)

```
add到首部，使用LinkedList
add到尾部，使用ArrayList
add到中间位置，使用ArrayList
```

先按照我們上方的排序方式，在插入ArrayList裡面，不要使用LinkedList，因為找尋位置在LinkedList中很耗費時間，ArrayList是用移動的方式。

```java
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (p1, p2) -> { 
            return p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]; 
        }); 
        List<int[]> list = new ArrayList<>(); 
        for (int i = 0; i < people.length; i++)
            list.add(people[i][1], people[i]); 

        return list.toArray(people); 
    }
}
```