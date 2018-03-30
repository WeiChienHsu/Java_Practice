# Populating Next Right Pointers in Each Node II

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
```
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
```

## Solution 
- 找規律: 
- 用一個 tempChild(類似dummyNode的概念)寫入memory(有new)，和一個currentChild(類似curNode的概念) 指針放在 tempChild上
- currentChild 先指向root.left (先判斷root.left是否為null)，接著移動到currentChild.next上(等於在root.left上)
- 在用 currentChild(root.left) 指向root.right (先判斷root.right是否為null)，接著移動到currentChild.next上(等於在root.right上)
- 結束left and right後，將root指向root.next（如果此時沒有next會指向null，如果有會指向同一層的右邊節點)
- 兩層的while loops，內層的在做層級遍歷，外層的在做深度遍歷
- 外層結束即得到新的樹。
- 結束內層的while loop後，必須讓 root = tempChild.next 也就是他的左子樹，代表進入下一層。

```java
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        while(root != null) {
            TreeLinkNode tempChild = new TreeLinkNode(0);
            TreeLinkNode currentChild = tempChild;
            while(root != null) {
                if(root.left != null) {
                    currentChild.next = root.left;
                    currentChild = currentChild.next;
                }
                
                if(root.right != null) {
                    currentChild.next = root.right;
                    currentChild = currentChild.next;
                }
                
                root = root.next;
            }
            root = tempChild.next;
        }
    }
}

```