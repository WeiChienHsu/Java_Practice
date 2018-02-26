# Serialized And Deserialized 


## Divide and Conquer - Recording Null value
- Seriazed : Inorder Traverse to record all nodes in the tree
- Deserialized: Put all recorded nodes into a Queue, Q&D way to build up a tree
- Conquer & Divede : if node == null(Which means == "#" in this case) return null, then build the left node and right node by recursive calling buildTree method and return the root.

```java
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        String res = root.val + "!";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> queue = new ArrayDeque<>();
        String[] strings = data.split("!");
        for(String s : strings){
            queue.offerLast(s);
        }
        return recordNodes(queue);
    }
    
    public TreeNode recordNodes(Deque<String> queue){
        String val = queue.pollFirst();
        if(val.equals("#")){
            return null;
        }
        
        TreeNode head = new TreeNode(Integer.valueOf(val));
        head.left = recordNodes(queue);
        head.right = recordNodes(queue);
        return head;
    }
}

```