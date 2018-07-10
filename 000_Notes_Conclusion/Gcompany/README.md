# 686 Repeated String Match

The idea is to keep string builder and appending until the length A is greater or equal to B.

```java
 public int repeatedStringMatch(String A, String B) {

    int count = 0;
    StringBuilder sb = new StringBuilder();
    while (sb.length() < B.length()) {
        sb.append(A);
        count++;
    }
    if(sb.toString().contains(B)) return count;
    if(sb.append(A).toString().contains(B)) return ++count;
    return -1;
}
```

# 687

- 要讓左子樹與右子樹回傳最大值，且該值符合以下判斷：取決於子數與父樹數值是否相等？
- 紀錄當前最長的值 ： 左節點長 + 右節點長

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
  public int maxHeight = 0;
  public int longestUnivaluePath(TreeNode root) {
      if(root == null) return 0;
      getHeight(root, root.val);
      return maxHeight;
  }
  
  public int getHeight(TreeNode node, int val) {
      if(node == null) return 0;
      //  Get the Height from left and right
      int left = getHeight(node.left, node.val);
      int right = getHeight(node.right, node.val);
      // if node value == fater node value -> return Max(right or left) 
      // if node value != father node value -> return 0
      // Record the current Hieght(Since the left and right always equal to their father's value or they wont be greater than 0)
      maxHeight = Math.max(left + right, maxHeight);
      if(val == node.val) return Math.max(left, right) + 1;
      return 0;
  }
}
```


```java
import java.util.*;

public class getTreeHeight {
    public static void main(String[] args) {
        int[] values = new int[] {1, 1, 1};
        int[] edges = new int[] {1, 2, 1, 3};


        TreeNode root = buildTree(makePair(values, edges));

        System.out.println(getHeight(root));


    }
    public static int getHeight(TreeNode root) {
        int[] maxHeight = new int[1];
        if(root == null) return 0;
        getHeight(root, root.val, maxHeight);
        return maxHeight[0];
    }

    public static int getHeight(TreeNode node, int val, int[] maxHeight) {
        if(node == null) return 0;
        int left = getHeight(node.left(), node.val, maxHeight);
        int right = getHeight(node.right(), node.val, maxHeight);
        maxHeight[0] = Math.max(left + right, maxHeight[0]);
        if(val == node.val) return Math.max(left, right) + 1;
        return 0;
    }

    public static TreeNode buildTree(List<Pair> edges ) {
        Set<TreeNode> rootNodes = new HashSet<>();
        Set<TreeNode> childNodes = new HashSet<>();

        for(Pair pair: edges){
            pair.start.addChild(pair.end);

            rootNodes.remove(pair.end);
            childNodes.add(pair.end);

            if (!childNodes.contains(pair.start)) {
                rootNodes.add(pair.start);
            }
        }

        if (rootNodes.isEmpty()) {
            throw new IllegalArgumentException("Input pairs contain a cycle with the root");
        }

        return rootNodes.iterator().next();

    }

    /* Pair (start ListNode, end ListNode) */
    public static List<Pair> makePair(int[] values, int[] edges) {
        Map<Integer, TreeNode> map = new HashMap<>();
        int index = 1;
        /* Store the index and TreeNode in the Map */
        for(int value : values) {
            map.put(index, new TreeNode(value));
            index++;
        }

        List<Pair> list = new ArrayList<>();

        /* Store the Start and End TreeNode in the Pair */
        for(int i = 0; i < edges.length - 1; i += 2) {
            Pair pair = new Pair(map.get(edges[i]), map.get(edges[i + 1]));
            list.add(pair);
        }
        return list;
    }

}


class TreeNode {
    int val;
    private List<TreeNode> children;
    TreeNode(int x) {
        this.val = x;
        children = new ArrayList<>();
    }

    public void addChild(TreeNode node){
        children.add(node);
    }

    public TreeNode left() {
        if(this.children.size() < 1) return null;
        else return this.children.get(0) ;
    }

    public TreeNode right() {
        if(this.children.size() < 2) return null;
        else return this.children.get(1) ;
    }
}

class Pair {
    TreeNode start;
    TreeNode end;
    Pair(TreeNode start, TreeNode end) {
        this.start = start;
        this.end = end;
    }
}
```