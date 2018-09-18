/*

Image there is a binary tree, 
we start from the dummy root, 
and the left child node means choice 1 (append a lower case), 
and right child node is choice 2 (upper case). 

Hence if String here is abc, we have a tree looks like this.

  /   \
 a     A
/ \   / \ 
ab aB Ab AB
/ \ / \ / \ / 
abc abC................................

Obviously our goal here will be printing out all leaf nodes, 
which we can accomplished by using DFS with backTracking or BFS.
*/


class Solution {
  public List<String> letterCasePermutation(String S) {
      List<String> result = new ArrayList<>();
      StringBuilder sb = new StringBuilder();
      backtracking(S, 0, sb, result);
      return result;
  }
  
  public void backtracking(String S, int index, StringBuilder sb, List<String> result) {
      if(index == S.length()) {
          result.add(new String(sb.toString()));
          return;
      }
      
      /* isDigit */
      if(S.charAt(index) >= '0' && S.charAt(index) <= '9') {
          sb.append(S.charAt(index));
          backtracking(S, index + 1, sb, result);
          sb.deleteCharAt(sb.length() - 1);
      }
      else {
          /* isAlph */
          sb.append(Character.toUpperCase(S.charAt(index)));
          backtracking(S, index + 1, sb, result);
          sb.deleteCharAt(sb.length() - 1);
          
          sb.append(Character.toLowerCase(S.charAt(index)));
          backtracking(S, index + 1, sb, result);
          sb.deleteCharAt(sb.length() - 1);
      }
  }
}