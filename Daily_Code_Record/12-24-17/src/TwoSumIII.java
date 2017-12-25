import java.util.HashSet;
import java.util.Set;

public class TwoSumIII {

     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;

         TreeNode(int x) {
             val = x;
         }
     }



    public static void main(String[] args) {
       TreeNode root = new TreeNode(5);
       root.left = new TreeNode(3);
       root.right = new TreeNode(6);
       root.left.left = new TreeNode(2);
       root.left.right = new TreeNode(4);
       root.right.right = new TreeNode(7);
       int target = 9;

        Boolean result = findTarget(root, target);
        System.out.println(result);
    }

    public static boolean findTarget(TreeNode root, int k) {
                Set<Integer> set = new HashSet<>();
                return helper(root, set, k);
            }

            public static boolean helper(TreeNode root, Set set, int target) {
                if(root == null) return false;
                if(set.contains(target - root.val)) return true;
                set.add(root.val);
                return helper(root.right, set, target) || helper(root.left, set, target);
            }

        }
