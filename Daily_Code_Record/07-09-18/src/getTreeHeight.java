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

