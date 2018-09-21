import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class nTry {

    static Map<TreeNode, float[]> map = new HashMap<>();
    static TreeNode largestNode = new TreeNode(0);
    static float maxValue = Integer.MIN_VALUE;


    public static void main(String[] args) {
        TreeNode root = new TreeNode(100);
        root.children.add(new TreeNode(120));
        root.children.add(new TreeNode(80));
        root.children.get(0).children.add(new TreeNode(40));
        root.children.get(0).children.add(new TreeNode(50));
        root.children.get(0).children.add(new TreeNode(60));
        root.children.get(1).children.add(new TreeNode(50));
        root.children.get(1).children.add(new TreeNode(70));

        System.out.println(getLargestNodeExclusive(root).val);

        for(TreeNode node : map.keySet()) {
            System.out.println(node.val + " - Average: " + map.get(node)[0] + " Count: " + map.get(node)[1]);
        }
    }

    public static TreeNode getLargestNodeExclusive(TreeNode root) {
        helper(root);
        return largestNode;

    }

    public static void helper(TreeNode root) {
        if(root.children.size() == 0) {
            return;
        }

        float count = 0;
        float sum = 0;

        for(int i = 0; i < root.children.size(); i++) {
            TreeNode child = root.children.get(i);
            helper(child);
            count += map.containsKey(child)?  map.get(child)[1] + 1 : 1;
            sum += map.containsKey(child)? map.get(child)[0] * map.get(child)[1] : child.val;
        }
        if((sum / count)> maxValue) {
            maxValue = sum / count;
            largestNode = root;
        }

        map.put(root, new float[]{sum / count, count});
    }

    public static TreeNode getLargestNodeInclusive(TreeNode root) {
        helper1(root);

        float max = Integer.MIN_VALUE;
        TreeNode result = root;

        for(TreeNode node : map.keySet()) {
            if(map.get(node)[0] > max) {
                max = map.get(node)[0];
                result = node;
            }
        }

        return result;

    }

    public static void helper1(TreeNode root) {
        if(root.children.size() == 0) {
            return;
        }

        float count = 1;
        float sum = root.val;

        for(int i = 0; i < root.children.size(); i++) {
            TreeNode child = root.children.get(i);
            helper1(child);
            count += map.containsKey(child)?  map.get(child)[1] : 1;
            sum += map.containsKey(child)? map.get(child)[0] * map.get(child)[1] : child.val;
        }

        map.put(root, new float[]{sum / count, count});
    }
}


class TreeNode {
    int val;
    List<TreeNode> children;
    TreeNode(int val) {
        this.val = val;
        children = new ArrayList<>();
    }
}